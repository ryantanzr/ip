package kronos.commands;

import org.junit.jupiter.api.Test;
import kronos.tasklist.TaskList;
import kronos.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {

    @Test
    public void ListTest() {

        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("run"));
        taskList.addTask(new ToDo("meditate"));

        ListCommand listCommand = new ListCommand();
        String expectedOutput = "Here are the tasks I've helped you store: \n"
                + "1.[T] [ ] read book\n"
                + "2.[T] [ ] run\n"
                + "3.[T] [ ] meditate";
        String outputString = listCommand.execute(taskList);

        assertEquals(expectedOutput, outputString);
    }
    
}
