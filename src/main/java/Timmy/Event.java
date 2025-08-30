package Timmy;

import exceptions.TimmyDateParsingException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected final LocalDate start;
    protected final LocalDate end;
    protected final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    Event(String desc, String start, String end) throws DateTimeParseException {
        super(desc);
        try {
            this.start = LocalDate.parse(start, INPUT_DATE_FORMAT);
            this.end = LocalDate.parse(end, INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new TimmyDateParsingException();
        }
    }

    public String toCompleteString() {
        return "[E]" + super.toStringWithStatusIcon()
                + " (from: " + start.format(OUTPUT_DATE_FORMAT)
                + " to: " + end.format(OUTPUT_DATE_FORMAT) + ")";
    }

    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + super.toString()
                + " | " + start.format(INPUT_DATE_FORMAT) + " | " + end.format(INPUT_DATE_FORMAT);
    }
}
