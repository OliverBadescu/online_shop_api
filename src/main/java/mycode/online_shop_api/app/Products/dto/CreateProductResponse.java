package mycode.online_shop_api.app.Products.dto;

import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
public record CreateProductResponse(int id, String category, LocalDate createDate, String description, String name, double price, int stock, double weight) implements Serializable {
}
