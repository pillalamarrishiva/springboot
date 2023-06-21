package com.example.demo.Services;

import com.example.demo.Entities.Customer;
import com.example.demo.Repositories.CustomerRepository;
import com.example.demo.SecondEntities.Product;
import com.example.demo.SecondRepositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(Integer id)
    {
        Optional<Product> a = productRepository.findById(id);
        return a.orElse(null);
    }



}
