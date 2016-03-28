package com.caderea.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by Cade on 3/27/2016.
 */
@Controller
@RequestMapping("/")
public class BuildingController {

    private BuildingRepository buildingRepo;

    @Autowired
    public BuildingController(BuildingRepository buildingRepo){
        this.buildingRepo = buildingRepo;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(Map<String,Object> model){
        List<Building> buildings = buildingRepo.getAll();
        model.put("buildings",buildings);
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Building building){
        buildingRepo.save(building);
        return "redirect:/";
    }
}
