package kronos.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import kronos.tasklist.TaskList;
import kronos.tasks.ToDo;

public class DeleteCommandTest {

    @Test
    public void validDeletionTest() {

        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("run"));
        taskList.addTask(new ToDo("meditate"));

        DeleteCommand deleteCommand = new DeleteCommand(2);

        deleteCommand.execute(taskList);

        assertEquals(taskList.getAllTasks().size(), 2);
    }

    @Test
    public void invalidDeletionTest() {

        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("run"));
        taskList.addTask(new ToDo("meditate"));

        DeleteCommand deleteCommand = new DeleteCommand(5);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            deleteCommand.execute(taskList);
        });

    }
}
