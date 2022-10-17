package africa.semicolon.lumexpress.services;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UpdateCustomerDetails {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String imageURL;
}
