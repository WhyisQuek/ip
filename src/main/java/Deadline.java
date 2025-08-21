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
}