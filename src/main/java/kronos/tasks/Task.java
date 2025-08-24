package kronos.tasks;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Instantiates a new task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the completion status of the task.
     * @param isDone The completion status to set.
     */
    public void setCompletionStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of the task.
     * @return The completion status of the task.
     */
    public String getCompletionStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getCompletionStatus(), description);
    }

    /**
     * Returns a CSV representation of the task.
     * @return The CSV representation of the task.
     */
    public String toDataString() {
        return String.format("%s,%s", description, (isDone ? "1" : "0"));
    }

}
