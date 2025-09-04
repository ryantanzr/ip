package kronos;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kronos.ui.components.MainWindow;

/**
 * Represents the main entry point for the Kronos chatbot application.
 */
public class Main extends Application {

    private String resourceLocation = "/view/MainWindow.fxml";

    /**
     * Starts the JavaFX application by initialising
     * Kronos and the nodes of the scene.
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(resourceLocation));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow window = fxmlLoader.<MainWindow>getController();
            Kronos kronos = new Kronos(window);
            window.setKronos(kronos);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
