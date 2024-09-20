package mycode.online_shop_api.app.Products.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record CreateProductRequest(String category, LocalDate createDate, String description, String name, double price, int stock, double weight) implements Serializable {
}
