package africa.semicolon.lumexpress.data.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder

public class CustomerResponse {
    @NonNull
    private String message;
    @NonNull
    private HttpStatus status;
    private Long id;

}
