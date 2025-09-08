package kronos.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import kronos.tasklist.TaskList;
import kronos.tasks.Deadline;
import kronos.tasks.Event;
import kronos.tasks.Task;
import kronos.tasks.TaskType;
import kronos.tasks.ToDo;

/**
 * Represents a command to add a new task.
 */
public class AddCommand implements Command {

    private static final int MAX_SIZE = 100;
    private TaskType taskType;
    private String request;

    /**
     * Instantiates a new AddCommand.
     * @param taskType The type of task to add.
     * @param request The user's request containing task details.
     */
    public AddCommand(TaskType taskType, String request) {
        this.taskType = taskType;
        this.request = request;
    }

    /**
     * Extracts the description for a ToDo task from the user's request.
     * @param request The user's request string.
     * @return A trimmed substring containing the task description.
     * @throws NullPointerException If the description is missing.
     */
    private String extractToDoDescription(String request) throws NullPointerException {

        if (request == null || request.length() < 5) {
            throw new NullPointerException("Sir, you might have forgotten to add a description.");
        }

        String description = request.substring(5).trim();

        return description;
    }

    /**
     * Extracts the description and by date from the user's request.
     * @param request The user's request string.
     * @return  An array of size 2containing the description and by date.
     * @throws NullPointerException If the description or by date is missing.
     */
    private String[] extractDeadlineDetails(String request) throws NullPointerException {

        // Split the request to get the description and by date
        String[] deadlineComponents = request.split("/by");

        // Trim each component to remove leading and trailing spaces
        for (int i = 0; i < deadlineComponents.length; ++i) {
            deadlineComponents[i] = deadlineComponents[i].trim();
        }

        // Check for if the description or by date is missing
        if (deadlineComponents[0].length() <= 9) {
            throw new NullPointerException("Sir, you might have forgotten to add a description.");
        } else if (deadlineComponents.length < 2 || deadlineComponents[1].isEmpty()) {
            throw new NullPointerException("Sir, you might have forgotten to add a by date.");
        }

        // Extract the by date from the request and description
        String byDateString = deadlineComponents[1];
        String description = deadlineComponents[0].substring(9).trim();

        //Place them in an array of size 2 to return
        String[] details = new String[2];
        details[0] = description;
        details[1] = byDateString;

        return details;
    }

    /**
     * Extracts the description, start date and end date from the user's request.
     * @param request The user's request string.
     * @return An array of size 3 containing the description, start date and end date.
     * @throws NullPointerException If the description, start date or end date is missing.
     */
    private String[] extractEventDetails(String request) throws NullPointerException {

        // Split the request to get the description, start and end dates
        String[] eventComponents = request.split("/from|/to");

        // Trim each component to remove leading and trailing spaces
        for (int i = 0; i < eventComponents.length; ++i) {
            eventComponents[i] = eventComponents[i].trim();
        }

        // Check for if the description, from or to date is missing
        if (eventComponents[0].trim().length() <= 6) {
            throw new NullPointerException("Sir, you might have forgotten to add a description.");
        } else if (eventComponents.length < 3 || eventComponents[1].isEmpty() || eventComponents[2].isEmpty()) {
            throw new NullPointerException("Sir, you might have forgotten to add a start or end date.");
        }

        // Extract the start and end dates from the request
        String description = eventComponents[0].substring(6).trim();
        String startDateString = eventComponents[1];
        String endDateString = eventComponents[2];

        // Place them in an array of size 3 to return
        String[] details = new String[3];
        details[0] = description;
        details[1] = startDateString;
        details[2] = endDateString;

        return details;
    }

    /**
     * Creates a new Task object based on the user's request.
     * @param taskList The task list to add the new task to.
     * @return A new Task object of the specified type.
     * @throws IllegalArgumentException If the task type is not recognized.
     * @throws NullPointerException If any of the required fields are missing.
     * @throws DateTimeParseException If the date format is incorrect.
     */
    @Override
    public String execute(TaskList taskList) throws IllegalArgumentException,
            NullPointerException, DateTimeParseException {

        // Determine if the storage is full
        if (taskList.getAllTasks().size() >= MAX_SIZE) {
            throw new IllegalArgumentException("Task storage is full, I cannot add more tasks.");
        }

        // Determine the task type based on the first word
        String description = "";
        Task newTask = null;

        switch (taskType) {
        case TODO:

            // Extract the description from the request
            description = extractToDoDescription(request);

            newTask = new ToDo(description);
            break;
        case DEADLINE:

            // Extract the description and by date from the request
            String[] deadlineComponents = extractDeadlineDetails(request);

            // Parse the by date string into a LocalDate object
            description = deadlineComponents[0];
            LocalDate byDate = LocalDate.parse(deadlineComponents[1]);

            newTask = new Deadline(description, byDate);
            break;
        case EVENT:

            // Extract the description, start and end dates from the request
            String[] eventComponents = extractEventDetails(request);

            // Parse the start and end date strings into LocalDate objects
            description = eventComponents[0];
            LocalDate startDate = LocalDate.parse(eventComponents[1]);
            LocalDate endDate = LocalDate.parse(eventComponents[2]);

            newTask = new Event(description, startDate, endDate);
            break;
        default:
            // If the task type is not recognized, throw an exception
            throw new IllegalArgumentException("What kind of task is " + taskType + "?");
        }

        // Add the new task to the list
        taskList.addTask(newTask);

        String responseString = "added this task:\n " + newTask.toString()
            + "\n" + "Now you have " + taskList.getAllTasks().size()
            + " tasks in the list.";

        return responseString;
    }

}
