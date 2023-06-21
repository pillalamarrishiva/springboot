package com.example.demo.Services;

//import com.example.demo.Config.DataBaseConfig;
import com.example.demo.Entities.Customer;
import com.example.demo.Repositories.CustomerRepository;
import com.example.demo.SecondEntities.Product;
import com.example.demo.SecondRepositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.util.Optional;

@Service
public class CustomerService {

    // for primary database use @PersistenceContext, itself contains primaryEntityManger name
    @PersistenceContext
    private EntityManager entityManager;

    // for secondaryEntityManger we need to explicitly mention EntityManager name
    @Autowired
    @Qualifier("secondaryEntityManagerFactory")
    private EntityManager secondaryEntityManager;

    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private ProductRepository productRepository;

    public Customer getCustomerById(Integer id)
    {
        Optional<Customer> a = customerRepository.findById(id);
       return a.orElse(null);
    }


    //give the transactionManager of particular DataBase in @Transactional annotation
    //here  primaryTransactionManager
    //use primary database TransactionManager and EntityManager
    @Transactional("primaryTransactionManager")
    public void updateUser(Customer customer) {
        String firstName = customer.getFirstName();
        Integer id = customer.getCustomerId();
        String s= "update Customer set first_name = :firstName where customer_id =:id";

       Query q= entityManager.createNativeQuery(s, Customer.class);
       q.setParameter("firstName",firstName);

        q.setParameter("id",id);
       q.executeUpdate();
       System.out.println("hi");

    }

    //give the transactionManager of particular DataBase in @Transactional annotation
    //here  secondaryTransactionManager
    //use secondary database TransactionManager and EntityManager
    @Transactional("secondaryTransactionManager")
    public void updateProduct(Product product) {
        Integer id = product.getProductId();
        String name = product.getName()
;        String s= "update Product set name = :name where productId =:id";

        Query q= secondaryEntityManager.createNativeQuery(s, Product.class);
        q.setParameter("name",name);
        q.setParameter("id",id);
        q.executeUpdate();

    }

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
