package com.assignment.springboot.controller;
import com.assignment.springboot.data.dto.CustomerDTO;
import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping
    public List<CustomerDTO> getCustomers(){
        return this.customerService.getAllCustomer();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable int id){
        boolean delete= this.customerService.deleteCustomer(id);
        if (delete)
            return ResponseEntity.ok().body("Delete success customer have id" + id);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Can't find/delete customer have id "+ id));

    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer (@RequestBody CustomerDTO  customerDTO, @PathVariable int id){
        CustomerDTO customerDTOUpdate=this.customerService.updateCustomer(customerDTO,id);
        return ResponseEntity.ok().body(customerDTOUpdate);
    }
    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO){
        this.customerService.saveCustomer(customerDTO);
        return ResponseEntity.ok().body(customerDTO);
    }
    @GetMapping("/phone")
    public ResponseEntity<CustomerDTO> getCustomerByPhoneNumber(@RequestParam String phoneNumber){
        CustomerDTO customerDTO=this.customerService.findCustomerByPhoneNumber(phoneNumber);
        return ResponseEntity.ok().body(customerDTO);
    }
}
