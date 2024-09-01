package mycode.online_shop_api.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "price",
            nullable = false,
            columnDefinition = "DOUBLE"
    )
    private Double price;

    @Column(
            name = "weight",
            nullable = false,
            columnDefinition = "DOUBLE"
    )
    private Double weight;

    @Column(
            name = "descriptions",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String descriptions;

    @Column(
            name = "category",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String category;

    @Column(
            name = "create_date",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate createDate;

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
