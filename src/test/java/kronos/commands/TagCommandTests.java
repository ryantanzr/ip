package kronos.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import kronos.exceptions.KronosException;
import kronos.tasklist.TaskList;
import kronos.tasks.ToDo;

/**
 * Tests for TagCommand.
 */
public class TagCommandTests {

    /**
    * Sets up the task list with some sample tasks.
    */
    private TaskList setUpTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("run"));
        taskList.addTask(new ToDo("meditate"));
        return taskList;
    }

    /**
     * Tests for successful tagging of a task.
     */
    @Test
    public void tagCommand_success() {

        var taskList = setUpTaskList();

        TagCommand tagCommand = new TagCommand("tag 1 #urgent", 1);

        try {
            tagCommand.execute(taskList);
            assert taskList.getTask(1).getTags().contains("#urgent");
        } catch (Exception e) {
            assert false; // This should not happen
        }

    }

    /**
     * Tests for tagging a task with multiple tags.
     */
    @Test
    public void tagCommand_multipleTags() {

        var taskList = setUpTaskList();

        TagCommand tagCommand = new TagCommand("tag 1 #urgent #important", 1);

        try {
            tagCommand.execute(taskList);
            assert taskList.getTask(1).getTags().contains("#urgent");
            assert taskList.getTask(1).getTags().contains("#important");
        } catch (Exception e) {
            assert false; // This should not happen
        }
    }

    /**
     * Tests for tagging a task with invalid tag format.
     */
    @Test
    public void tagCommand_invalidTagFormat_exceptionThrown() {

        var taskList = setUpTaskList();

        TagCommand tagCommand = new TagCommand("tag 1 #urgent123 #important", 1);

        assertThrows(KronosException.class, () -> {
            tagCommand.execute(taskList);
        });
    }

    /**
     * Tests for tagging a task exceeding the maximum number of tags.
     */
    @Test
    public void tagCommand_exceedMaxTags_exceptionThrown() {

        var taskList = setUpTaskList();

        TagCommand tagCommand = new TagCommand("tag 1 #one #two #three #fail", 1);

        assertThrows(KronosException.class, () -> {
            tagCommand.execute(taskList);
        });
    }

}
