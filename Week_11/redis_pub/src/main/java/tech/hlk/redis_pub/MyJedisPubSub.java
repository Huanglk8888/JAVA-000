package tech.jiangchen.redis_pub;

import redis.clients.jedis.JedisPubSub;


class MyJedisPubSub extends JedisPubSub {


    @Override
    public void onMessage(String channel, String message) {
        System.out.println(Thread.currentThread().getName() + "-接收到消息:channel=" + channel + ",message=" + message);
        //接收到exit消息后退出
        if (MessageConsumer.EXIT_COMMAND.equals(message)) {
            System.exit(0);
        }
    }
}