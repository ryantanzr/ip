package kronos.tasklist;
import java.util.ArrayList;
import java.util.List;

import kronos.tasks.Task;


/**
 *  Represents the list of tasks in Kronos.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Instantiates a new TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Sets the tasks in the list.
     * @param tasks The tasks to set.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Removes a task from the list.
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets a task from the list.
     * @param index The index of the task to get.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets all tasks in the list.
     * @return A list of all tasks.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }
}
