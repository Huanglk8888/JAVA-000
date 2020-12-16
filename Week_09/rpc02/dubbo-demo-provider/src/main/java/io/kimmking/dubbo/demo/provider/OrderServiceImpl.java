import Order;
import OrderService;
import org.apache.dubbo.config.annotation.DubboService;


@DubboService(version = "1.0.0")
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "bbc" + System.currentTimeMillis(), 9.9f);
    }
}
