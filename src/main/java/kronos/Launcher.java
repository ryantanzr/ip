import javafx.application.Application;
import kronos.Main;

/**
 * Represents a launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the JavaFX application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
