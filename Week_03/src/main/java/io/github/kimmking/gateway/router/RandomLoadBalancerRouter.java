package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.Random;

/**
 * 随机访问路由
 *
 * @author huanglk
 */
public class RandomLoadBalancerRouter implements HttpEndpointRouter {

    @Override
    public String route(List<String> endpoints) {
        int size = endpoints.size();
        int nextInt = new Random().nextInt(size * 100);
        return endpoints.get(nextInt % size);
    }
}
