import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CodeGetter {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        String login = "", line = "";
        JTextField loginField = new JTextField(10);

        final JComponent[] inputs = new JComponent[]{
                new JLabel("Enter Login"),
                loginField
        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "Login dialog", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            login = loginField.getText();

            FileReader fileReader = new FileReader("ServerPart/src/" + login + ".txt");
            BufferedReader br = new BufferedReader(fileReader);
            line = br.readLine();
            System.out.println("Your 4 digit number: " + line);

        } else {
            System.out.println("User canceled or closed the dialog");
        }

        if (line == null || line.equals("")){
            int result2 = JOptionPane.showConfirmDialog(null, "No such user, try again later",
                    "Code", JOptionPane.PLAIN_MESSAGE);
            if (result2 == JOptionPane.OK_OPTION){}
        }
        else {
            int result2 = JOptionPane.showConfirmDialog(null, "Your 4 digit number: " + line,
                    "Code", JOptionPane.PLAIN_MESSAGE);
            if (result2 == JOptionPane.OK_OPTION){}
        }


    }
}
