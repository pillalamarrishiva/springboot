package com.example.demo.SecondEntities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Product")
public class Product {
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Integer productId;

    @Column(name = "name")
    private String name;
}
