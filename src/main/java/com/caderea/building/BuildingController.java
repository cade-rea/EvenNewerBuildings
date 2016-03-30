package com.caderea.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(method = RequestMethod.GET)
    public String home(Map<String,Object> model){
        List<Building> buildings = buildingRepo.getAll();
        model.put("buildings",buildings);
        model.put("getOneBuilding",getOneBuilding);
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String submit(Building building){
        buildingRepo.save(building);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method=RequestMethod.POST)
    public String delete(@RequestParam("id") long id){
        buildingRepo.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/getone", method=RequestMethod.POST)
    public String getOne(@RequestParam("id") long id, Map<String,Object> model){
        Building b = buildingRepo.getOne(id);
        getOneBuilding = b;
        return "redirect:/";
    }
}
