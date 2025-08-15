public class Kronos {

    private static int counter = 0;
    private static final int MAX_SIZE = 100;
    private static String divider = "____________________________________________________________\n";
    private static Task[] storage = new Task[MAX_SIZE];
    
    /**
     * Changes the completion status of a task
     * 
     * @param isDone Boolean flag indicating whether the task is done or not
     * @param taskNumber Where the task is stored in the storage array
     * @return A formatted string showing the new status and task description
     */
    public static String changeTaskStatus(boolean isDone, int taskNumber) {

        // Mark task as imcomplete
        storage[taskNumber].setCompletionStatus(isDone);

        // Extract task details
        String taskDescription = storage[taskNumber].getDescription();
        String taskStatus = storage[taskNumber].getCompletionStatus();

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
     */
    public static Task createTask(String taskType, String request) {

        // Determine the task type based on the first word
        String description = "";
        Task newTask = null;

        switch (taskType) {
            case "todo":
                // Extract the description from the request
                description = request.substring(5).trim();

                // Create a new ToDo task
                newTask = new ToDo(description);
                break;
            case "deadline":
                // Split the request to get the description and by date
                String[] deadlineComponents = request.split("/by");
                
                // Extract the by date from the request and description
                String byDate = deadlineComponents[1].trim();
                description = deadlineComponents[0].substring(9).trim();
                
                // Create a new Deadline task
                newTask = new Deadline(description, byDate);
                break;
            case "event":
                // Split the request to get the description, start and end dates
                String[] eventComponents = request.split("/from|/to");
                
                // Extract the start and end dates from the request
                String startDate = eventComponents[1].trim();
                String endDate = eventComponents[2].trim();

                // Extract the description from the request
                description = eventComponents[0].substring(6).trim();

                // Create a new Event task
                newTask = new Event(description, startDate, endDate);
                break;
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

            // Print out a custom exit message
            String exitMessage = "Till next time...";
            System.out.println(divider + exitMessage + "\n" + divider);
        
        } else if (keyword.equals("list")) {
            
            String listMessage = "Here are the tasks I've helped you store: \n";
            String storedText = divider + listMessage;

            // Start a for loop to print out the stored text
            for (int index = 0; index < counter; ++index) {
                storedText += String.format("%d.", index + 1);
                storedText += storage[index].toString() + "\n";
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

        } else {

            Task newTask = createTask(keyword, request);

            // Store the new task in the storage array
            storage[counter++] = newTask;

            // Print out the task added message
            System.out.println(divider + "added this task:\n "+ newTask.toString() + "\n");
            System.out.println("Now you have " + counter + " tasks in the list.\n" + divider);
        
        }
        return shouldExit;
    }

    public static void main(String[] args) {
        
        String greetingMessage = "You've invoked the timekeeper Kronos\n" + "How may I assist you today?\n";
        boolean isExiting = false;
        java.util.Scanner scanner = new java.util.Scanner(System.in);

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
