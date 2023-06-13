package Model;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {

    public static final int PORT = 8080;
    public static LinkedList<ServerHelper> serverList = new LinkedList<>();
//    static Logger logger = LogManager.getLogger(Server.class.getName());

    public static void main(String[] args) throws IOException {
        //        logger.info("Server listened on port 8080");
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerHelper(socket));
                } catch (IOException e) {
//                    logger.error(e);
                    socket.close();
                }
            }
        }
    }
}