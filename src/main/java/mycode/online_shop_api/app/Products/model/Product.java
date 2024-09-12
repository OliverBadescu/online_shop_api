package mycode.online_shop_api.app.Products.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import mycode.online_shop_api.app.OrderDetails.model.OrderDetails;
import mycode.online_shop_api.app.ProductCategories.model.ProductCategories;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Table(name = "products")
@Entity(name = "Product")
public class Product {


    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "product_sequence"
    )
    @Column(name = "id")
    private int id;


    @NotBlank(message = "Product name cannot be empty")
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;


    @NotNull(message = "Price is required")
    @Positive(message = "Price must be a positive number")
    @Column(
            name = "price",
            nullable = false,
            columnDefinition = "DOUBLE"
    )
    private Double price;


    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be a positive number")
    @Column(
            name = "weight",
            nullable = false,
            columnDefinition = "DOUBLE"
    )
    private Double weight;


    @NotBlank(message = "Descriptions cannot be empty")
    @Column(
            name = "descriptions",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String descriptions;

    @NotBlank(message = "Category cannot be empty")
    @Column(
            name = "category",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String category;


    @NotNull(message = "Create date is required")
    @Column(
            name = "create_date",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate createDate;

    @Min(value = 0, message = "Stock must be at least 0")
    @Column(
            name = "stock",
            nullable = false,
            columnDefinition = "INT"
    )
    private int stock;

    @OneToMany(mappedBy ="product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
        @ToString.Exclude
    private Set<OrderDetails> orderDetails = new HashSet<>();

    private void addOrderDetails(OrderDetails orderDetails){
        this.orderDetails.add(orderDetails);
        orderDetails.setProduct(this);
    }
    private void removeOrderDetails(OrderDetails orderDetails){
        this.orderDetails.remove(orderDetails);
        orderDetails.setProduct(null);
    }

    @OneToMany(mappedBy ="product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
        @ToString.Exclude
    private Set<ProductCategories> productCategories = new HashSet<>();

    private void addProductCategory(ProductCategories productCategory){
        this.productCategories.add(productCategory);
        productCategory.setProduct(this);
    }

    private void removeProductCategory(ProductCategories productCategory){
        this.productCategories.remove(productCategory);
        productCategory.setProduct(null);
    }
}
