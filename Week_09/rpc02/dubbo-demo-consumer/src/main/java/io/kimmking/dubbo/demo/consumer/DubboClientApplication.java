import Order;
import OrderService;
import User;
import UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DubboClientApplication {

    @DubboReference(version = "1.0.0", url = "dubbo://localhost:12345")
    private UserService userService;

    @DubboReference(version = "1.0.0", url = "dubbo://localhost:12345")
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(DubboClientApplication.class).close();
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            User user = userService.findById(1);
            System.out.println("find user id=1 from server: " + user.getName());
            Order order = orderService.findOrderById(123456);
            System.out.println(String.format("find order name=%s, amount=%f", order.getName(), order.getAmount()));
        };
    }

}
