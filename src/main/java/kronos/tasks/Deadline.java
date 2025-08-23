package kronos.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate byDate;

    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
   
    @Override
    public String toDataString() {
        return String.format("D,%s,%s", super.toDataString(), byDate);
    }
}
