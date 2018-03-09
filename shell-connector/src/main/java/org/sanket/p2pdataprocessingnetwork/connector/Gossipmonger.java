package org.sanket.p2pdataprocessingnetwork.connector;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.logging.LoggingHandler;
import org.sanket.p2pdataprocessingnetwork.common.connector.Server;

public class Gossipmonger implements Server {

    private final EventLoopGroup _bossGroup;
    private final int _listenerPort;

    public Gossipmonger(int listenerPort){
        _bossGroup = new NioEventLoopGroup();
        _listenerPort = listenerPort;
    }

    @Override
    public void startServer() throws Exception {
        Bootstrap b = new Bootstrap();
        b.group(_bossGroup)
        .channel(NioDatagramChannel.class)
        .handler(new ChannelInitializer<NioDatagramChannel>() {
            @Override
            protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {
                ChannelPipeline pipeline = nioDatagramChannel.pipeline();
                pipeline.addLast(new LoggingHandler());
                pipeline.addLast()
            }
        });
        try {
            b.bind(_listenerPort).sync().channel().closeFuture().await();
        } catch (InterruptedException e) {
            throw e;
        }
    }

    @Override
    public void shutdownServer() {
        _bossGroup.shutdownGracefully();
    }

    @Override
    public boolean isRunning() {
        return (!_bossGroup.isShutdown() &&
                !_bossGroup.isTerminated() &&
                !_bossGroup.isShuttingDown());
     }
}
