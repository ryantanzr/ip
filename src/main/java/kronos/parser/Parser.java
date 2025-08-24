package kronos.parser;

import kronos.commands.AddCommand;
import kronos.commands.Command;
import kronos.commands.DeleteCommand;
import kronos.commands.ExitCommand;
import kronos.commands.FindCommand;
import kronos.commands.ListCommand;
import kronos.commands.MarkCommand;
import kronos.commands.UnmarkCommand;
import kronos.tasks.TaskType;

/**
 * Parses user input and extracting relevant information.
 * And returning an appropriate enum which represents the next action to
 * take.
 */
public class Parser {

    /**
     * Parses user input and returns the corresponding command.
     * @param userInput The user input to parse.
     * @return The command corresponding to the user input.
     */
    public Command parse(String userInput) {

        String[] requestComponents = userInput.split(" ");
        String keyword = requestComponents[0];
        Command command = null;

        if (keyword.equals("bye")) {
            command = new ExitCommand();
        } else if (keyword.equals("list")) {
            command = new ListCommand();
        } else if (keyword.equals("mark")) {
            Integer taskNumber = Integer.parseInt(requestComponents[1]) - 1;
            command = new MarkCommand(taskNumber);
        } else if (keyword.equals("unmark")) {
            Integer taskNumber = Integer.parseInt(requestComponents[1]) - 1;
            command = new UnmarkCommand(taskNumber);
        } else if (keyword.equals("delete")) {
            Integer taskNumber = Integer.parseInt(requestComponents[1]) - 1;
            command = new DeleteCommand(taskNumber);
        } else if (keyword.equals("find")) {
            String searchTerm = requestComponents[1];
            command = new FindCommand(searchTerm);
        } else {
            TaskType taskType = TaskType.fromString(keyword);
            command = new AddCommand(taskType, userInput);
        }

        return command;
    }

}
