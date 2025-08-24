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

    /**
     * Starts the Kronos application.
     */
    public void run() {

        boolean isExiting = false;
        String greetingMessage = "You've invoked the timekeeper Kronos\n" + "How may I assist you today?";

        ui.showMessage(greetingMessage);
        taskList.setTasks(storage.loadTasks());

        while (!isExiting) {

            String message = ui.getUserInput();
            String responseString = "";

            try {

                Command command = parser.parse(message);
                responseString = command.execute(taskList);

                if (responseString.equals("Till next time...")) {
                    storage.saveTasks(taskList.getAllTasks());
                    isExiting = true;
                }

                ui.showMessage(responseString);

            } catch (Exception e) {
                ui.showMessage("Error: " + e.getMessage());
            }
        }

    }

    /**
     * Represents the entry point for the Kronos application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        Kronos kronos = new Kronos();
        kronos.run();

    }
}
