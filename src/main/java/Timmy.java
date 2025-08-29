import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.TimmyDateParsingException;
import Exceptions.TimmyInvalidParamException;
import Exceptions.TimmyStorageOutOfBoundsException;

public class Timmy {
    protected final ArrayList<Task> storage;
    protected final Ui ui;

    public Timmy() {
        storage = Filer.loadStorage();
        ui = new Ui();
    }

    public static void main(String[] args) {
        new Timmy().run();
    }

    private void handleList() {
        ui.showList(this.storage);
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
        ui.showMarkMessage(targetTask);
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
        ui.showUnmarkMessage(targetTask);
    }

    private void handleToDo(String input) {
        ToDo newToDo = new ToDo(input);
        this.storage.add(newToDo);
        Filer.saveStorage(storage);
        ui.showAddMessage(newToDo, storage.size());
    }

    private void handleDeadline(String input) throws TimmyInvalidParamException, TimmyDateParsingException {
        String[] args = Parser.parseDeadline(input);
        Deadline newDeadline = new Deadline(args[0], args[1]);
        this.storage.add(newDeadline);
        Filer.saveStorage(storage);
        ui.showAddMessage(newDeadline, storage.size());
    }

    private void handleEvent(String input) throws TimmyInvalidParamException {
        String[] args = Parser.parseEvent(input);
        Event newEvent = new Event(args[0], args[1], args[2]);
        this.storage.add(newEvent);
        Filer.saveStorage(storage);
        ui.showAddMessage(newEvent, storage.size());
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
        ui.showDeleteMessage(deletedTask, storage.size());
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        ui.showWelcomeMessage();
        while (!isExit) {
            try {
                String input = sc.nextLine();
                String[] args = Parser.parseCommand(input);
                ValidCommand command = ValidCommand.valueOf(args[0].toUpperCase());
                switch (command) {
                    case BYE:
                        ui.showByeMessage();
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
                ui.showMessage("     Sorry, I do not understand that command.");
            } catch (TimmyInvalidParamException e) {
                ui.showMessage("     Error: Invalid Parameters were provided.");
            } catch (TimmyStorageOutOfBoundsException e) {
                ui.showMessage("     Error: Invalid Index.");
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showMessage("     Error: No arguments were provided.");
            } catch (TimmyDateParsingException e) {
                ui.showMessage("     Error: Invalid Date Format.");
            }
        }
    }
}
