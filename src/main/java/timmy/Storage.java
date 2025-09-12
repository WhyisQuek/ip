package timmy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Exceptions.TimmyDateParsingException;
import Exceptions.TimmyFilerException;

/**
 * Represents the file used to store the list of tasks given by a user
 * Allows for tasks to be saved between sessions.
 */
public class Storage {
    private static final String WORK_DIR = System.getProperty("user.dir");
    private static final Path DIR_PATH = Paths.get(WORK_DIR, "data");

    // Save file path
    private static final Path FILE_PATH = Paths.get(WORK_DIR, "data", "storage.txt");

    /**
     * Writes the list of tasks to the configured save file.
     *
     * @param storage the list of tasks.
     */
    public static void saveStorage(ArrayList<Task> storage) {
        try {
            if (!Files.exists(DIR_PATH)) {
                Files.createDirectory(DIR_PATH);
            }
            if (!Files.exists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            }

            StringBuilder data = new StringBuilder();
            for (Task t : storage) {
                data.append(t.toFileString());
                data.append("\n");
            }
            Files.writeString(FILE_PATH, data.toString());

        } catch (IOException e) {
            // TODO: CATCH
            throw new TimmyFilerException();
        }
    }

    private static Path getNextArchivePath() {
        int i = 1;
        Path archivePath;
        do {
            archivePath = Paths.get(WORK_DIR, "data", "archive-" + i + ".txt");
            i += 1;
        } while (Files.exists(archivePath));
        return archivePath;
    }

    public static String archiveStorage(ArrayList<Task> storage) {
        try {
            if (!Files.exists(DIR_PATH)) {
                Files.createDirectory(DIR_PATH);
            }
            Path archivePath = getNextArchivePath();

            StringBuilder data = new StringBuilder();
            for (Task t : storage) {
                data.append(t.toFileString());
                data.append("\n");
            }
            Files.writeString(archivePath, data.toString());
            return archivePath.toString();

        } catch (IOException e) {
            // TODO: CATCH
            throw new TimmyFilerException();
        }
    }

    private static void loadToDo(String[] taskData, ArrayList<Task> list) {
        if (taskData.length != 3) {
            return;
        }

        ToDo newToDo = new ToDo(taskData[2]);
        if (taskData[1].equals("1")) {
            newToDo.markAsDone();
        }
        list.add(newToDo);
    }

    private static void loadDeadline(String[] taskData, ArrayList<Task> list) {
        if (taskData.length != 4) {
            return;
        }

        Deadline newDeadline = new Deadline(taskData[2], taskData[3]);
        if (taskData[1].equals("1")) {
            newDeadline.markAsDone();
        }
        list.add(newDeadline);
    }

    private static void loadEvent(String[] taskData, ArrayList<Task> list) {
        if (taskData.length != 5) {
            return;
        }

        Event newEvent = new Event(taskData[2], taskData[3], taskData[4]);
        if (taskData[1].equals("1")) {
            newEvent.markAsDone();
        }
        list.add(newEvent);
    }

    /**
     * Loads the saved list of tasks saved within the save file.
     *
     * @return the list of tasks that was saved within the save file.
     */
    public static ArrayList<Task> loadStorage() {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            String[] data = Files.readString(FILE_PATH).split("\n");
            for (String line : data) {
                String[] taskData = line.split(" \\| ");
                switch (taskData[0]) {
                case "T":
                    loadToDo(taskData, list);
                    break;
                case "D":
                    loadDeadline(taskData, list);
                    break;
                case "E":
                    loadEvent(taskData, list);
                    break;
                default:
                    break;
                }
            }
        } catch (IOException | TimmyDateParsingException e) {
            return list;
        }

        return list;
    }
}
