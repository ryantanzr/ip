package kronos.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import kronos.tasklist.TaskList;
import kronos.tasks.TaskType;
import kronos.tasks.ToDo;



public class AddCommandTest {

    @Test
    public void addValidTaskTest() {

        TaskList taskList = new TaskList();
        AddCommand addCommand = new AddCommand(TaskType.TODO, "todo Run");

        addCommand.execute(taskList);

        assertTrue(taskList.getAllTasks().get(0) instanceof ToDo);
    }

    @Test
    public void addInvalidTodoTest() {

        TaskList taskList = new TaskList();
        AddCommand addCommand = new AddCommand(TaskType.TODO, "Run");

        assertThrows(NullPointerException.class, () -> {
            addCommand.execute(taskList);
        });

    }

    @Test
    public void addInvalidDeadlineTest() {

        TaskList taskList = new TaskList();
        AddCommand addCommand = new AddCommand(TaskType.DEADLINE, "Run /by");

        assertThrows(NullPointerException.class, () -> {
            addCommand.execute(taskList);
        });

    }

}
