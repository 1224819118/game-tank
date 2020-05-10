package client;

import club.caohao.game.AppKt;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import server.ServerInitialzer;
import util.ClientMessageUtil;
import util.MessageUtil;

public class Client {
    public static void main(String[] args) {

        EventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(childGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new ClientInitialzer());
            ChannelFuture sync = bootstrap.connect("127.0.0.1",10100).sync();
            Channel channel = sync.channel();
            while (!MessageUtil.sendqueue.isEmpty()){
                channel.writeAndFlush(ClientMessageUtil.sendqueue.poll());
            }
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            childGroup.shutdownGracefully();
        }
    }
}
