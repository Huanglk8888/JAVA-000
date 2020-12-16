import org.springframework.boot.autoconfigure.security.SecurityProperties;

public interface UserService {

    SecurityProperties.User findById(int id);

}
