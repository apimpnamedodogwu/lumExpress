package africa.semicolon.lumexpress.data.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;

    @ElementCollection
    private List<Category> category;

    private String imageURL;
}
