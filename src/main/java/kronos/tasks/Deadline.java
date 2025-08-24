package kronos.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline with a description and due date.
 */
public class Deadline extends Task {

    private LocalDate byDate;

    /**
     * Instantiates a new deadline.
     * @param description The description of the deadline.
     * @param byDate The due date of the deadline.
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Returns the string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a CSV representation of the deadline.
     * @return The CSV representation of the deadline.
     */
    @Override
    public String toDataString() {
        return String.format("D,%s,%s", super.toDataString(), byDate);
    }
}
