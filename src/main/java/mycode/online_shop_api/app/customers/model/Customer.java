package mycode.online_shop_api.app.customers.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import mycode.online_shop_api.app.orders.model.Order;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Table(name = "customer")
@Entity(name = "Customer")
public class Customer implements Serializable {


    @Id
    @SequenceGenerator(
            name="customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "customer_sequence"
    )

    @Column(
            name = "id"
    )
    private int id;


    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    @Column(
            name = "full_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String fullName;

    @Column(
            name = "billing_address",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String billingAddress;

    @Column(
            name = "shipping_address",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String shippingAddress;

    @Column(
            name = "country",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String country;

    @Column(
            name = "phone",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String phone;


    @OneToMany(mappedBy ="customer",fetch = FetchType.LAZY,cascade = CascadeType.ALL ,orphanRemoval = true)
    @Builder.Default
        @ToString.Exclude
    @JsonManagedReference
    private Set<Order> orders = new HashSet<>();

    public void addOrder(Order order){

        this.orders.add(order);
        order.setCustomer(this);

    }

    public void deleteOrder(Order order){
        this.orders.remove(order);
        order.setCustomer(null);

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}

