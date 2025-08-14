public class Kronos {

    private static int counter = 0;
    private static final int MAX_SIZE = 100;
    private static String divider = "____________________________________________________________\n";
    private static Task[] storage = new Task[MAX_SIZE];
    
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

                String taskDescription = storage[index].getDescription();
                String taskStatus = storage[index].getCompletionStatus();

                storedText += String.format("%d.[%s] %s \n", index + 1, taskStatus, taskDescription);
            }
            System.out.println(storedText + divider);

        } else if (keyword.equals("mark")) {
            
            String markMessage = "Marking that task as completed: \n";
            String storedText = divider + markMessage;

            // Extract the task number
            Integer taskNumber = Integer.parseInt(requestComponents[1]) - 1;

            // Mark task as completed
            storage[taskNumber].setCompletionStatus(true);

            // Extract task details
            String taskDescription = storage[taskNumber].getDescription();
            String taskStatus = storage[taskNumber].getCompletionStatus();

            // Format the string
            storedText += String.format("  [%s] %s \n", taskStatus, taskDescription);
            
            // Print out the full response
            System.out.println(storedText + divider);

        } else if (keyword.equals("unmark")) {
            
            String unmarkMessage = "Marking that task as incomplete: \n";
            String storedText = divider + unmarkMessage;

            // Extract the task number
            Integer taskNumber = Integer.parseInt(requestComponents[1]) - 1;

            // Mark task as imcomplete
            storage[taskNumber].setCompletionStatus(false);

            // Extract task details
            String taskDescription = storage[taskNumber].getDescription();
            String taskStatus = storage[taskNumber].getCompletionStatus();

            // Format the string
            storedText += String.format("  [%s] %s \n", taskStatus, taskDescription);
            
            // Print out the full response
            System.out.println(storedText + divider);

        } else {

            // Assign the content to a variable with a more fitting name
            String taskDescription = keyword;

            // Store the text and increment the counter
            storage[counter++] = new Task(taskDescription);
            System.out.println(divider + "added: "+ request + "\n" + divider);
        
        }
        return shouldExit;
    }

    public static void main(String[] args) {
        
        String greetingMessage = "You've invoked the timekeeper Kronos\n" + "How may I assist you today?\n";
        boolean isExiting = false;

        System.out.println(String.format(divider + greetingMessage + divider));
       
        while (!isExiting) {

            // Await for user's message
            String message = System.console().readLine();

            // Invoke message handler function
            isExiting = handleRequest(message);
        
        }

    }
}
