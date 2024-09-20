package mycode.online_shop_api.app.Products.dto;

import java.time.LocalDate;

public record UpdateProductRequest (
        String category,
        String description,
        String name,
        double price,
        int stock,
        double weight){
}
