package kronos.commands;
import kronos.tasklist.TaskList;

/**
 * Represents an abstract command that Kronos can execute.
 */
public interface Command {
    public String execute(TaskList taskList);
}
