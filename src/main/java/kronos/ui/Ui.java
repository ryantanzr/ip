package kronos.ui;

import kronos.ui.components.MainWindow;

/**
 * Handles user interactions and displays information to the user.
 */
public class Ui {

    private MainWindow mainWindow;

    /**
     * Displays a message to the user with top and bottom dividers.
     */
    public void showResponse(String message) {
        mainWindow.showResponse(message);
    }

    /**
     * Sets the main window for the UI.
     * @param mainWindow The main window to set.
     */
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

}
