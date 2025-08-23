package kronos.commands;

import java.util.List;
import kronos.tasklist.TaskList;
import kronos.tasks.Task;

public class ListCommand implements Command {
    
    /**
     * Executes the list command. 
     * @param taskList
     * @return
     */
    @Override
    public String execute(TaskList taskList) {
            
        List<Task> tasks = taskList.getAllTasks();
        String listMessage = "Here are the tasks I've helped you store: \n";
        String storedText = listMessage;

        // Start a for loop to print out the stored text
        for (int index = 0; index < tasks.size(); ++index) {
            storedText += String.format("%d.", index + 1);
            storedText += tasks.get(index).toString() + "\n";
        }

        return storedText.substring(0, storedText.length() - 1);
    }

}