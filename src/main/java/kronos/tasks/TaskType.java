package kronos.tasks;
public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    public static TaskType fromString(String keyword) {

        if (keyword.equals("todo")) {
            return TODO;
        } else if (keyword.equals("deadline")) {
            return DEADLINE;
        } else if (keyword.equals("event")) {
            return EVENT;
        } else {
            throw new IllegalArgumentException("Sir what do you mean by: " + keyword + "?");
        }

    }
}