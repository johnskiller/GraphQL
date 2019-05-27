package com.skykiller.GraphQL.product;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class ProductService {
    private ProductRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    // use constructer injection
    ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    
    List<Product> getAll() {
        logger.info("get all products");
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