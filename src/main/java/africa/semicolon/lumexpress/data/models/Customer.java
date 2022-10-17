package africa.semicolon.lumexpress.data.models;

import africa.semicolon.lumexpress.data.dto.response.UserResponse;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Customer extends LumExpressUser {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Address> address;

    private Authority authority;


    public UserResponse userResponse() {
        return UserResponse.builder()
                .id(getId())
                .email(getEmail())
                .firstname(getFirstName())
                .lastname(getLastName())
                .addresses(getAddress())
                .phoneNumber(getPhoneNumber())
                .cart(getCart())
                .build();
    }
}
