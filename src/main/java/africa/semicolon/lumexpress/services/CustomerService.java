package africa.semicolon.lumexpress.services;


import africa.semicolon.lumexpress.data.dto.request.LoginRequest;
import africa.semicolon.lumexpress.data.dto.request.UserRequest;
import africa.semicolon.lumexpress.data.dto.response.CustomerResponse;
import africa.semicolon.lumexpress.data.dto.response.LoginResponse;
import africa.semicolon.lumexpress.data.dto.response.UserResponse;
import africa.semicolon.lumexpress.services.exceptions.CustomerIdException;

import java.util.List;

public interface CustomerService {
    CustomerResponse register(UserRequest request);

    LoginResponse login(LoginRequest request);

    UserResponse getACustomer(Long id) throws CustomerIdException;

    List<UserResponse> getAllCustomers();

    Long count();

    CustomerResponse updateCustomer(UserRequest request, Long customerId) throws CustomerIdException;
}
