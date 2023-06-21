package com.example.demo.Controllers;

import com.example.demo.Entities.Customer;
import com.example.demo.Repositories.CustomerRepository;
import com.example.demo.SecondEntities.Product;
import com.example.demo.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hi")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/welcome")
    public String welcome()
    {
        return "HI There!";
    }

    @RequestMapping(value = "/getCustomerById" , method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerById(@RequestParam Integer id)
    {
        Customer c;
        c = customerService.getCustomerById(id);
        return new ResponseEntity<>(c,HttpStatus.OK);
    }


    @RequestMapping(value = "/updateCustomer" , method = RequestMethod.PUT)
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer)
    {
        customerService.updateUser(customer);
        String a= "user updated";
        return new ResponseEntity<String>(a,HttpStatus.CREATED);
    }


    @RequestMapping(value = "/updateProduct" , method = RequestMethod.PUT)
    public ResponseEntity<String> updateProduct(@RequestBody Product product)
    {
        customerService.updateProduct(product);
        String a= "product updated";
        return new ResponseEntity<String>(a,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/createCustomer" , method = RequestMethod.POST)
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer)
    {
        customerService.createCustomer(customer);
        String a= "user created";
        return new ResponseEntity<String>(a,HttpStatus.CREATED);
    }


}
