package kronos.tasks;
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toDataString() {
        return String.format("TD,%s", super.toDataString());
    }

}
