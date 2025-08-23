package kronos.commands;

import kronos.tasklist.TaskList;

public class ExitCommand implements Command {
   
    @Override
    public String execute(TaskList taskList) {
        return "Till next time...";
    }
    
}
