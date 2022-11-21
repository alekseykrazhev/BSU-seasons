package Model;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class ServerHelper extends Thread {
    private final Socket socket;

    private final BufferedReader in;

    private final BufferedWriter out;
//    static Logger logger = LogManager.getLogger(ServerHelper.class.getName());

    public ServerHelper(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String word;
        try {
            word = in.readLine();
            try {
                out.write(word + "\n");
                out.flush();
            } catch (IOException e) {
//                logger.error(e);
            }
            try {
                while (true) {
                    Scanner sc = new Scanner(new File("index.txt"));
                    while(sc.hasNext()) {
                        System.out.println(sc.nextLine());
                    }
                    System.out.println("Choose client index");
                    sc = new Scanner(System.in);
                    int clientIndex = sc.nextInt();
                    Server.serverList.get(clientIndex).send();
                }
            } catch (NullPointerException e) {
//                logger.error(e);
            }
        } catch (IOException e) {
//            logger.error(e);
            this.downService();
        }
    }

    private void send() {
        try {
            out.write("New message from server!" + "\n");
            out.flush();
        } catch (IOException e) {
//            logger.error(e);
        }
    }

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (ServerHelper vr : Server.serverList) {
                    if (vr.equals(this)) vr.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException e) {
//            logger.error(e);
        }
    }
}
