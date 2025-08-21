import java.util.ArrayList;
import java.util.Scanner;

public class Timmy {
    protected final ArrayList<Task> storage;

    public Timmy() {
        storage = new ArrayList<Task>();
    }

    public static void main(String[] args) {
        new Timmy().run();
    }

    private void borderPrint(String s) {
        System.out.println("    ____________________________________________________________");
        System.out.println(s);
        System.out.println("    ____________________________________________________________");
    }

    private void handleList() {
        int index = 1;
        System.out.println("    ____________________________________________________________");
        for (Task t : this.storage) {
            System.out.println("    " + index + ". " + t.printWithStatusIcon());
            index += 1;
        }
        System.out.println("    ____________________________________________________________");
    }

    private void handleMark(String input) {
        String[] args = input.split(" ");
        int index;
        try {
            index = Integer.parseInt(args[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Error: " + args[1]);
            return;
        }
        Task targetTask = this.storage.get(index);
        targetTask.markAsDone();
        borderPrint("     Nice! I've marked this task as done:\n"
                + "       " + targetTask.printWithStatusIcon());
    }

    private void handleUnmark(String input) {
        String[] args = input.split(" ");
        int index;
        try {
            index = Integer.parseInt(args[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Error: " + args[1]);
            return;
        }
        Task targetTask = storage.get(index);
        targetTask.markAsNotDone();
        borderPrint("     Ok. I've marked this task as not done yet:\n"
                + "       " + targetTask.printWithStatusIcon());
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        String welcome = "     Hello! I'm Timmy\n     What can I do for you?";
        String bye = "     Bye. Hope to see you again soon!";

        borderPrint(welcome);

        while (!isExit) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                borderPrint(bye);
                isExit = true;
            }
            else if (input.equals("list")) {
                handleList();
            }
            else if (input.startsWith("mark")) {
                handleMark(input);
            }
            else if (input.startsWith("unmark")) {
                handleUnmark(input);
            }
            else {
                Task newTask = new Task(input);
                storage.add(newTask);
                borderPrint("     added: " + newTask.printWithStatusIcon());
            }
        }
    }
}
