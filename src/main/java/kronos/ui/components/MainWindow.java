package kronos.ui.components;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kronos.Kronos;

/**
 * Represents the main window component of the Kronos chatbot application.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Kronos kronos;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    private Image kronosImage = new Image(this.getClass().getResourceAsStream("/images/kronos.png"));

    /**
     * Initializes the main window and binds the scroll pane to the dialog container height.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Kronos instance */
    public void setKronos(Kronos k) {
        kronos = k;
    }

    /**
     * Displays an error message from Kronos in the dialog container.
     */
    public void showError(String errorMessage) {
        dialogContainer.getChildren().add(
                DialogBox.getKronosErrorResponse(errorMessage, kronosImage)
        );
    }

    /**
     * Displays the response from Kronos in the dialog container.
     */
    public void showResponse(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getKronosResponse(message, kronosImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        kronos.handleMessage(input);

        if (kronos.getIsExiting()) {
            handleQuit();
            Platform.exit();
        }

        userInput.clear();
    }

    /**
     * Handles the quit action by saving tasks before exiting.
     */
    @FXML
    private void handleQuit() {
        kronos.saveTasks();
    }

}
