public class Task {
    protected final String description;
    protected boolean isDone;

    Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    public String printWithStatusIcon() {
        return this.getStatusIcon() + " " + this;
    }

    public String toString() {
        return this.description;
    }
}
