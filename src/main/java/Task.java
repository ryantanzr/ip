public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setCompletionStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String getCompletionStatus() {
        return (isDone ? "X" : " ");
    }

}
