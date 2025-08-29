public class Event extends Task {
    protected final String start;
    protected final String end;

    Event(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    public String toCompleteString() {
        return "[E]" + super.toStringWithStatusIcon()
                + " (from: " + start
                + " to: " + end + ")";
    }

    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + start + " | " + end;
    }
}
