package kronos.commands;

import kronos.tasklist.TaskList;

public class MarkCommand implements Command {

    private final int taskNumber;

    public MarkCommand(int taskNumber) {
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

        String responseString = "Marking that task as completed: \n";

        // Mark task as incomplete
        taskList.getTask(taskNumber).setCompletionStatus(true);

        // Extract task details
        String taskDescription = taskList.getTask(taskNumber).getDescription();
        String taskStatus = taskList.getTask(taskNumber).getCompletionStatus();

        // Return a formatted string showing the new status and description
        return responseString + String.format("  [%s] %s ", taskStatus, taskDescription);

    }
}
