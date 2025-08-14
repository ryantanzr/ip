public class Kronos {

    private static int counter = 0;
    private static final int MAX_SIZE = 100;
    private static String divider = "____________________________________________________________\n";
    private static String[] storage = new String[MAX_SIZE];
    
    /**
     * Handles the user's entered message
     * 
     * @param request User's entered message
     * @return Boolean flag to exit program or not 
     */
    public static boolean handleRequest(String request) {

        boolean shouldExit = false;

        if (request.equals( "bye")) {

            // Toggle shouldExit to true
            shouldExit = true;

            // Print out a custom exit message
            String exitMessage = "Till next time...";
            System.out.println(divider + exitMessage + "\n" + divider);
        
        } else if (request.equals("list")) {
            
            String storedText = divider;
            // Start a for loop to print out the stored text
            for (int index = 0; index < counter; ++index) {
                storedText += String.format("%d. %s \n", index + 1, storage[index]);
            }
            System.out.println(storedText + divider);

        } else {

            //Store the text and increment the counter
            storage[counter++] = request;
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
