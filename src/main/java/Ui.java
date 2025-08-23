import java.util.Scanner;

/**
 * Handles user interactions and displays information to the user.
 */
public class Ui {

    private String divider = "____________________________________________________________";
    private Scanner scanner = new Scanner(System.in);

    public void showMessage(String message) {
        System.out.println(divider);
        System.out.println(message);
        System.out.println(divider);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }



}
