package Timmy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exceptions.TimmyInvalidParamException;

public class Parser {
    public static String[] parseCommand(String command) {
        return command.split(" ", 2);
    }

    public static int parseMark(String input) throws TimmyInvalidParamException {
        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new TimmyInvalidParamException();
        }
        return index;
    }

    public static String[] parseDeadline(String input) throws TimmyInvalidParamException {
        Pattern pattern = Pattern.compile("(.*?) /by (.*)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return new String[]{matcher.group(1), matcher.group(2)};
        } else {
            throw new TimmyInvalidParamException();
        }
    }

    public static String[] parseEvent(String input) throws TimmyInvalidParamException {
        Pattern pattern = Pattern.compile("(.*?) /from (.*?) /to (.*)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return new String[]{matcher.group(1), matcher.group(2), matcher.group(3)};
        } else {
            throw new TimmyInvalidParamException();
        }
    }
}
