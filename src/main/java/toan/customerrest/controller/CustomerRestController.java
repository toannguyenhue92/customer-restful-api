package toan.customerrest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toan.customerrest.model.Customer;
import toan.customerrest.service.CustomerService;

@RestController
@RequestMapping(path = "api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "")
    public ResponseEntity<List<Customer>> allCustomers() {
        List<Customer> customers = customerService.findAll();
        if (customers == null) {
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(name = "id") Long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @PostMapping(path = "")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(name = "id") Long id,
            @RequestBody Customer customer) {
        if (customerService.findById(id) == null && !customer.getId().equals(id)) {
            return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
        }
        customerService.save(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable(name = "id") Long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
        }
        customerService.remove(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
}
