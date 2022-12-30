package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;

/**
 * client handler class
 */
public class ClientHandler extends ChannelInboundMessageHandlerAdapter<String> {
    Logger logger = LogManager.getLogger(ClientHandler.class.getName());

    /**
     * method for dealing with the received message
     * @param channelHandlerContext channel handler
     * @param s message
     */
    @Override
    public void messageReceived(ChannelHandlerContext channelHandlerContext, String s) {
        try {
            logger.info("MESSAGE FROM SERVER!\n");
            System.out.println("MESSAGE FROM SERVER!\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) {

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) {

    }
}
