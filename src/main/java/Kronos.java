import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Kronos {

    private static final int MAX_SIZE = 100;
    private static String divider = "____________________________________________________________\n";
    private static List<Task> storage = new ArrayList<>();
    private static String filePath = "data/tasks.csv";
    
    /**
     * Saves the current tasks to a csv file
     * @throws java.io.IOException
     */
    private static void saveTasks() {
        
        // Create or open the file in the given file path
        File file = new File(filePath);

        // Write the tasks into the file
        try (FileWriter writer = new FileWriter(file)) {
            
            // Create a new file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            // Write the tasks into the file
            for (Task task : storage) {
                writer.write(task.toDataString() + "\n");
            }

            // Flush the writer to ensure all data is written
            writer.flush();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads saved tasks 
     * @return A list of loaded tasks
     */
    private static List<Task> loadTasks() {
        
        List<Task> loadedTasks = new ArrayList<>();

        // Create or open the file in the given file path
        File file = new File(filePath);

        // Read the tasks from the file
        try (java.util.Scanner scanner = new java.util.Scanner(file)) {

            // If a file doesn't exist, return an empty list
            if (!file.exists()) {
                return new ArrayList<Task>();
            }

            // Read each line from the csv file
            while (scanner.hasNextLine()) {

                // Extract and dissect the lines according to the CSV format
                String line = scanner.nextLine();
                String[] components = line.split(",");

                // Extract the individdual components
                String taskType = components[0];
                String description = components[1];
                boolean isDone = components[2].equals("1");

                // Initialise the actual tasks
                Task task = null;

                switch (taskType) {
                    case "TD":
                        task = new ToDo(description);
                        break;
                    case "D":
                        LocalDate byDate = LocalDate.parse(components[3]);
                        task = new Deadline(description, byDate);
                        break;
                    case "E":
                        LocalDate startDate = LocalDate.parse(components[3]);
                        LocalDate endDate = LocalDate.parse(components[4]);
                        task = new Event(description, startDate, endDate);
                        break;
                    default:
                        System.out.println("Unknown task type: " + taskType);
                        continue;
                }

                // Set the completion status of the task
                if (task != null) {
                    task.setCompletionStatus(isDone);
                    loadedTasks.add(task);
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            loadedTasks = new ArrayList<Task>();
        } 

        return loadedTasks;
    }

    /**
     * Deletes a task from the storage
     * @param taskNumber he index of the task to be deleted
     * @return A formatted string showing the removed task
     * @throws IndexOutOfBoundsException If the task number is invalid
     */
    public static String deleteTask(int taskNumber) throws IndexOutOfBoundsException {

        // Check if the task number is valid
        if (taskNumber < 0 || taskNumber >= storage.size()) {
            throw new IndexOutOfBoundsException("Invalid task number: " + (taskNumber + 1));
        }

        // Remove the task from the list
        Task removedTask = storage.remove(taskNumber);
        
        // Format the response to show the removed task
        String responseString = "Noted. I've removed this task:\n";
        responseString += "  " + removedTask.toString() + "\n"
        + "Now you have " + storage.size() + " tasks in the list.\n";

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
        storage.get(taskNumber).setCompletionStatus(isDone);

        // Extract task details
        String taskDescription = storage.get(taskNumber).getDescription();
        String taskStatus = storage.get(taskNumber).getCompletionStatus();

        // Return a formatted string showing the new status and description
        return String.format("  [%s] %s \n", taskStatus, taskDescription);

    }

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
        if (storage.size() >= MAX_SIZE) {
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
     * 
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
            saveTasks();

            // Print out a custom exit message
            String exitMessage = "Till next time...";
            System.out.println(divider + exitMessage + "\n" + divider);
        
        } else if (keyword.equals("list")) {
            
            String listMessage = "Here are the tasks I've helped you store: \n";
            String storedText = divider + listMessage;

            // Start a for loop to print out the stored text
            for (int index = 0; index < storage.size(); ++index) {
                storedText += String.format("%d.", index + 1);
                storedText += storage.get(index).toString() + "\n";
            }
            System.out.println(storedText + divider);

        } else if (keyword.equals("mark")) {
            
            String markMessage = "Marking that task as completed: \n";
            String storedText = divider + markMessage;

            // Extract the task number
            Integer taskNumber = Integer.parseInt(requestComponents[1]) - 1;

            // Mark task as completed
            storedText += changeTaskStatus(true, taskNumber);
            
            // Print out the full response
            System.out.println(storedText + divider);

        } else if (keyword.equals("unmark")) {
            
            String unmarkMessage = "Marking that task as incomplete: \n";
            String storedText = divider + unmarkMessage;

            // Extract the task number
            Integer taskNumber = Integer.parseInt(requestComponents[1]) - 1;

            // Mark task as incomplete
            storedText += changeTaskStatus(false, taskNumber);
            
            // Print out the full response
            System.out.println(storedText + divider);

        } else if (keyword.equals("delete")) {

            //Extract the task number
            Integer taskNumber = Integer.parseInt(requestComponents[1]) - 1;

            //Delete the task and print out the response
            String deleteText = deleteTask(taskNumber);

            System.out.println(divider + deleteText + divider);

        } else {

            try {
                Task newTask = createTask(keyword, request);
                
                // Store the new task in the storage array
                storage.add(newTask);

                // Print out the task added message
                System.out.println(divider + "added this task:\n "+ newTask.toString() + "\n");
                System.out.println("Now you have " + storage.size() + " tasks in the list.\n" + divider);

            } catch (IllegalArgumentException | NullPointerException | DateTimeParseException e) {
                // Print out the error message
                System.out.println(divider + e.getMessage() + ". Please try again."+ "\n" + divider);
                return shouldExit;
            }


        
        }
        return shouldExit;
    }

    public static void main(String[] args) {
        
        String greetingMessage = "You've invoked the timekeeper Kronos\n" + "How may I assist you today?\n";
        boolean isExiting = false;
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        // Load tasks
        storage = loadTasks();

        System.out.println(String.format(divider + greetingMessage + divider));
       
        while (!isExiting && scanner.hasNextLine()) {

            // Await for user's message
            String message = scanner.nextLine();

            // Invoke message handler function
            isExiting = handleRequest(message);
        
        }
        scanner.close();
    }
}
