package com.example.demo;
import com.example.demo.services.DataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringController{
    
    @Autowired
    DataService dataService;

    @GetMapping
    public String home(Model model){
        model.addAttribute("fuelStats", dataService.getAllStats());
        return "home";
    }

}