package kronos.tasks;

import kronos.exceptions.KronosException;

/**
 * Represents the different types of tasks.
 */
public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    /**
     * Converts a string keyword to its corresponding TaskType.
     * @param keyword The string representation of the task type.
     * @return The TaskType corresponding to the keyword.
     * @throws KronosException If the keyword does not match any TaskType.
     */
    public static TaskType fromString(String keyword) throws KronosException {

        if (keyword.equals("todo")) {
            return TODO;
        } else if (keyword.equals("deadline")) {
            return DEADLINE;
        } else if (keyword.equals("event")) {
            return EVENT;
        } else {
            throw new KronosException("Sir what do you mean by: " + keyword + "?");
        }

    }
}
