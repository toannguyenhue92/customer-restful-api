package toan.customerrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toan.customerrest.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
