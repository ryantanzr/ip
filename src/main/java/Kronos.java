import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Kronos {

    private static final int MAX_SIZE = 100;

    private static TaskList taskList = new TaskList();
    private static Storage storage = new Storage();
    private static Ui ui = new Ui();


    /**
     * Deletes a task from the storage
     * @param taskNumber he index of the task to be deleted
     * @return A formatted string showing the removed task
     * @throws IndexOutOfBoundsException If the task number is invalid
     */
    public static String deleteTask(int taskNumber) throws IndexOutOfBoundsException {


        // Check if the task number is valid
        if (taskNumber < 0 || taskNumber >= taskList.getAllTasks().size()) {
            throw new IndexOutOfBoundsException("Invalid task number: " + (taskNumber + 1));
        }

        // Remove the task from the list
        Task removedTask = taskList.removeTask(taskNumber);
        
        // Format the response to show the removed task
        String responseString = "Noted. I've removed this task:\n";
        responseString += "  " + removedTask.toString() + "\n"
        + "Now you have " + taskList.getAllTasks().size() + " tasks in the list.";

        return responseString;
    }

    /**
     * Changes the completion status of a task
     * 
     * @param isDone Boolean flag indicating whether the task is done or not
     * @param taskNumber Where the task is stored in the storage array
     * @return A formatted string showing the new status and task description
     */
    public static String changeTaskStatus(boolean isDone, int taskNumber) {

        // Mark task as imcomplete
        taskList.getTask(taskNumber).setCompletionStatus(isDone);

        // Extract task details
        String taskDescription = taskList.getTask(taskNumber).getDescription();
        String taskStatus = taskList.getTask(taskNumber).getCompletionStatus();

        // Return a formatted string showing the new status and description
        return String.format("  [%s] %s ", taskStatus, taskDescription);

    }


    // Todo: Rework createTask to accept a varargs

    /**
     * Creates a new Task object based on the user's request
     * 
     * @param taskType Type of task to be added (todo, deadline, event)
     * @param request User's full request containing task details such
     * as description, by date, start and end dates
     * @return A new Task object of the specified type
     * @throws IllegalArgumentException If the task type is not recognized
     * @throws NullPointerException If any of the required fields are missing
     * @throws DateTimeParseException If the date format is incorrect
     */
    public static Task createTask(String taskType, String request) throws IllegalArgumentException, 
        NullPointerException, DateTimeParseException {

        // Determine if the storage is full
        if (taskList.getAllTasks().size() >= MAX_SIZE) {
            throw new IllegalArgumentException("Task storage is full, I cannot add more tasks.");
        }

        // Determine the task type based on the first word
        String description = "";
        Task newTask = null;

        switch (taskType) {
            case "todo":
                
                // If the request lacks a description, throw an exception
                if (request.trim().length() < 5) {
                    throw new NullPointerException("Sir, you might have forgotten to add a description.");
                }

                // Extract the description from the request
                description = request.substring(5).trim();

                // Create a new ToDo task
                newTask = new ToDo(description);
                break;
            case "deadline":
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
                LocalDate byDate = LocalDate.parse(deadlineComponents[1]);
                description = deadlineComponents[0].substring(9).trim();
                
                // Create a new Deadline task
                newTask = new Deadline(description, byDate);
                break;
            case "event":
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
                LocalDate startDate = LocalDate.parse(eventComponents[1]);
                LocalDate endDate = LocalDate.parse(eventComponents[2]);

                // Extract the description from the request
                description = eventComponents[0].substring(6).trim();

                // Create a new Event task
                newTask = new Event(description, startDate, endDate);
                break;
            default:
                // If the task type is not recognized, throw an exception
                throw new IllegalArgumentException("What kind of task is " + taskType + "?");
        }

        return newTask;
    }

    /**
     * Handles the user's entered message
     * Todo: 
     * @param request User's entered message
     * @return Boolean flag to exit program or not 
     */
    public static boolean handleRequest(String request) {

        boolean shouldExit = false;
        String[] requestComponents = request.split(" ");
        String keyword = requestComponents[0];

        if (keyword.equals( "bye")) {

            // Toggle shouldExit to true
            shouldExit = true;

            // Save the tasks to the given file
            storage.saveTasks(taskList.getAllTasks());

            // Print out a custom exit message
            String exitMessage = "Till next time...";
            ui.showMessage(exitMessage);
        
        } else if (keyword.equals("list")) {
            
            List<Task> tasks = taskList.getAllTasks();
            String listMessage = "Here are the tasks I've helped you store: \n";
            String storedText = listMessage;

            // Start a for loop to print out the stored text
            for (int index = 0; index < tasks.size(); ++index) {
                storedText += String.format("%d.", index + 1);
                storedText += tasks.get(index).toString() + "\n";
            }

            storedText = storedText.substring(0, storedText.length() - 1);

            ui.showMessage(storedText);

        } else if (keyword.equals("mark")) {
            
            String markMessage = "Marking that task as completed: \n";

            // Extract the task number
            Integer taskNumber = Integer.parseInt(requestComponents[1]) - 1;

            // Mark task as completed
            markMessage += changeTaskStatus(true, taskNumber);
            
            // Print out the full response
            ui.showMessage(markMessage);

        } else if (keyword.equals("unmark")) {
            
            String unmarkMessage = "Marking that task as incomplete: \n";

            // Extract the task number
            Integer taskNumber = Integer.parseInt(requestComponents[1]) - 1;

            // Mark task as incomplete
            unmarkMessage += changeTaskStatus(false, taskNumber);
            
            // Print out the full response
            ui.showMessage(unmarkMessage);

        } else if (keyword.equals("delete")) {

            //Extract the task number
            Integer taskNumber = Integer.parseInt(requestComponents[1]) - 1;

            //Delete the task and print out the response
            String deleteText = deleteTask(taskNumber);

            ui.showMessage(deleteText);

        } else {

            try {
                Task newTask = createTask(keyword, request);

                // Store the new task in the task list
                taskList.addTask(newTask);

                // Print out the task added message
                ui.showMessage("added this task:\n "+ newTask.toString() + "\n" + 
                    "Now you have " + taskList.getAllTasks().size() + " tasks in the list.");

            } catch (IllegalArgumentException | NullPointerException | DateTimeParseException e) {
                // Print out the error message
                ui.showMessage(e.getMessage() + ". Please try again.");
                return shouldExit;
            }


        
        }
        return shouldExit;
    }

    public void run() {
        
    }

    public static void main(String[] args) {
        
        String greetingMessage = "You've invoked the timekeeper Kronos\n" + "How may I assist you today?";
        boolean isExiting = false;
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        //Load tasks
        taskList.setTasks(storage.loadTasks());

        ui.showMessage(greetingMessage);

        while (!isExiting && scanner.hasNextLine()) {

            // Await for user's message
            String message = scanner.nextLine();

            // Invoke message handler function
            isExiting = handleRequest(message);
        
        }
        scanner.close();
    }
}
