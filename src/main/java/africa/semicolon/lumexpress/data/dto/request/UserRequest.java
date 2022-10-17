package africa.semicolon.lumexpress.data.dto.request;

import lombok.*;

@Data
//@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private Long id;
    private Integer houseNumber;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phoneNumber;

}
