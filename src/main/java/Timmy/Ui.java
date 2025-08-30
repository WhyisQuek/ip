package Timmy;

import java.util.ArrayList;

/**
 * Collection of methods used to print Timmy's output to the user interface.
 */
public class Ui {
    private static final String CHATBOT_TEXT_OFFSET = "     ";
    private static final String CHATBOT_TEXT_LARGE_OFFSET = "       ";
    private static final String CHATBOT_BORDER = "    ____________________________________________________________";

    public Ui() {}

    /**
     * Prints a given message with pre-set borders.
     *
     * @param message the message to be printed.
     */
    public void showMessage(String message) {
        System.out.println(CHATBOT_BORDER);
        System.out.println(message);
        System.out.println(CHATBOT_BORDER);
    }

    /**
     * Prints the welcome message to be shown when Timmy is run.
     */
    public void showWelcomeMessage() {
        showMessage(CHATBOT_TEXT_OFFSET + "Hello! I'm Timmy.\n"
                + CHATBOT_TEXT_OFFSET + "What can I do for you?");
    }

    /**
     * Prints the goodbye message to be shown when Timmy exits gracefully
     * from a 'bye' command.
     */
    public void showByeMessage() {
        showMessage(CHATBOT_TEXT_OFFSET + "Bye. Hope to see you again soon!");
    }

    /**
     * Prints the current list of tasks stored in memory.
     *
     * @param list the current list of tasks.
     */
    public void showList(ArrayList<Task> list) {
        int index = 1;
        System.out.println(CHATBOT_BORDER);
        for (Task t : list) {
            System.out.println(CHATBOT_TEXT_OFFSET + index + ". " + t.toCompleteString());
            index += 1;
        }
        System.out.println(CHATBOT_BORDER);
    }

    public void showFindList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            showMessage(CHATBOT_TEXT_OFFSET + "No matching tasks were found.");
        } else {
            int index = 1;
            System.out.println(CHATBOT_BORDER);
            System.out.println(CHATBOT_TEXT_OFFSET + "Here are the matching tasks in your list:");
            for (Task t : list) {
                System.out.println(CHATBOT_TEXT_OFFSET + index + ". " + t.toCompleteString());
                index += 1;
            }
            System.out.println(CHATBOT_BORDER);
        }
    }

    /**
     * Prints the message to be shown upon successfully adding a task.
     *
     * @param task the task that has been added.
     * @param size the number of tasks in the list after successful addition.
     */
    public void showAddMessage(Task task, Integer size) {
        showMessage(CHATBOT_TEXT_OFFSET + "Got it. I've added this task: \n"
                + CHATBOT_TEXT_LARGE_OFFSET + task.toCompleteString() + "\n"
                + CHATBOT_TEXT_OFFSET + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the message to be shown upon successfully deleting a task.
     *
     * @param task the task that has been deleted.
     * @param size the number of tasks in the list after successful deletion.
     */
    public void showDeleteMessage(Task task, Integer size) {
        showMessage(CHATBOT_TEXT_OFFSET + "Noted. I've removed this task: \n"
                + CHATBOT_TEXT_LARGE_OFFSET + task.toCompleteString() + "\n"
                + CHATBOT_TEXT_OFFSET + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the message to be shown upon successfully marking a task for completion.
     *
     * @param task the task that has been marked for completion.
     */
    public void showMarkMessage(Task task) {
        showMessage(CHATBOT_TEXT_OFFSET + "Nice! I've marked this task as done:\n"
                + CHATBOT_TEXT_LARGE_OFFSET + task.toCompleteString());
    }

    /**
     * Prints the message to be shown upon successfully marking a task as incomplete.
     *
     * @param task the task that has been marked as incomplete.
     */
    public void showUnmarkMessage(Task task) {
        showMessage(CHATBOT_TEXT_OFFSET + "Ok. I've marked this task as not done yet:\n"
                + CHATBOT_TEXT_LARGE_OFFSET + task.toCompleteString());
    }
}
