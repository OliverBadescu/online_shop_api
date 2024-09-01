package mycode.online_shop_api.app.view;

import mycode.online_shop_api.app.model.Customer;
import mycode.online_shop_api.app.model.Order;
import mycode.online_shop_api.app.model.OrderDetails;
import mycode.online_shop_api.app.model.Product;
import mycode.online_shop_api.app.repository.*;
import mycode.online_shop_api.app.utile.Cart;
import mycode.online_shop_api.app.utile.ProductDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class View {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private ProductCategoriesRepository productCategoriesRepository;
    private ProductRepository productRepository;
    private Scanner scanner;
    private Customer customer;
    private Cart cart;

    public View(CategoryRepository categoryRepository, CustomerRepository customerRepository, OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, ProductCategoriesRepository productCategoriesRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.productCategoriesRepository = productCategoriesRepository;
        this.productRepository = productRepository;

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
                    showProducts();
                    break;
                case 2:
                    sortedASC();
                    break;
                case 3:
                    sortedDESC();
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

        Optional<Customer> customer = customerRepository.findByEmailAndPassword(email,password);

        if(customer.isPresent()){
            this.customer = customer.get();
            this.cart = new Cart(this.customer.getId(), null);
            clientPlay();
        }else {
            System.out.println("Login failed");
        }

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

        String email;
        while (true) {
            System.out.println("Email: ");
            email = scanner.nextLine();

            if (customerRepository.existsByEmail(email)) {
                System.out.println("This email is already in use");
            } else {
                break;
            }
        }

        System.out.println("Password: ");
        String password = scanner.nextLine();

        System.out.println("Phone: ");
        String phone = scanner.nextLine();

        Customer customer = Customer.builder()
                .fullName(fullName)
                .billingAddress(billingAddress)
                .shippingAddress(shippingAddress)
                .country(country)
                .email(email)
                .password(password)
                .phone(phone)
                .build();

        customerRepository.saveAndFlush(customer);


    }

    private void showProducts(){

        List<Product> list = productRepository.findAll();

        list.forEach(System.out::println);

    }

    private void sortedASC(){

        Optional<List<Product>> list = productRepository.sortedAsc();

        list.get().forEach(System.out::println);

    }

    private void sortedDESC(){

        Optional<List<Product>> list = productRepository.sortedDesc();

        list.get().forEach(System.out::println);

    }

    private void addToCart(){

        boolean running = true;
        ArrayList<ProductDto> list = new ArrayList<>();

        while(running){
            System.out.println("Enter the products name: ");
            String nume = scanner.nextLine();
            Optional<Product> product = productRepository.findByName(nume);

            if(product.isPresent()){
                System.out.println("Quantity: ");
                int q = Integer.parseInt(scanner.nextLine());
                ProductDto productDto = new ProductDto(product.get().getName(),q, product.get().getPrice());
                list.add(productDto);
            }else{
                System.out.println("Product not found");
            }

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
            Optional<Product> product = productRepository.findByName(nume);


            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().equals(product.get().getName())) {
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
                Order order = Order.builder().orderAddress(customer.getShippingAddress()).orderDate(LocalDate.now()).orderEmail(customer.getEmail()).orderStatus("Preparing").amount(amount).shippingAddress(customer.getShippingAddress()).customer(customer).build();
                orderRepository.saveAndFlush(order);

                for (ProductDto productDto : list) {
                    OrderDetails orderDetails =OrderDetails.builder().order(order).product(productRepository.findByName(productDto.getName()).get()).price(productDto.getPrice()).quantity(productDto.getCantitate()).build();
                    orderDetailsRepository.saveAndFlush(orderDetails);
                    Product product = orderDetails.getProduct();
                    product.setStock(product.getStock()-orderDetails.getQuantity());
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
