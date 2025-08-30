package Timmy;

import Exceptions.TimmyTaskListOutOfBoundsException;

import java.util.ArrayList;

/**
 * Represents the list of tasks given by the user that has been stored in Timmy
 */
public class TaskList {
    protected final ArrayList<Task> list;

    /**
     * Creates an empty list of tasks.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns the list of tasks.
     *
     * @return list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Retrieves a task stored in the existing list based on a given index.
     *
     * @param   index the index of the task to be retrieved
     * @return  the task corresponding to the given index
     * @throws  TimmyTaskListOutOfBoundsException if the given index is out of bounds
     *          of the existing list of tasks.
     */
    public Task getTask(int index) throws TimmyTaskListOutOfBoundsException {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TimmyTaskListOutOfBoundsException();
        }
    }

    /**
     * Adds a task to the end of the existing list of tasks.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Removes a task, specified by a given index, from the existing list of tasks.
     *
     * @param   index the index of the task to be removed.
     * @throws  TimmyTaskListOutOfBoundsException if the given index is out of bounds
     *          of the existing list of tasks.
     */
    public void remove(int index) throws TimmyTaskListOutOfBoundsException {
        try {
            list.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TimmyTaskListOutOfBoundsException();
        }
    }

    /**
     * Removes all tasks from the list.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int size() {
        return this.list.size();
    }
}
