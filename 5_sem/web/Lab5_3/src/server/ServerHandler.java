package server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Server handler
 */
public class ServerHandler extends ChannelInboundMessageHandlerAdapter<String> {
    /**
     * Connected channels
     */
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * random
     */
    final Random random = new Random();
    //Logger logger = LogManager.getLogger(ServerHandler.class.getName());


    public ServerHandler() {
        new ReadMsg().start();
    }

    /**
     * Method for dealing with just joined client
     * @param ctx context
     * @throws Exception ex
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        File file = new File("index.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fr);
        int i = 0;
        if (br.readLine() != null) {
            while ((st = br.readLine()) != null) {
                i = Integer.parseInt(st.trim());
            }

            i++;
            bw.newLine();
            bw.write(String.valueOf(i));
        } else {
            bw.write(String.valueOf(i));
        }

        bw.close();
        br.close();
        fr.close();

        channels.add(ctx.channel());
    }

    /**
     * Method for dealing with just left client
     * @param ctx context
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.write("[Server] - " + incoming.remoteAddress() + " has left!\n");
        }
        channels.remove(ctx.channel());
    }

    /**
     * Method for dealing with just came msg
     * @param channelHandlerContext channel handler context
     * @param s msg
     */
    @Override
    public void messageReceived(ChannelHandlerContext channelHandlerContext, String s) {
        Channel incoming = channelHandlerContext.channel();
        for (Channel channel : channels) {
            if (channel != incoming) {
                channel.write("[" + incoming.remoteAddress() + "]" + s + "\n");
            }
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

    /**
     * class for sending messages to connected clients once in 2000 millis
     */
    private static class ReadMsg extends Thread {
        @Override
        public void run() {
            while (true) {
                Scanner sc = null;
                try {
                    sc = new Scanner(new File("index.txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while(true) {
                    assert sc != null;
                    if (!sc.hasNext()) break;
                    System.out.println(sc.nextLine());
                }
                System.out.println("Choose client index");
                sc = new Scanner(System.in);
                int clientIndex = sc.nextInt();
                int i = 0;
                System.out.println("Client index: " + clientIndex);

                for (Channel channel : channels) {
                    if (i == clientIndex) {
                        channel.write(  " New message from server!\n");
                        System.out.println("New message from server!\n");
                    }
                    i++;
                }
            }
        }
    }
}
