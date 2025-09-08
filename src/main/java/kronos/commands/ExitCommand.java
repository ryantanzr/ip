package kronos.commands;

import kronos.tasklist.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand implements Command {

    private static final String EXIT_MESSAGE = "Till next time...";

    /**
     * Executes the exit command.
     */
    @Override
    public String execute(TaskList taskList) {
        return EXIT_MESSAGE;
    }

}
