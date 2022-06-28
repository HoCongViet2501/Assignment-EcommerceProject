package com.assignment.springboot.service.impl;
import com.assignment.springboot.data.dto.CustomerDTO;
import com.assignment.springboot.data.entity.Customer;
import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.repository.CustomerRepository;
import com.assignment.springboot.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customers=this.customerRepository.findAll();
        for(Customer customer: customers){
            List<CustomerDTO> customerDTOS=new ArrayList<>();
            if(!customers.isEmpty()){
               CustomerDTO customerDTO= modelMapper.map(customer, CustomerDTO.class);
                customerDTOS.add(customerDTO);
            }else{
                return customerDTOS;
            }
        }
        throw new ResourceNotFoundException("Can't get all Customer");
    }

    @Override
    public boolean deleteCustomer(int id) {
        Optional<Customer> customer=this.customerRepository.findById(id);
        if(customer.isPresent()){
            this.customerRepository.delete(customer.get());
            return true;
        }else {
            return false;
        }
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, int id) {
        log.info("update customer");
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            modelMapper.map(customerDTO, customer);
            this.customerRepository.save(customer);
            return customerDTO;
        } else {
            throw new ResourceNotFoundException("can't find customer have id: " + id);
        }
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        log.info("save new customer to database");
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        this.customerRepository.save(customer);
        return true;
    }

    @Override
    public CustomerDTO findCustomerByPhoneNumber(String phoneNumber) {
        log.info("find customer by name");
        Customer customer = this.customerRepository.findCustomerByPhoneNumber(phoneNumber);
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        if (customer != null) {
            return customerDTO;
        } else {
            throw new ResourceNotFoundException("Can't find category have name : " + phoneNumber);
        }
    }
}
