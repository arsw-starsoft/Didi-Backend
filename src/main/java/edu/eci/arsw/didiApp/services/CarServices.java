package edu.eci.arsw.didiApp.services;

import java.util.List;

import edu.eci.arsw.didiApp.model.Car;
import edu.eci.arsw.didiApp.persistence.DidiAppApplicationPersistenceException;

public interface CarServices {

    List<Car> getAllCars() throws DidiAppApplicationPersistenceException;

    void saveCar(Car car) throws DidiAppApplicationPersistenceException;

    Car findCarByPlate(String plate) throws DidiAppApplicationPersistenceException;
}
