import java.util.ArrayList;
import java.util.Scanner;

public class Timmy {
    protected final ArrayList<Task> storage;

    public Timmy() {
        storage = Filer.loadStorage();
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
        Filer.saveStorage(storage);
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
        Filer.saveStorage(storage);
        borderPrint("     Ok. I've marked this task as not done yet:\n"
                + "       " + targetTask.toCompleteString());
    }

    private void handleToDo(String input) {
        ToDo newToDo = new ToDo(input);
        this.storage.add(newToDo);
        Filer.saveStorage(storage);
        borderPrint(addMessage(newToDo));
    }

    private void handleDeadline(String input) throws TimmyInvalidParamException {
        String[] args = Parser.parseDeadline(input);
        Deadline newDeadline = new Deadline(args[0], args[1]);
        this.storage.add(newDeadline);
        Filer.saveStorage(storage);
        borderPrint(addMessage(newDeadline));
    }

    private void handleEvent(String input) throws TimmyInvalidParamException {
        String[] args = Parser.parseEvent(input);
        Event newEvent = new Event(args[0], args[1], args[2]);
        this.storage.add(newEvent);
        Filer.saveStorage(storage);
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
        Filer.saveStorage(storage);
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
                ValidCommand command = ValidCommand.valueOf(args[0].toUpperCase());
                switch (command) {
                    case BYE:
                        borderPrint(bye);
                        isExit = true;
                        break;
                    case LIST:
                        handleList();
                        break;
                    case MARK:
                        handleMark(args[1]);
                        break;
                    case UNMARK:
                        handleUnmark(args[1]);
                        break;
                    case TODO:
                        handleToDo(args[1]);
                        break;
                    case DEADLINE:
                        handleDeadline(args[1]);
                        break;
                    case EVENT:
                        handleEvent(args[1]);
                        break;
                    case DELETE:
                        handleDelete(args[1]);
                        break;
                }
            } catch (IllegalArgumentException e) {
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
