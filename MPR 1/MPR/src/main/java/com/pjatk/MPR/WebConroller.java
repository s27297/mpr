package com.pjatk.MPR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebConroller {

    BikeService service ;
    @Autowired
    public WebConroller(BikeService service) {
        this.service = service;
    }

    @GetMapping(value = "/welcome")
    public String getWelcomeView() { return "welcome";}

    @GetMapping(value = "/index")
    public String getIndexView(Model model) {
        model.addAttribute("bikes", service.findAll());
        return "index";
    }

    @GetMapping(value = "/addBike")
    public String displayAddBike(Model model) {
        model.addAttribute("bike", new Bike());
        return "addBike";
    }

    @PostMapping(value = "/addBike")
    public String saveBike(@ModelAttribute Bike bike) {
        service.addBike(bike);

        return "addBike";
    }


}
