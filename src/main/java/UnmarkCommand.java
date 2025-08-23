public class UnmarkCommand implements Command {

    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Changes the completion status of a task
     * 
     * @param taskNumber Where the task is stored in the storage array
     * @return A formatted string showing the new status and task description
     */
    @Override
    public String execute(TaskList taskList) {

        String responseString = "Marking that task as incomplete: \n";

        // Mark task as incomplete
        taskList.getTask(taskNumber).setCompletionStatus(false);

        // Extract task details
        String taskDescription = taskList.getTask(taskNumber).getDescription();
        String taskStatus = taskList.getTask(taskNumber).getCompletionStatus();

        // Return a formatted string showing the new status and description
        return responseString + String.format("  [%s] %s ", taskStatus, taskDescription);

    }
}