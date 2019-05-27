package com.skykiller.GraphQL.product;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
class ProductService {
    private ProductRepository repository;
    
    // use constructer injection
    ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    
    List<Product> getAll() {
        return repository.findAll();
    }

	public Product save(Product newProduct) {
		return repository.save(newProduct);
	}

	public Product findById(Long id) {
		return repository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
  
	}

	public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    public Product replaceProduct( Product newProduct,  Long id) {
        return repository.findById(id)
        .map(Product -> {
          Product.setName(newProduct.getName());
          Product.setPrice(newProduct.getPrice());
          return repository.save(Product);
        })
        .orElseGet(() -> {
          newProduct.setId(id);
          return repository.save(newProduct);
        });
    }
}