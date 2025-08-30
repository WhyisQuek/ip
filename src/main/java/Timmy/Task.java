package Timmy;

public abstract class Task {
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

    abstract String toCompleteString();

    abstract String toFileString();

    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    public String toStringWithStatusIcon() {
        return this.getStatusIcon() + " " + this;
    }

    public String toString() {
        return this.description;
    }
}
