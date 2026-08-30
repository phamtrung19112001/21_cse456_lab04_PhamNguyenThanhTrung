package edu.eiu.cse456.lab04.repository;

import edu.eiu.cse456.lab04.entity.Product;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
}
