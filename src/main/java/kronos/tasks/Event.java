package kronos.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents an event with a description, start, and end date.
 */
public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Instantiates a new event.
     * @param description The description of the event.
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.startDate = start;
        this.endDate = end;
    }

    /**
     * Returns the string representation of the event.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a CSV representation of the event.
     * @return The CSV representation of the event.
     */
    @Override
    public String toDataString() {
        return String.format("E,%s,%s,%s", super.toDataString(), startDate, endDate);
    }
}
