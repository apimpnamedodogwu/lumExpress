package africa.semicolon.lumexpress.data.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Seller extends LumExpressUser {

    @OneToOne(fetch = FetchType.EAGER)
    private Store store;
}
