public class ToDo extends Task {

    ToDo(String desc) {
        super(desc);
    }

    public String toCompleteString() {
        return "[T]" + super.toStringWithStatusIcon();
    }

    public String toFileString() {
        return "D | " + (isDone ? "1" : "0");
    }
}