package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import util.MessageUtil;

public class Server {
    public static void main(String[] args) {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(parentGroup,childGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new ServerInitialzer());
            ChannelFuture sync = serverBootstrap.bind(10100).sync();
            Channel channel = sync.channel();
            while (!MessageUtil.sendqueue.isEmpty()){
                channel.writeAndFlush(MessageUtil.sendqueue.poll());
            }
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }

    }
}
