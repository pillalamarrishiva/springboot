package com.example.demo.Controllers;


import com.example.demo.SecondEntities.Product;
import com.example.demo.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/getProductById" , method = RequestMethod.GET)
    public ResponseEntity<Product> getCustomerById(@RequestParam Integer id)
    {
        Product p;
        p = productService.getProductById(id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }


}