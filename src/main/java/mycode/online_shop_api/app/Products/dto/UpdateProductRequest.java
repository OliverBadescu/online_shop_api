package mycode.online_shop_api.app.Products.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateProductRequest (
        @NotNull
        String category,
        @NotNull
        String description,
        @NotNull
        String name,
        @NotNull
        double price,
        @NotNull
        int stock,
        @NotNull
        double weight){
}
