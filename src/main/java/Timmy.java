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

    private String addMessage(Task task) {
        return "     Got it. I've added this task: \n"
                + "       " + task.toCompleteString() + "\n"
                + "     Now you have " + this.storage.size() + " tasks in the list.";
    }

    private String deleteMessage(Task task) {
        return "     Noted. I've removed this task: \n"
                + "       " + task.toCompleteString() + "\n"
                + "     Now you have " + this.storage.size() + " tasks in the list.";
    }

    private void handleList() {
        int index = 1;
        System.out.println("    ____________________________________________________________");
        for (Task t : this.storage) {
            System.out.println("    " + index + ". " + t.toCompleteString());
            index += 1;
        }
        System.out.println("    ____________________________________________________________");
    }

    private void handleMark(String input) throws TimmyInvalidParamException, TimmyStorageOutOfBoundsException {
        int index = Parser.parseMark(input);
        Task targetTask;
        try {
            targetTask = this.storage.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TimmyStorageOutOfBoundsException();
        }
        targetTask.markAsDone();
        borderPrint("     Nice! I've marked this task as done:\n"
                + "       " + targetTask.toCompleteString());
    }

    private void handleUnmark(String input) throws TimmyInvalidParamException, TimmyStorageOutOfBoundsException {
        int index = Parser.parseMark(input);
        Task targetTask;
        try {
            targetTask = this.storage.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TimmyStorageOutOfBoundsException();
        }
        targetTask.markAsNotDone();
        borderPrint("     Ok. I've marked this task as not done yet:\n"
                + "       " + targetTask.toCompleteString());
    }

    private void handleToDo(String input) {
        ToDo newToDo = new ToDo(input);
        this.storage.add(newToDo);
        borderPrint(addMessage(newToDo));
    }

    private void handleDeadline(String input) throws TimmyInvalidParamException {
        String[] args = Parser.parseDeadline(input);
        Deadline newDeadline = new Deadline(args[0], args[1]);
        this.storage.add(newDeadline);
        borderPrint(addMessage(newDeadline));
    }

    private void handleEvent(String input) throws TimmyInvalidParamException {
        String[] args = Parser.parseEvent(input);
        Event newEvent = new Event(args[0], args[1], args[2]);
        this.storage.add(newEvent);
        borderPrint(addMessage(newEvent));
    }
    
    private void handleDelete(String input) throws TimmyInvalidParamException, TimmyStorageOutOfBoundsException {
        int index = Parser.parseMark(input);
        Task deletedTask;
        try {
            deletedTask = this.storage.get(index);
            this.storage.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TimmyStorageOutOfBoundsException();
        }
        borderPrint(deleteMessage(deletedTask));
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        String welcome = "     Hello! I'm Timmy\n     What can I do for you?";
        String bye = "     Bye. Hope to see you again soon!";

        borderPrint(welcome);

        while (!isExit) {
            try {
                String input = sc.nextLine();
                String[] args = Parser.parseCommand(input);
                if (args[0].equals("bye")) {
                    borderPrint(bye);
                    isExit = true;
                } else if (args[0].equals("list")) {
                    handleList();
                } else if (args[0].equals("mark")) {
                    handleMark(args[1]);
                } else if (args[0].equals("unmark")) {
                    handleUnmark(args[1]);
                } else if (args[0].equals("todo")) {
                    handleToDo(args[1]);
                } else if (args[0].equals("deadline")) {
                    handleDeadline(args[1]);
                } else if (args[0].equals("event")) {
                    handleEvent(args[1]);
                } else if (args[0].equals("delete")) {
                    handleDelete(args[1]);
                } else {
                    throw new TimmyUnknownCommandException();
                }
            } catch (TimmyUnknownCommandException e) {
                borderPrint("     Sorry, I do not understand that command.");
            } catch (TimmyInvalidParamException e) {
                borderPrint("     Error: Invalid Parameters were provided.");
            } catch (TimmyStorageOutOfBoundsException e) {
                borderPrint("     Error: Invalid Index.");
            } catch (ArrayIndexOutOfBoundsException e) {
                borderPrint("     Error: No arguments were provided");
            }
        }
    }
}
