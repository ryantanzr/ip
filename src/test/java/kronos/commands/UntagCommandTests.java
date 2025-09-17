package kronos.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import kronos.exceptions.KronosException;
import kronos.tasklist.TaskList;
import kronos.tasks.ToDo;

/**
 * Tests for UntagCommand.
 */
public class UntagCommandTests {

    /**
    * Sets up the task list with some sample tasks.
    * @throws KronosException If the tagging fails.
    */
    private TaskList setUpTaskList() throws KronosException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("run"));
        TagCommand tagCommand = new TagCommand("tag 1 #urgent #important", 1);
        tagCommand.execute(taskList);
        return taskList;
    }

    /**
     * Tests for successful untagging of a task.
     */
    @Test
    public void untagCommand_success() {

        try {
            var taskList = setUpTaskList();

            UntagCommand untagCommand = new UntagCommand("#urgent", 1);
            untagCommand.execute(taskList);

            assert !taskList.getTask(1).getTags().contains("#urgent");

        } catch (Exception e) {
            assert false; // This should not happen
        }
    }

    /**
     * Tests for untagging all tags from a task.
     */
    @Test
    public void untagCommand_allTags() {
        try {
            var taskList = setUpTaskList();

            UntagCommand untagCommand = new UntagCommand("#ALL", 1);
            untagCommand.execute(taskList);

            assert taskList.getTask(1).getTags().isEmpty();

        } catch (Exception e) {
            assert false; // This should not happen
        }
    }

    /**
     * Tests for untagging a non-existent tag from a task.
     */
    @Test
    public void untagCommand_nonExistentTag_exceptionThrown() {
        try {
            var taskList = setUpTaskList();

            UntagCommand untagCommand = new UntagCommand("#nonExistentTag", 1);

            assertThrows(KronosException.class, () -> {
                untagCommand.execute(taskList);
            });

        } catch (Exception e) {
            assert false; // This should not happen
        }
    }

    /**
     * Tests for untagging a task with an invalid task number.
     */
    @Test
    public void untagCommand_invalidTaskNumber_exceptionThrown() {
        try {
            var taskList = setUpTaskList();

            UntagCommand untagCommand = new UntagCommand("#thisShouldBeOuttaBounds", 3);

            assertThrows(KronosException.class, () -> {
                untagCommand.execute(taskList);
            });
        } catch (Exception e) {
            assert false; // This should not happen
        }
    }

    /**
     * Tests for untagging all tasks from an untagged task.
     */
    @Test
    public void untagCommand_allTagsFromUntaggedTask_success() {
        try {
            var taskList = setUpTaskList();

            UntagCommand untagCommand = new UntagCommand("#ALL", 0);
            untagCommand.execute(taskList);

        } catch (Exception e) {
            assert false; // This should not happen
        }
    }
}
