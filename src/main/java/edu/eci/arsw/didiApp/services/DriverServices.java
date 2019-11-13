package edu.eci.arsw.didiApp.services;

import java.util.List;


import edu.eci.arsw.didiApp.model.Car;
import edu.eci.arsw.didiApp.model.Driver;
import edu.eci.arsw.didiApp.persistence.DidiAppApplicationPersistenceException;

public interface DriverServices {

    List<Driver> getAllDrivers() throws DidiAppApplicationPersistenceException;

    void saveDriver(Driver driver) throws DidiAppApplicationPersistenceException;

    Driver findDriverByEmail(String email) throws DidiAppApplicationPersistenceException;

    List<Car> findCarsByEmail(String user) throws DidiAppApplicationPersistenceException;

    void updateDriver(String user, Driver driver) throws DidiAppApplicationPersistenceException;

    void updateCar(String user, Car car) throws DidiAppApplicationPersistenceException;

}
