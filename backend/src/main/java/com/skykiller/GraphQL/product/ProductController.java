package com.skykiller.GraphQL.product;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductController {

  private final ProductService service;

  ProductController(ProductService service) {
    this.service = service;
  }

  // Aggregate root

  @GetMapping("/products")
  List<Product> all() {
    return service.getAll();
  }

  @PostMapping("/products")
  Product newProduct(@RequestBody Product newProduct) {
    return service.save(newProduct);
  }

  // Single item

  @GetMapping("/products/{id}")
  Product one(@PathVariable Long id) {

    return service.findById(id);

  }

  @PutMapping("/products/{id}")
  Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {

   return service.replaceProduct(newProduct, id);
  }

  @DeleteMapping("/products/{id}")
  void deleteProduct(@PathVariable Long id) {
    service.deleteById(id);
  }
}