package Timmy;

import java.util.ArrayList;

public class Ui {
    private static final String CHATBOT_TEXT_OFFSET = "     ";
    private static final String CHATBOT_TEXT_LARGE_OFFSET = "       ";
    private static final String CHATBOT_BORDER = "    ____________________________________________________________";

    public Ui() {}

    public void showMessage(String s) {
        System.out.println(CHATBOT_BORDER);
        System.out.println(s);
        System.out.println(CHATBOT_BORDER);
    }

    public void showWelcomeMessage() {
        showMessage(CHATBOT_TEXT_OFFSET + "Hello! I'm Timmy.\n"
                + CHATBOT_TEXT_OFFSET + "What can I do for you?");
    }

    public void showByeMessage() {
        showMessage(CHATBOT_TEXT_OFFSET + "Bye. Hope to see you again soon!");
    }

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

    public void showAddMessage(Task task, Integer size) {
        showMessage(CHATBOT_TEXT_OFFSET + "Got it. I've added this task: \n"
                + CHATBOT_TEXT_LARGE_OFFSET + task.toCompleteString() + "\n"
                + CHATBOT_TEXT_OFFSET + "Now you have " + size + " tasks in the list.");
    }

    public void showDeleteMessage(Task task, Integer size) {
        showMessage(CHATBOT_TEXT_OFFSET + "Noted. I've removed this task: \n"
                + CHATBOT_TEXT_LARGE_OFFSET + task.toCompleteString() + "\n"
                + CHATBOT_TEXT_OFFSET + "Now you have " + size + " tasks in the list.");
    }

    public void showMarkMessage(Task task) {
        showMessage(CHATBOT_TEXT_OFFSET + "Nice! I've marked this task as done:\n"
                + CHATBOT_TEXT_LARGE_OFFSET + task.toCompleteString());
    }

    public void showUnmarkMessage(Task task) {
        showMessage(CHATBOT_TEXT_OFFSET + "Ok. I've marked this task as not done yet:\n"
                + CHATBOT_TEXT_LARGE_OFFSET + task.toCompleteString());
    }
}
