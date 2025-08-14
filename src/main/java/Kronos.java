public class Kronos {

    private static String divider = "____________________________________________________________\n";
    
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
        } else {
            // Echo the message back to the user
            System.out.println(divider + request + "\n" + divider);
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
