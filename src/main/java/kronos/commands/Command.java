package kronos.commands;

import kronos.tasklist.TaskList;

/**
 * Represents an abstract command that Kronos can execute.
 */
public interface Command {

    /**
     * Executes logic related to handling tasks.
     * @param taskList The task list to operate on.
     * @return A message indicating the result of the command execution.
     */
    public String execute(TaskList taskList);
}
