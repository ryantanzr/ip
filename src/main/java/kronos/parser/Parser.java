package kronos.parser;

import kronos.commands.AddCommand;
import kronos.commands.Command;
import kronos.commands.CommandType;
import kronos.commands.DeleteCommand;
import kronos.commands.ExitCommand;
import kronos.commands.FindCommand;
import kronos.commands.ListCommand;
import kronos.commands.MarkCommand;
import kronos.commands.TagCommand;
import kronos.commands.UnmarkCommand;
import kronos.commands.UntagCommand;
import kronos.exceptions.KronosException;
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
    public Command parse(String userInput) throws KronosException {

        String[] requestComponents = userInput.split(" ");
        String keyword = requestComponents[0];
        CommandType commandType = CommandType.fromString(keyword);
        Command command = null;

        switch (commandType) {
        case EXIT:
            command = new ExitCommand();
            break;
        case LIST:
            command = new ListCommand();
            break;
        case MARK:
            Integer taskNumber = Integer.parseInt(requestComponents[1]) - 1;
            command = new MarkCommand(taskNumber);
            break;
        case UNMARK:
            taskNumber = Integer.parseInt(requestComponents[1]) - 1;
            command = new UnmarkCommand(taskNumber);
            break;
        case DELETE:
            taskNumber = Integer.parseInt(requestComponents[1]) - 1;
            command = new DeleteCommand(taskNumber);
            break;
        case FIND:
            String searchTerm = requestComponents[1];
            command = new FindCommand(searchTerm);
            break;
        case TAG:
            taskNumber = Integer.parseInt(requestComponents[1]) - 1;
            command = new TagCommand(userInput, taskNumber);
            break;
        case UNTAG:
            taskNumber = Integer.parseInt(requestComponents[1]) - 1;
            String tagToRemove = requestComponents[2];
            command = new UntagCommand(tagToRemove, taskNumber);
            break;
        default:
            TaskType taskType = TaskType.fromString(keyword);
            command = new AddCommand(taskType, userInput);
            break;
        }

        return command;
    }

}
