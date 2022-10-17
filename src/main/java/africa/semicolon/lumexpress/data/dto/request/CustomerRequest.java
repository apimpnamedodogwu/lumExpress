package africa.semicolon.lumexpress.data.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CustomerRequest {
    private String country;
    private String email;
    private String password;
}
