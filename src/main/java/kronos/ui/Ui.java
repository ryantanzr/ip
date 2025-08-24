package kronos.ui;
import java.util.Scanner;

/**
 * Handles user interactions and displays information to the user.
 */
public class Ui {

    private String divider = "____________________________________________________________";
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays a message to the user with top and bottom dividers.
     */
    public void showMessage(String message) {
        System.out.println(divider);
        System.out.println(message);
        System.out.println(divider);
    }

    /**
     * Pauses the program to retrieve user input.
     * @return The user input as a string.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }



}
