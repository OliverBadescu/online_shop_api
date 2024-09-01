package mycode.online_shop_api.app.utile;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private String name;
    private int cantitate;
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
