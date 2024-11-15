package com.pjatk.MPR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeService {


    private BikeRepository repository;
@Autowired
    public BikeService(BikeRepository repository) {
        this.repository = repository;
        this.repository.save(new Bike("ad", "black"));
        this.repository.save(new Bike("Artem", "black"));
    }

    public BikeRepository getRepository() {
        return repository;
    }

    public void setRepository(BikeRepository repository) {
        this.repository = repository;
    }

    public List<Bike> findByColor(String color) {
        List<Bike> bikeList = this.repository.findByColor(color);
        if(bikeList.isEmpty()) {
            throw new BikeNotFoundException();
        } else {
            return bikeList;
        }
    }
    public Bike addBike(Bike bike) {
        List<Bike> bikeListName = this.repository.findByName(bike.getName());
        List<Bike> bikeListColor = this.repository.findByColor(bike.getColor());
        if (!bikeListName.isEmpty() && !bikeListColor.isEmpty()) {
            throw new BikeAlreadyExistException();
        } else {
            return this.repository.save(bike);
        }
    }


    public List<Bike> findByName(String name) {
        Iterable<Bike> bikeList = this.repository.findAll();
        List<Bike> bikes = new ArrayList<>();

        for (Bike bike : bikeList) {
            if (bike.getName().equals(name)) {
                bikes.add(bike);
            }
        }

        return bikes;
    }

    public Iterable<Bike> findAll() {
        return this.repository.findAll();

    }
}
