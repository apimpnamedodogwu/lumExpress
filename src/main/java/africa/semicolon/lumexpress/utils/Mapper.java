package africa.semicolon.lumexpress.utils;

import africa.semicolon.lumexpress.data.dto.request.UserRequest;
import africa.semicolon.lumexpress.data.models.Address;
import africa.semicolon.lumexpress.data.models.Authority;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Customer;

import java.util.HashSet;
import java.util.Set;

public class Mapper {
    public static void mapRequestToUser(UserRequest userRequest, Customer customer) {
        customer.setEmail(userRequest.getEmail());
        customer.setAddress(new HashSet<>(Set.of(new Address(userRequest.getCountry()))));
        customer.setPassword(userRequest.getPassword());
        customer.setCart(Cart.builder().build());
        customer.setAuthority(Authority.AUTH_3);
    }

    public static void updateUser(UserRequest userRequest, Customer customer) {
        if (userRequest.getFirstname() != null) {
            customer.setFirstName(userRequest.getFirstname());
        }

        if (userRequest.getLastname() != null) {
            customer.setLastName(userRequest.getLastname());
        }

        if (userRequest.getPhoneNumber() != null) {
            customer.setPhoneNumber(userRequest.getPhoneNumber());
        }

        updateAddress(userRequest, customer);
    }

    private static void updateAddress(UserRequest userRequest, Customer customer) {
        if (userRequest.getCountry() == null) {
            customer.getAddress().stream().findFirst().ifPresent(address -> {
                if (userRequest.getCity() != null) {
                    address.setCity(userRequest.getCity());
                }

                if (userRequest.getState() != null) {
                    address.setState(userRequest.getState());
                }

                if (userRequest.getStreet() != null) {
                    address.setStreet(userRequest.getStreet());
                }

                if (userRequest.getHouseNumber() != null) {
                    address.setBuildingNumber(userRequest.getHouseNumber());
                }

                if (userRequest.getZipCode() != null) {
                    address.setZipCode(userRequest.getZipCode());
                }

            });
        }
    }
}
