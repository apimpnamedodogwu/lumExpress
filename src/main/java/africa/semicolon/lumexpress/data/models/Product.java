package africa.semicolon.lumexpress.data.models;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private Category category;
    private String imageURL;
}
