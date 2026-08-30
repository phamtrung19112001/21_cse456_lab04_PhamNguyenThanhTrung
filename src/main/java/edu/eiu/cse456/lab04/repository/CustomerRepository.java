package edu.eiu.cse456.lab04.repository;

import edu.eiu.cse456.lab04.entity.Customer;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
}
