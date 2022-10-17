package africa.semicolon.lumexpress.services;

import africa.semicolon.lumexpress.data.dto.request.UserRequest;
import africa.semicolon.lumexpress.data.dto.response.CustomerResponse;
import africa.semicolon.lumexpress.data.dto.response.UserResponse;
import africa.semicolon.lumexpress.data.models.Address;
import africa.semicolon.lumexpress.data.models.Authority;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Customer;
import africa.semicolon.lumexpress.data.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerImplTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImplementation customerServiceImplementation;

    private CustomerResponse customerResponse;

    private UserRequest request;
    private UserResponse userResponse;

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
        customerResponse = customerServiceImplementation.register(request);

        userResponse = UserResponse.builder()
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }


    @Test
    void testThatACustomerCanBeRegistered() {
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerArgumentCaptor.capture());
        var savedCustomer = customerArgumentCaptor.getValue();
        assertThat(savedCustomer.getEmail()).isEqualTo(request.getEmail()).contains("eden@gmail.com");
        var savedCustomerAddress = savedCustomer.getAddress().stream().findFirst();
        savedCustomerAddress.ifPresent(address -> assertThat(address.getCountry()).isEqualTo("Nigeria"));
        verify(customerRepository, times(1)).save(savedCustomer);
    }

    @Test
    void testThatACustomerCanBeGotten() {
        when(customerRepository.findById(customerResponse.getId())).thenReturn(Optional.of(Customer.builder().build()));
        var existingCustomer = customerServiceImplementation.getACustomer(customerResponse.getId());
        ArgumentCaptor<Long> customerArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(customerRepository).findById(customerArgumentCaptor.capture());
        var existingCustomerId = customerArgumentCaptor.getValue();
        assertThat(existingCustomerId).isEqualTo(existingCustomer.getId());
        verify(customerRepository, times(1)).findById(existingCustomerId);
    }

    @Test
    void testThatAllCustomersCanBeGotten() {
        List<Customer> aCustomer = new ArrayList<>();
        Customer customer = Customer.builder()
                .cart(Cart.builder().build())
                .address(Set.of(new Address()))
                .authority(Authority.AUTH_2)
                .build();
        aCustomer.add(customer);
        when(customerRepository.findAll()).thenReturn(aCustomer);
        var customerList = customerServiceImplementation.getAllCustomers();
        assertThat(customerList.size()).isEqualTo(1);
    }

    @Test
    void testThatCustomerDetailsCanBeUpdated() {
        request.setFirstname("Chimaraobim");
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        when(customerRepository.findById(customerResponse.getId())).thenReturn(Optional.of(Customer.builder().build()));
        var customerToBeUpdated = customerServiceImplementation.updateCustomer(request, customerResponse.getId());
        verify(customerRepository).save(customerArgumentCaptor.capture());
        var updatedCustomer = customerArgumentCaptor.getValue();
        assertThat(updatedCustomer.getFirstName()).isEqualTo(request.getFirstname());
        verify(customerRepository, times(1)).findById(customerResponse.getId());
        verify(customerRepository, times(2)).save(updatedCustomer);

    }
}
