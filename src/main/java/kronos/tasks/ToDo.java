package kronos.tasks;

/**
 * Represents a to-do task with a description.
 */
public class ToDo extends Task {

    /**
     * Instantiates a new to-do task.
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Returns a CSV representation of the to-do task.
     */
    @Override
    public String toDataString() {
        return String.format("TD,%s", super.toDataString());
    }

}
