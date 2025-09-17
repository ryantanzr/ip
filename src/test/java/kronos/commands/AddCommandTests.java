package kronos.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import kronos.exceptions.KronosException;
import kronos.tasklist.TaskList;
import kronos.tasks.TaskType;
import kronos.tasks.ToDo;

/**
 * Tests for AddCommand.
 */
public class AddCommandTests {

    /**
     * Tests that a task is added successfully.
     */
    @Test
    public void addCommand_success() {

        TaskList taskList = new TaskList();
        AddCommand addCommand = new AddCommand(TaskType.TODO, "todo Run");

        try {
            addCommand.execute(taskList);
            assertTrue(taskList.getAllTasks().get(0) instanceof ToDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests that an invalid task type throws an exception.
     */
    @Test
    public void addCommand_invalidTaskType_exceptionThrown() {

        TaskList taskList = new TaskList();
        AddCommand addCommand = new AddCommand(TaskType.TODO, "Run");

        assertThrows(KronosException.class, () -> {
            addCommand.execute(taskList);
        });

    }

    /**
     * Tests that adding a deadline without a date throws an exception.
     */
    @Test
    public void addCommand_missingDeadlineDate_exceptionThrown() {

        TaskList taskList = new TaskList();
        AddCommand addCommand = new AddCommand(TaskType.DEADLINE, "Run /by");

        assertThrows(KronosException.class, () -> {
            addCommand.execute(taskList);
        });

    }

}
