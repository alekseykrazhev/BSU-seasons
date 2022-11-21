package Model;

import java.io.*;

public class Client {

    public static String ipAddr = "localhost";
    public static int port = 8080;

    public static void main(String[] args) throws IOException {
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

        new ClientHelper(ipAddr, port);
    }
}