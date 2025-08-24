package kronos.commands;

import kronos.tasklist.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command.
     */
    @Override
    public String execute(TaskList taskList) {
        return "Till next time...";
    }

}
