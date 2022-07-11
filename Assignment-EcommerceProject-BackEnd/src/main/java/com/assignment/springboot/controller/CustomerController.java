package com.assignment.springboot.controller;

import com.assignment.springboot.dto.CustomerDTO;
import com.assignment.springboot.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @Operation(summary = "get list customer")
    public List<CustomerDTO> getCustomers() {
        return this.customerService.getAllCustomer();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted.category.by.id"),
            @ApiResponse(responseCode = "404", description = "not.found.customer")
    })
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        this.customerService.deleteCustomer(id);
        return ResponseEntity.ok().body("Delete.success.customer.have.id" + id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update customer")
    public ResponseEntity<CustomerDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable int id) {
        CustomerDTO customerDtoUpdate = this.customerService.updateCustomer(customerDTO, id);
        return ResponseEntity.ok().body(customerDtoUpdate);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create new customer")
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDto) {
        this.customerService.createCustomer(customerDto);
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping("/phone")
    @Operation(summary = "get customer by phone number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found.customer.by.phone.number"),
            @ApiResponse(responseCode = "404", description = "not.found.customer")
    })
    public ResponseEntity<CustomerDTO> getCustomerByPhoneNumber(@RequestParam String phoneNumber) {
        CustomerDTO customerDto = this.customerService.findCustomerByPhoneNumber(phoneNumber);
        return ResponseEntity.ok().body(customerDto);
    }
}
