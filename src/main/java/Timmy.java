import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Timmy {
    private ArrayList<Task> storage;

    public Timmy() {
        storage = new ArrayList<Task>();
    }

    public static void main(String[] args) {
        new Timmy().run();
    }

    private void border_print(String s) {
        System.out.println("____________________________________________________________");
        System.out.println(s);
        System.out.println("____________________________________________________________");
    }

    private void print_storage() {
        int index = 1;
        System.out.println("____________________________________________________________");
        for (Task t : storage) {
            System.out.println(index + ". " + t);
            index += 1;
        }
        System.out.println("____________________________________________________________");
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        boolean isExit = false;
        String welcome = """
                        Hello! I'm Timmy
                        What can I do for you?""";
        String bye = """
                        Bye. Hope to see you again soon!""";

        border_print(welcome);

        while (!isExit) {
            String inputString = input.nextLine();
            if (inputString.equals("bye")) {
                border_print(bye);
                isExit = true;
            }
            else if (inputString.equals("list")) {
                print_storage();
            }
            else {
                Task newTask = new Task(inputString);
                storage.add(newTask);
                border_print("added: " + newTask);
            }
        }

    }
}
