package edu.eci.arsw.didiApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.didiApp.model.Customer;
import edu.eci.arsw.didiApp.persistence.DidiAppApplicationPersistenceException;
import edu.eci.arsw.didiApp.services.UserServices;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserServices userServices;

    //Método de prueba para verificar autowired y repo
    @GetMapping(value = "/usertest")
    public ResponseEntity<?> testMethod() throws DidiAppApplicationPersistenceException {
        Customer customer = new Customer();
        customer.setName("test name");
        customer.setEmail("test1@mail.com");
        customer.setPassword("123");
        userServices.saveUser(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    /**
     * Gets the User
     * @return All User
     */
    @GetMapping
    public ResponseEntity<?> getAllUser(){
        try{
            List<Customer> customers = userServices.getAllUsers();
            return new ResponseEntity<>(customers,HttpStatus.ACCEPTED);
        }catch (DidiAppApplicationPersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @GetMapping(path = "/{user}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("user") String email){
        Customer customer = null;
        try{
            customer = userServices.findUserByEmail(email);
            return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
        }catch (DidiAppApplicationPersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    
    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody Customer customer){
        try{
            userServices.saveUser(customer);
            return new ResponseEntity<>(customer,HttpStatus.CREATED);
        }catch (DidiAppApplicationPersistenceException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{user}")
    public ResponseEntity<?> updateUser(@PathVariable("user") String user,@Valid @RequestBody Customer customer){
        try {
            //System.out.println(customer.getEmail());
            userServices.updateUser(user,customer);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (DidiAppApplicationPersistenceException ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
        }        

    }
}
