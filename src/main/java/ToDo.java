public class ToDo extends Task {

    ToDo(String desc) {
        super(desc);
    }

    public String toCompleteString() {
        return "[T]" + super.toStringWithStatusIcon();
    }
}