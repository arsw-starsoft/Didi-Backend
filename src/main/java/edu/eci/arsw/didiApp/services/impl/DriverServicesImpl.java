package edu.eci.arsw.didiApp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.eci.arsw.didiApp.model.Car;
import edu.eci.arsw.didiApp.model.Driver;
import edu.eci.arsw.didiApp.persistence.CarRepository;
import edu.eci.arsw.didiApp.persistence.DriverRepository;
import edu.eci.arsw.didiApp.persistence.DidiAppApplicationPersistenceException;
import edu.eci.arsw.didiApp.services.DriverServices;

@Service
public class DriverServicesImpl implements DriverServices {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Driver> getAllDrivers() throws DidiAppApplicationPersistenceException {

        return driverRepository.findAll();

    }

    @Override
    public void saveDriver(Driver driver) throws DidiAppApplicationPersistenceException {
        Optional<Driver> optionalDriver = driverRepository.findByEmail(driver.getEmail());
        if (optionalDriver.isPresent()) {
            throw new DidiAppApplicationPersistenceException(DidiAppApplicationPersistenceException.DRIVER_ALREDY_EXISTS);
        } else {
            driverRepository.save(driver);
        }

    }

    @Override
    public Driver findDriverByEmail(String driver) throws DidiAppApplicationPersistenceException {
        Optional<Driver> optinalDriver = driverRepository.findByEmail(driver);
        if (!optinalDriver.isPresent())
            throw new DidiAppApplicationPersistenceException(DidiAppApplicationPersistenceException.DRIVER_NOT_FOUND);
        return optinalDriver.get();
    }

    
    @Override
    public List<Car> findCarsByEmail(String user) throws DidiAppApplicationPersistenceException {
        Optional<Driver> optinalUser = driverRepository.findByEmail(user);
        boolean present = optinalUser.isPresent();
        System.out.println(present);
        if (!present)
            throw new DidiAppApplicationPersistenceException(DidiAppApplicationPersistenceException.DRIVER_NOT_FOUND);
        return optinalUser.get().getCars();
    }
    

    @Override
    public void updateDriver(String user, Driver driver) throws DidiAppApplicationPersistenceException {
        Optional<Driver> optinalDriver = driverRepository.findByEmail(user);
        boolean present = optinalDriver.isPresent();
        if (!present){
            throw new DidiAppApplicationPersistenceException(DidiAppApplicationPersistenceException.DRIVER_NOT_FOUND);
        }else{
            Driver dri = optinalDriver.get();
            setCars(dri,driver.getCars());
            dri.setCellPhone(driver.getCellPhone());
            dri.setFirstName(driver.getFirstName());
            dri.setLastName(driver.getLastName());
            dri.setName(driver.getUserName());
            dri.setPassword(driver.getPassword());
            driverRepository.save(dri);
        }
        
    }

    private void setCars(Driver driver, List<Car> cars) throws DidiAppApplicationPersistenceException{
        Boolean flag = false;
        if (driver.getCars().isEmpty()){
            for (Car i: cars){
                i.setDriver(driver);
                carRepository.save(i);
            }
            driver.setCars(cars);
        }else{
            List<Car> currentCars = driver.getCars();
            for (Car i: cars){
                for (Car j: currentCars){
                    if (j.getPlate().equals(i.getPlate())){
                        flag = true;
                        throw new DidiAppApplicationPersistenceException(DidiAppApplicationPersistenceException.CAR_ALREDY_EXISTS);
                    }
                }
                if(!flag){
                    i.setDriver(driver);
                    carRepository.save(i);
                    currentCars.add(i);
                }
            }
            driver.setCars(currentCars);
        }

    }

    

    @Override
    public void updateCar(String user, Car car) throws DidiAppApplicationPersistenceException {
        Optional<Driver> optinalDriver = driverRepository.findByEmail(user);
        boolean present = optinalDriver.isPresent();
        if (!present){
            throw new DidiAppApplicationPersistenceException(DidiAppApplicationPersistenceException.DRIVER_NOT_FOUND);
        }else{
            Driver dri = optinalDriver.get();
            car.setDriver(dri);
            carRepository.save(car);
            if (!dri.getCars().isEmpty()){
                List<Car> cars = dri.getCars();
                cars.add(car);
                dri.setCars(cars);
            }else{
                List<Car> newCar = new ArrayList<>();
                newCar.add(car);
                dri.setCars(newCar);
            }
            driverRepository.save(dri);

        }
    

    }
}
