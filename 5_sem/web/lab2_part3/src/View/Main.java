package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 *  Class that starts all the work with bank application.
 *  Creates Bank instance and starts main menu.
 * @author Aleksey Krazhevskiy
 * @version 1.0
 */
public class Main extends Application {
    /**
     * Main method that starts all the work of the program.
     * @param args - console arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI.fxml")));
        stage.setTitle("Bank App");
        stage.setScene(new Scene(root));
        stage.show();
    }
}