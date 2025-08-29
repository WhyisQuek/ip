package Timmy;

import Exceptions.TimmyDateParsingException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected final LocalDate end;
    protected final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

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