package com.caderea.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Cade on 3/27/2016.
 */
@Controller
@RequestMapping("/")
public class BuildingController {

    private BuildingRepository buildingRepo;
    private Building getOneBuilding;

    @Autowired
    public BuildingController(BuildingRepository buildingRepo){
        this.buildingRepo = buildingRepo;
    }

    //Home page
    @RequestMapping(method = RequestMethod.GET)
    public String home(Map<String,Object> model){

        model.put("buildings", buildingRepo.getAllBuildings());
        model.put("getOneBuilding",getOneBuilding);
        model.put("rooms", buildingRepo.getAllRooms());

        if(getOneBuilding != null)
            model.put("getOneBuildingRooms",buildingRepo.getRoomsInBuilding(getOneBuilding.getId()));
        else
            model.put("getOneBuildingRooms",null);

        return "home";
    }

    @RequestMapping(value = "/saveBuilding", method = RequestMethod.POST)
    public String saveBuilding(
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("numFloors") int numFloors,
            @RequestParam("numRooms") int numRooms
            ){
        Building building = new Building(name, address, numFloors);
        buildingRepo.saveBuilding(building);

        //Generate a new room for every room in the new building
        int roomsPerFloor = numRooms / numFloors;
        int extraRooms = numRooms % numFloors;

        for(int i = 0; i < numFloors; ++i){             //loop every floor
            for(int j = 0; j < roomsPerFloor; ++j) {    //loop for every room on a floor
                buildingRepo.saveRoom(new Room(0, building.getId(), i));    //create a room with the given building and floor.
                                                                            //id is assigned when saved in BuildingRepo
                if (i == 1 && extraRooms > 0) {     //if there is not an equal number of roomsPerFloor
                    j--;                            //squeeze the extra rooms onto the first floor
                    extraRooms--;                   //decrement j & extraRooms to keep the loop going
        }   }   }

        return "redirect:/";
    }

    @RequestMapping(value = "/deleteBuilding", method=RequestMethod.POST)
    public String deleteBuilding(@RequestParam("id") long id){
        buildingRepo.deleteBuilding(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/getOneBuilding", method=RequestMethod.POST)
    public String getOneBuilding(@RequestParam("id") long id, Map<String,Object> model){
        Building b = buildingRepo.getOneBuilding(id);
        getOneBuilding = b;
        return "redirect:/";
    }
}
