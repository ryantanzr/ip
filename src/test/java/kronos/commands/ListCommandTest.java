package kronos.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import kronos.tasklist.TaskList;
import kronos.tasks.ToDo;

public class ListCommandTest {

    @Test
    public void listTest() {

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
