package edu.eci.arsw.didiApp.services;

import java.util.List;

import edu.eci.arsw.didiApp.model.Customer;
import edu.eci.arsw.didiApp.persistence.DidiAppApplicationPersistenceException;

public interface UserServices {

    List<Customer> getAllUsers() throws DidiAppApplicationPersistenceException;

    void saveUser(Customer customer) throws DidiAppApplicationPersistenceException;

    Customer findUserByEmail(String email) throws DidiAppApplicationPersistenceException;

    void updateUser(String user, Customer customer) throws DidiAppApplicationPersistenceException;

}
