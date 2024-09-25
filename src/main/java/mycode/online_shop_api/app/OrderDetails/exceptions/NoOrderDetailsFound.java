package mycode.online_shop_api.app.OrderDetails.exceptions;

public class NoOrderDetailsFound extends RuntimeException {
  public NoOrderDetailsFound(String message) {
    super(message);
  }
}
