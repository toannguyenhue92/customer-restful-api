package toan.customerrest.service;

import java.util.List;
import toan.customerrest.model.Customer;

public interface CustomerService {
    List<Customer> findAll();

    Customer findById(Long id);

    void save(Customer customer);

    void remove(Long id);
}
