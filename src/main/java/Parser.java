import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {
    public static String[] parseCommand(String command) {
        return command.split(" ", 2);
    }

    public static String[] parseDeadline(String input) {
        Pattern pattern = Pattern.compile("deadline (.*?) /by (.*)");
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return new String[] {matcher.group(1), matcher.group(2)};
    }

    public static String[] parseEvent(String input) {
        Pattern pattern = Pattern.compile("event (.*?) /from (.*?) /to (.*)");
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return new String[] {matcher.group(1), matcher.group(2), matcher.group(3)};
    }
}
