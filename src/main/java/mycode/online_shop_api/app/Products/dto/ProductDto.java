package mycode.online_shop_api.app.Products.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mycode.online_shop_api.app.Products.validator.NameConstraint;
import mycode.online_shop_api.app.Products.validator.PriceConstraint;
import mycode.online_shop_api.app.Products.validator.QuantityConstraint;

@Getter
@Setter
@Builder
public class ProductDto {

    @NameConstraint(message = "Numele trebuie sa contina doar litere mari")
    private String name;
    @QuantityConstraint(message = "Cantitatea trebuie sa fie mai mare decat 0")
    private int cantitate;
    @PriceConstraint(message = "Pretul trebuie sa fie pozitiv")
    private double price;


    public ProductDto(String name, int cantitate, double price) {

        this.name = name;
        this.cantitate = cantitate;
        this.price = price;
    }


    public String description(){

        String text = "";

        text += "Name: " + name + "\n";
        text += "Cantitate: " + cantitate + "\n";
        text += "Price: " + price + "\n";
        return text;
    }



}
