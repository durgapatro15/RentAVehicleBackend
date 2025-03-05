// package com.rent.rentavehicle.controller;

// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.rent.rentavehicle.entity.Customer;
// import com.rent.rentavehicle.service.CustomerService;

// @RestController
// @RequestMapping("/customers")
// public class CustomerController {

//     private final CustomerService customerService;

//     public CustomerController(CustomerService customerService) {
//         this.customerService = customerService;
//     }

//     // Create a new customer
//     @PostMapping
//     public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
//         Customer savedCustomer = customerService.createCustomer(customer);
//         return ResponseEntity.ok(savedCustomer);
//     }

//     // Get customer by ID
//     @GetMapping("/{customerId}")
//     public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) {
//         Customer customer = customerService.getCustomerById(customerId);
//         return ResponseEntity.ok(customer);
//     }

//     // Get customer by Email
//     @GetMapping("/email/{email}")
//     public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
//         Customer customer = customerService.getCustomerByEmail(email);
//         return ResponseEntity.ok(customer);
//     }

//     // Get all customers
//     @GetMapping
//     public ResponseEntity<List<Customer>> getAllCustomers() {
//         List<Customer> customers = customerService.getAllCustomers();
//         return ResponseEntity.ok(customers);
//     }

//     // Delete a customer by ID
//     @DeleteMapping("/{customerId}")
//     public ResponseEntity<String> deleteCustomer(@PathVariable String customerId) {
//         customerService.deleteCustomer(customerId);
//         return ResponseEntity.ok("Customer deleted successfully.");
//     }

// }

package com.rent.rentavehicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rent.rentavehicle.entity.Customer;
import com.rent.rentavehicle.service.CustomerService;

@RestController
@CrossOrigin(origins = "*")  
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Create a new customer
    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    // Get customer by ID
    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {
        return customerService.getCustomerById(customerId);
    }

    // Get customer by Email
    @GetMapping("/email/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    // Get all customers
    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Delete customer by ID
    @DeleteMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
        return "Customer deleted successfully.";
    }
}

