package org.sanket.p2pdataprocessingnetwork.connector;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.sanket.p2pdataprocessingnetwork.common.connector.Server;

public class Gossipmonger implements Server {

    private final EventLoopGroup _bossGroup;
    private final int _port;

    public Gossipmonger(int port){
        _bossGroup = new NioEventLoopGroup();
        _port = port;
    }

    @Override
    public void startServer() {
        Bootstrap b = new Bootstrap();
        b.group(_bossGroup)
        .channel(NioDatagramChannel.class)
                .handler(new ChannelInitializer<NioDatagramChannel>() {
                    @Override
                    protected void initChannel(NioDatagramChannel nioDatagramChannel) throws Exception {

                    }
                });

    }

    @Override
    public void shutdownServer() {
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
