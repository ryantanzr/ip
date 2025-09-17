package kronos.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import kronos.tasklist.TaskList;
import kronos.tasks.ToDo;

/**
 * Tests for ListCommand.
 */
public class ListCommandTests {

    /**
     * Tests that listing tasks works successfully.
     */
    @Test
    public void listCommand_success() {

        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("run"));
        taskList.addTask(new ToDo("meditate"));

        ListCommand listCommand = new ListCommand();
        String outputString = listCommand.execute(taskList);

        assertTrue(outputString.length() > 0);
    }

}
