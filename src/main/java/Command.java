
/**
 * A representation of a command that Kronos can execute.
 */
public interface Command {
    public String execute(TaskList taskList);
}
