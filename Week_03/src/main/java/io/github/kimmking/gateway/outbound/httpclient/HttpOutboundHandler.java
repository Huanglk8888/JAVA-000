package io.github.kimmking.gateway.outbound.httpclient;

import io.github.kimmking.gateway.outbound.netty4.NettyHttpClientOutboundHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

import java.net.URI;

/**
 * 使用netty实现后端http访问
 * @author huanglk
 */
public class HttpOutboundHandler {


    public String execute(final FullHttpRequest fullRequest) throws Exception {
        NettyHttpClientOutboundHandler clientHandler = new NettyHttpClientOutboundHandler();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);

            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(clientHandler);
                }
            });

            URI uri = new URI(fullRequest.uri());
            ChannelFuture f = bootstrap.connect(uri.getHost(), uri.getPort()).sync();
            f.channel().writeAndFlush(fullRequest);
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
        return clientHandler.getData();
    }


}
