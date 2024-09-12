package mycode.online_shop_api.app.Categories.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import mycode.online_shop_api.app.ProductCategories.model.ProductCategories;

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
@Table(name = "category")
@Entity(name = "Category")
public class Category {

    @Id
    @SequenceGenerator(
            name="category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "category_sequence"
    )

    @Column(
            name = "id"
    )
    private int id;

    @NotBlank(message = "Category cannot be empty")
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @OneToMany(mappedBy ="category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
        @ToString.Exclude
    private Set<ProductCategories> productCategories = new HashSet<>();

    private void addProductCategory(ProductCategories productCategory){
        this.productCategories.add(productCategory);
        productCategory.setCategory(this);
    }

    private void removeProductCategory(ProductCategories productCategory){
        this.productCategories.remove(productCategory);
        productCategory.setCategory(null);
    }

}
