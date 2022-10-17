package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.dto.request.UserRequest;
import africa.semicolon.lumexpress.data.dto.response.CustomerResponse;
import africa.semicolon.lumexpress.data.dto.response.UserResponse;
import africa.semicolon.lumexpress.data.models.Address;
import africa.semicolon.lumexpress.services.exceptions.CustomerIdException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CustomerServiceImplementationTest {
    @Autowired
    private CustomerService customerService;
    private UserResponse userResponse;
    private UserRequest request;
    private UserRequest requestTwo;
    private CustomerResponse customerResponse;
    private CustomerResponse customerResponseTwo;

    @BeforeEach
    void setUp() {
        request = UserRequest.builder()
                .email("eden@gmail.com")
                .country("Nigeria")
                .password("12345")
                .firstname("Eden")
                .lastname("Elenwoke")
                .city("Lagos")
                .state("Lagos")
                .zipCode("10001")
                .street("Emily Akinola, street, Akoka, Yaba")
                .houseNumber(31)
                .phoneNumber("08160577375")
                .build();

        requestTwo = UserRequest.builder()
                .email("dan@gmail.com")
                .country("Nigeria")
                .password("12345")
                .firstname("Daniel")
                .lastname("Elenwoke")
                .city("Port Harcourt")
                .state("Rivers")
                .zipCode("50002")
                .street("Chokmah International School")
                .houseNumber(31)
                .phoneNumber("08160577375")
                .build();
        customerResponse = customerService.register(request);
        customerResponseTwo = customerService.register(requestTwo);

        userResponse = UserResponse.builder()
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    @Test
    void testThatACustomerCanBeRegistered() {
        assertEquals(2L, customerService.count());
        assertEquals(customerResponse.getMessage(), "Customer with id number " + customerResponse.getId() + " has been created successfully");
        assertEquals(customerResponse.getStatus(), HttpStatus.OK);
    }

    @Test
    void testThatACustomerCanBeGotten() {
        try {
            var response = customerService.getACustomer(1L);
            assertEquals(response.getEmail(), "eden@gmail.com");
        } catch (CustomerIdException message) {
            System.out.println(message.getMessage());
        }

        assertEquals(userResponse.getPhoneNumber(), "08160577375");
        assertEquals(userResponse.getFirstname(), "Eden");
        assertEquals(userResponse.getLastname(), "Elenwoke");
    }

    @Test
    void testThatCustomerIdExceptionMessageIsThrown() {
        try {
            customerService.getACustomer(10L);
        } catch (CustomerIdException message) {
            System.out.println(message.getMessage());
        }
    }

    @Test
    void testThatAllCustomersCanBeGotten() {
        assertEquals(customerService.getAllCustomers().size(), 2);
    }

    @Test
    void testThatCustomerDetailsCanBeUpdated() throws CustomerIdException {
        request.setFirstname("Love");
        request.setLastname("Bilquis");
        customerService.updateCustomer(request, 1L);

    }
}