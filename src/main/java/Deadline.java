import java.time.LocalDate;

public class Deadline extends Task {
    protected final String end;

    Deadline(String desc, String end) {
        super(desc);
        this.end = end;
    }

    public String toCompleteString() {
        return "[D]" + super.toStringWithStatusIcon()
                + " (by: " + end + ")";
    }

    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + super.toString() + " | " + end;
    }
}