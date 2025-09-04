package kronos;

import kronos.commands.Command;
import kronos.parser.Parser;
import kronos.storage.Storage;
import kronos.tasklist.TaskList;
import kronos.ui.Ui;

/**
 * Represents the main entry point for the Kronos chatbot application.
 */
public class Kronos {

    private TaskList taskList = new TaskList();
    private Storage storage = new Storage();
    private Parser parser = new Parser();
    private Ui ui = new Ui();

    private boolean isExiting = false;

    /**
     * Initializes the Kronos application with a handle
     * to manage the main window.
     * @param mainWindow The main window to interact with.
     */
    public Kronos(MainWindow mainWindow) {
        this.ui.setMainWindow(mainWindow);

        try {

            String greetingMessage = "You've invoked the timekeeper Kronos\n" + "How may I assist you today?";

            ui.showResponse(greetingMessage);
            taskList.setTasks(storage.loadTasks());

        } catch (Exception e) {
            ui.showResponse("Error: " + e.getMessage());
        }
    }

    /**
     * Saves the current tasks to storage.
     */
    public void saveTasks() {
        storage.saveTasks(taskList.getAllTasks());
    }

    /**
     * Handles a user message by parsing and executing the corresponding command.
     * @param message The user message to handle.
     */
    public void handleMessage(String message) {

        String responseString = "";

        try {

            Command command = parser.parse(message);
            responseString = command.execute(taskList);
            ui.showResponse(responseString);

            if (responseString.equals("Till next time...")) {
                isExiting = true;
            }

        } catch (Exception e) {
            ui.showResponse("Error: " + e.getMessage());
        }
    }

    /**
     * Checks if the application is set to exit.
     * @return True if the application should exit, false otherwise.
     */
    public boolean getIsExiting() {
        return isExiting;
    }
}
