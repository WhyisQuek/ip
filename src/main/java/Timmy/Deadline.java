package Timmy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Exceptions.TimmyDateParsingException;

public class Deadline extends Task {
    protected static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected final LocalDate end;

    Deadline(String desc, String end) throws TimmyDateParsingException {
        super(desc);
        try {
            this.end = LocalDate.parse(end, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new TimmyDateParsingException();
        }
    }

    public String toCompleteString() {
        return "[D]" + super.toStringWithStatusIcon()
                + " (by: " + end.format(OUTPUT_DATE_FORMAT) + ")";
    }

    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + super.toString() + " | " + end.format(INPUT_DATE_FORMAT);
    }
}
