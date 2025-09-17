package kronos.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import kronos.exceptions.KronosException;
import kronos.tasklist.TaskList;
import kronos.tasks.ToDo;

/**
 * Tests for DeleteCommand.
 */
public class DeleteCommandTests {

    /**
     * Tests that a task is deleted successfully.
     */
    @Test
    public void deleteCommand_success() {

        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("run"));
        taskList.addTask(new ToDo("meditate"));

        DeleteCommand deleteCommand = new DeleteCommand(2);

        try {
            deleteCommand.execute(taskList);
            assertEquals(taskList.getAllTasks().size(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests that deleting a task with an invalid index throws an exception.
     */
    @Test
    public void deleteCommand_invalidIndex_exceptionThrown() {

        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("run"));
        taskList.addTask(new ToDo("meditate"));

        DeleteCommand deleteCommand = new DeleteCommand(5);

        assertThrows(KronosException.class, () -> {
            deleteCommand.execute(taskList);
        });

    }
}
