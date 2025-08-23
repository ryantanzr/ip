import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.startDate = start;
        this.endDate = end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toDataString() {
        return String.format("E,%s,%s,%s", super.toDataString(), startDate, endDate);
    }
}
