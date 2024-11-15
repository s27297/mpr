package com.pjatk.MPR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BikeController {
    BikeService service ;

    private BikeRepository repository;
@Autowired
    public BikeController(BikeService service) {
        this.service = service;
    }


//    public Bike findByName(String name) {
//        return this.repository.findByColor(name);
//    }

    @GetMapping("bike/{color}")
    public List<Bike> getBikeByColor(@PathVariable("color") String color)
    {
        return this.service.findByColor(color);
    }


    @PostMapping("/bike/add")
    public Bike addBikeWithBody(@RequestBody Bike bike) {
        return this.service.addBike(bike);
    }

    @PutMapping("/bike/change")
    public Bike changeBike() {
        return this.service.
    }


    @DeleteMapping("/bike/delete")
    public Bike deleteBike() {

    }

    @GetMapping("bike/b")
    public List<Bike> getBikeByName(@PathVariable("name") String name) {return  this.service.findByName(name);}
}
