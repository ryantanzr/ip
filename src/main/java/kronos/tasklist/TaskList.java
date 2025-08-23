package kronos.tasklist;
import java.util.ArrayList;
import java.util.List;

import kronos.tasks.Task;


/**
 * TaskList represents the list of tasks in Kronos.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}
