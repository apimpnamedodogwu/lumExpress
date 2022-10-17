package africa.semicolon.lumexpress.services;


import africa.semicolon.lumexpress.data.dto.request.LoginRequest;
import africa.semicolon.lumexpress.data.dto.request.UserRequest;
import africa.semicolon.lumexpress.data.dto.response.CustomerResponse;
import africa.semicolon.lumexpress.data.dto.response.LoginResponse;
import africa.semicolon.lumexpress.data.dto.response.UserResponse;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.repositories.CustomerRepository;
import africa.semicolon.lumexpress.services.exceptions.CustomerIdException;
import africa.semicolon.lumexpress.utils.Mapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImplementation implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse register(UserRequest request) {
        Customer customer = new Customer();
        Mapper.mapRequestToUser(request, customer);
        customerRepository.save(customer);
        return new CustomerResponse("Customer with id number " + customer.getId() + " has been created successfully", HttpStatus.OK, customer.getId());
    }


    @Override
    public LoginResponse login(LoginRequest request) {
        return null;
    }

    @Override
    @SneakyThrows(CustomerIdException.class)
    public UserResponse getACustomer(Long id) {
        var customer = customerRepository.findById(id).orElseThrow(() -> new CustomerIdException(id));
        return customer.userResponse();
    }

    @Override
    public List<UserResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(Customer::userResponse).toList();
    }

    @Override
    public Long count() {
        return customerRepository.count();
    }

    @Override
    @SneakyThrows(CustomerIdException.class)
    public CustomerResponse updateCustomer(UserRequest request, Long customerId) {
        var customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerIdException(customerId));
        Mapper.updateUser(request, customer);
        customerRepository.save(customer);
        return new CustomerResponse("Customer with id number " + customerId + " has been updated successfully", HttpStatus.OK);
    }
}
