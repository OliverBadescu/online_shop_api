package mycode.online_shop_api.app.view;

import mycode.online_shop_api.app.Categories.repository.CategoryRepository;
import mycode.online_shop_api.app.Customers.dtos.CreateCustomerRequest;
import mycode.online_shop_api.app.Customers.model.Customer;
import mycode.online_shop_api.app.Customers.repository.CustomerRepository;
import mycode.online_shop_api.app.Customers.service.CustomerCommandService;
import mycode.online_shop_api.app.Customers.service.CustomerQueryService;
import mycode.online_shop_api.app.OrderDetails.dtos.CreateOrderDetailsRequest;
import mycode.online_shop_api.app.OrderDetails.repository.OrderDetailsRepository;
import mycode.online_shop_api.app.OrderDetails.service.OrderDetailsCommandService;
import mycode.online_shop_api.app.OrderDetails.service.OrderDetailsQueryService;
import mycode.online_shop_api.app.Orders.dtos.CreateOrderRequest;
import mycode.online_shop_api.app.Orders.model.Order;
import mycode.online_shop_api.app.Orders.repository.OrderRepository;
import mycode.online_shop_api.app.OrderDetails.model.OrderDetails;
import mycode.online_shop_api.app.Orders.service.OrderCommandService;
import mycode.online_shop_api.app.Orders.service.OrderQueryService;
import mycode.online_shop_api.app.ProductCategories.repository.ProductCategoriesRepository;
import mycode.online_shop_api.app.Products.repository.ProductRepository;
import mycode.online_shop_api.app.Products.model.Product;
import mycode.online_shop_api.app.Products.service.ProductCommandService;
import mycode.online_shop_api.app.Products.service.ProductQueryService;
import mycode.online_shop_api.app.utile.Cart;
import mycode.online_shop_api.app.utile.ProductDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class View {

    private CustomerCommandService customerCommandService;
    private CustomerQueryService customerQueryService;
    private ProductQueryService productQueryService;
    private OrderDetailsRepository orderDetailsRepository;
    private ProductRepository productRepository;
    private OrderCommandService orderCommandService;
    private OrderQueryService orderQueryService;
    private OrderDetailsCommandService orderDetailsCommandService;
    private OrderDetailsQueryService orderDetailsQueryService;
    private Scanner scanner;
    private Customer customer;
    private Cart cart;

    public View( OrderDetailsRepository orderDetailsRepository, ProductRepository productRepository, CustomerCommandService customerCommandService, CustomerQueryService customerQueryService, ProductQueryService productQueryService, OrderCommandService orderCommandService, OrderQueryService orderQueryService, OrderDetailsQueryService orderDetailsQueryService, OrderDetailsCommandService orderDetailsCommandService) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.productRepository = productRepository;
        this.customerCommandService = customerCommandService;
        this.customerQueryService = customerQueryService;
        this.productQueryService = productQueryService;
        this.orderCommandService = orderCommandService;
        this.orderQueryService = orderQueryService;
        this.orderDetailsCommandService = orderDetailsCommandService;
        this.orderDetailsQueryService = orderDetailsQueryService;

        this.scanner = new Scanner(System.in);
        playLogin();

    }

    private void loginMenu(){

        System.out.println("1. Login");
        System.out.println("2. Register");

        System.out.println("5. Exit");

    }

    private void playLogin(){


        boolean running = true;
        while (running) {
            loginMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice");

            }

        }
    }

    private void clientMenu(){
        System.out.println("1. Show products");
        System.out.println("2. Show products by price ascending");
        System.out.println("3. Show products by price descending");

        System.out.println("4. Add a product to the cart");
        System.out.println("5. Remove from cart");
        System.out.println("6. Show cart");

        System.out.println("7. Send order");
        System.out.println("8. Show orders ");
    }

    private void clientPlay(){
        boolean running = true;

        while(running ) {
            clientMenu();
            int alegere = Integer.parseInt(scanner.nextLine());

            switch (alegere) {
                case 1:
                    productQueryService.showProducts();
                    break;
                case 2:
                    productQueryService.showProductsSortedASC();
                    break;
                case 3:
                    productQueryService.showProductsSortedDESC();
                    break;
                case 4:
                    addToCart();
                    break;
                case 5:
                    removeFromCart();
                    break;
                case 6:
                    showCart();
                    break;
                case 7:
                    sendOrder();
                    break;
                case 8:
                    showOrders();
                    break;
            }

        }

    }


    private void login(){

        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        this.customer = customerQueryService.findByEmailAndPassword(email,password);
        this.cart = new Cart(this.customer.getId(), null);
        clientPlay();

    }

    private void register(){

        System.out.println("Full Name: ");
        String fullName = scanner.nextLine();

        System.out.println("Billing Address: ");
        String billingAddress = scanner.nextLine();

        System.out.println("Shipping Address: ");
        String shippingAddress = scanner.nextLine();

        System.out.println("Country: ");
        String country = scanner.nextLine();

        System.out.println("Email:");
        String email= scanner.nextLine();

        System.out.println("Password: ");
        String password = scanner.nextLine();

        System.out.println("Phone: ");
        String phone = scanner.nextLine();


        CreateCustomerRequest createCustomerRequest= new CreateCustomerRequest(fullName, email, password, billingAddress, shippingAddress, phone, country);

        customerCommandService.addCustomer(createCustomerRequest);


    }

    private void addToCart(){

        boolean running = true;
        ArrayList<ProductDto> list = new ArrayList<>();

        while(running){
            System.out.println("Enter the products name: ");
            String nume = scanner.nextLine();
            Product product = productQueryService.findByName(nume);


            System.out.println("Quantity: ");
            int q = Integer.parseInt(scanner.nextLine());
            ProductDto productDto = new ProductDto(product.getName(),q, product.getPrice());
            list.add(productDto);

            System.out.println("Would you like to add something else to the cart? (y/n)");
            String choice = scanner.nextLine();

            if(choice.equals("n")){
                running = false;
            }
        }
        cart.setProducts(list);
    }

    private void removeFromCart(){

        ArrayList<ProductDto> list = cart.getProducts();
        boolean found = false;

            System.out.println("Enter product name: ");
            String nume = scanner.nextLine();
            Product product = productQueryService.findByName(nume);


            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().equals(product.getName())) {
                    list.remove(list.get(i));
                    found = true;
                    System.out.println("Product deleted");
                }
            }
            if (!found) {
                System.out.println("Product not found in cart");
            }


        cart.setProducts(list);


    }

    private void showCart(){

        ArrayList<ProductDto> list = cart.getProducts();

        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getName() + " " + list.get(i).getPrice() + " "  +list.get(i).getCantitate());
            }
        }else{
            System.out.println("Cart is empty");
        }

    }

    private void sendOrder(){

        ArrayList<ProductDto> list = cart.getProducts();
        double amount = 0;

        if(list.isEmpty()){
            System.out.println("Cart is empty");
        }else{
            for(int i =0 ; i < list.size(); i++){
                amount += list.get(i).getPrice() * list.get(i).getCantitate();
            }
            System.out.println("Total is: " + amount);
            System.out.println("Send order? (y/n)");
            String choice = scanner.nextLine();
            if(choice.equals("y")){
                CreateOrderRequest createOrderRequest = new CreateOrderRequest(customer.getEmail(), customer.getBillingAddress(), customer.getShippingAddress(), LocalDate.now(), amount, "Preparing",customer);
                orderCommandService.addOrder(createOrderRequest, list);

                for (ProductDto productDto : list) {
                    Product product = productQueryService.findByName(productDto.getName());
                    product.setStock(product.getStock()-productDto.getCantitate());
                }
            }
        }
        cart.setProducts(null);

    }

    private void showOrders(){

        Set<Order> list = customer.getOrders();

        if(list.isEmpty()){
            System.out.println("No orders");
        }else{
            list.forEach(System.out::println);
        }

    }


}
