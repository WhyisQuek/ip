package Timmy;

import Exceptions.TimmyDateParsingException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected final LocalDate end;
    protected final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates an incomplete task with a specified deadline.
     *
     * @param desc  description of the task.
     * @param end   date of deadline, in d/M/yyyy format.
     */
    Deadline(String desc, String end) throws TimmyDateParsingException {
        super(desc);
        try {
            this.end = LocalDate.parse(end, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new TimmyDateParsingException();
        }
    }

    /**
     * Returns the task as a formatted string for Timmy to output.
     *
     * @return task as a formatted string to output on UI.
     */
    public String toCompleteString() {
        return "[D]" + super.toStringWithStatusIcon()
                + " (by: " + end.format(OUTPUT_DATE_FORMAT) + ")";
    }

    /**
     * Returns the task as a formatted string for Timmy to save.
     *
     * @return task as a formatted string to save in storage.
     */
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + super.toString() + " | " + end.format(INPUT_DATE_FORMAT);
    }
}