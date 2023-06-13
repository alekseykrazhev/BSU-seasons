package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class represents chat client
 */
public class Client {

    public static void main(String[] args) {
        new Client("localhost", 9000).run();
    }

    /**
     * host
     */
    private final String host;

    /**
     * port
     */
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Method for starting client configuration
     */
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());
            Channel channel = bootstrap.connect(host, port).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                channel.write(in.readLine() + "\r\n");
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}

