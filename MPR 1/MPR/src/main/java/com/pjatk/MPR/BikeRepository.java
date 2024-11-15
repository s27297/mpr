package com.pjatk.MPR;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository
    extends CrudRepository <Bike, Long> {

    public List<Bike> findByColor(String color);

    public List<Bike> findByName(String name);
}
