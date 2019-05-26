package com.skykiller.GraphQL.product;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Product {
    private @Id @GeneratedValue Long id;
    private String name;
    private Double price;

    Product() {}

    Product(String name, Double price) {
      this.name = name;
      this.price = price;
    }
}