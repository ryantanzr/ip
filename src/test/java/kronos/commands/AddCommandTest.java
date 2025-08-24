package kronos.commands;

import org.junit.jupiter.api.Test;
import kronos.tasklist.TaskList;
import kronos.tasks.TaskType;
import kronos.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCommandTest {

    @Test
    public void AddValidTaskTest() {

        TaskList taskList = new TaskList();
        AddCommand addCommand = new AddCommand(TaskType.TODO, "todo Run");

        addCommand.execute(taskList);
        
        assertTrue(taskList.getAllTasks().get(0) instanceof ToDo);
    }

    @Test
    public void AddInvalidTodoTest() {
        
        TaskList taskList = new TaskList();
        AddCommand addCommand = new AddCommand(TaskType.TODO, "Run");
        
        assertThrows(NullPointerException.class, () -> {
            addCommand.execute(taskList);
        });

    }

    @Test
    public void AddInvalidDeadlineTest() {
        
        TaskList taskList = new TaskList();
        AddCommand addCommand = new AddCommand(TaskType.DEADLINE, "Run /by");
        
        assertThrows(NullPointerException.class, () -> {
            addCommand.execute(taskList);
        });

    }
    
}
