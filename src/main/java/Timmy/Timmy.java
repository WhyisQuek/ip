package Timmy;

import java.util.Scanner;

import Exceptions.TimmyDateParsingException;
import Exceptions.TimmyInvalidParamException;
import Exceptions.TimmyTaskListOutOfBoundsException;

public class Timmy {
    protected final Ui ui;
    protected final TaskList taskList;

    public Timmy() {
        this.ui = new Ui();
        this.taskList = new TaskList(Storage.loadStorage());
    }

    public static void main(String[] args) {
        new Timmy().run();
    }

    private void handleList() {
        ui.showList(this.taskList.getList());
    }

    private void handleMark(String input) throws TimmyInvalidParamException, TimmyTaskListOutOfBoundsException {
        int index = Parser.parseMark(input);
        Task targetTask;
        targetTask = this.taskList.getTask(index);
        targetTask.markAsDone();
        Storage.saveStorage(this.taskList.getList());
        ui.showMarkMessage(targetTask);
    }

    private void handleUnmark(String input) throws TimmyInvalidParamException, TimmyTaskListOutOfBoundsException {
        int index = Parser.parseMark(input);
        Task targetTask;
        targetTask = this.taskList.getTask(index);
        targetTask.markAsNotDone();
        Storage.saveStorage(this.taskList.getList());
        ui.showUnmarkMessage(targetTask);
    }

    private void handleToDo(String input) {
        ToDo newToDo = new ToDo(input);
        this.taskList.add(newToDo);
        Storage.saveStorage(this.taskList.getList());
        ui.showAddMessage(newToDo, this.taskList.size());
    }

    private void handleDeadline(String input) throws TimmyInvalidParamException, TimmyDateParsingException {
        String[] args = Parser.parseDeadline(input);
        Deadline newDeadline = new Deadline(args[0], args[1]);
        this.taskList.add(newDeadline);
        Storage.saveStorage(this.taskList.getList());
        ui.showAddMessage(newDeadline, this.taskList.size());
    }

    private void handleEvent(String input) throws TimmyInvalidParamException {
        String[] args = Parser.parseEvent(input);
        Event newEvent = new Event(args[0], args[1], args[2]);
        this.taskList.add(newEvent);
        Storage.saveStorage(this.taskList.getList());
        ui.showAddMessage(newEvent, this.taskList.size());
    }

    private void handleDelete(String input) throws TimmyInvalidParamException, TimmyTaskListOutOfBoundsException {
        int index = Parser.parseMark(input);
        Task deletedTask;
        deletedTask = this.taskList.getTask(index);
        this.taskList.remove(index);
        Storage.saveStorage(this.taskList.getList());
        ui.showDeleteMessage(deletedTask, taskList.size());
    }

    private void handleClear() {
        this.taskList.clear();
        Storage.saveStorage(this.taskList.getList());
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
                case CLEAR:
                    handleClear();
                    break;
                default:
                    break;
                }

            } catch (IllegalArgumentException e) {
                ui.showMessage("     Sorry, I do not understand that command.");
            } catch (TimmyInvalidParamException e) {
                ui.showMessage("     Error: Invalid Parameters were provided.");
            } catch (TimmyTaskListOutOfBoundsException e) {
                ui.showMessage("     Error: Invalid Index.");
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showMessage("     Error: No arguments were provided.");
            } catch (TimmyDateParsingException e) {
                ui.showMessage("     Error: Invalid Date Format.");
            }
        }
    }
}
