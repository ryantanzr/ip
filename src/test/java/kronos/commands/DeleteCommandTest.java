package kronos.commands;

import org.junit.jupiter.api.Test;
import kronos.tasklist.TaskList;
import kronos.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {

    @Test
    public void ValidDeletionTest() {

        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("run"));
        taskList.addTask(new ToDo("meditate"));

        DeleteCommand deleteCommand = new DeleteCommand(2);
        
        deleteCommand.execute(taskList);

        assertEquals(taskList.getAllTasks().size(), 2);
    }
    
    @Test
    public void InvalidDeletionTest() {

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
