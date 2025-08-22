import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {
    public static String[] parseCommand(String command) {
        return command.split(" ", 2);
    }

    public static int parseMark(String input) {
        String[] args = input.split(" ", 2);
        int index;
        try {
            index = Integer.parseInt(args[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Error: " + args[1]);
            return -1;
        }
        return index;
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
