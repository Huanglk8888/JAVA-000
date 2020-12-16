import org.springframework.core.annotation.Order;

public interface OrderService {

    Order findOrderById(int id);

}
