package Timmy;

import Exceptions.TimmyTaskListOutOfBoundsException;

import java.util.ArrayList;

public class TaskList {
    protected final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public Task getTask(int index) throws TimmyTaskListOutOfBoundsException {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TimmyTaskListOutOfBoundsException();
        }
    }

    public void add(Task task) {
        list.add(task);
    }

    public void remove(int index) throws TimmyTaskListOutOfBoundsException {
        try {
            list.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TimmyTaskListOutOfBoundsException();
        }
    }

    public int size() {
        return this.list.size();
    }
}
