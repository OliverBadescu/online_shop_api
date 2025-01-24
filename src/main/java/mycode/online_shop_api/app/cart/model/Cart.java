package mycode.online_shop_api.app.cart.model;

import jakarta.persistence.*;
import lombok.*;
import mycode.online_shop_api.app.products.model.Product;
import mycode.online_shop_api.app.users.model.User;

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
@Table(name = "cart")
@Entity(name = "Cart")
public class Cart {

    @Id
    @SequenceGenerator(
            name = "cart_sequence",
            sequenceName = "cart_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "cart_sequence"
    )
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartProduct> cartProducts = new HashSet<>();

    public void addProduct(Product product, int quantity) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(this);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(quantity);
        this.cartProducts.add(cartProduct);
    }

    public void removeProduct(Product product) {
        this.cartProducts.removeIf(cartProduct -> cartProduct.getProduct().equals(product));
    }

}
