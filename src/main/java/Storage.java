import Exceptions.TimmyDateParsingException;
import Exceptions.TimmyFilerException;

import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class Storage {
    private static final String WORK_DIR = System.getProperty("user.dir");
    private static final Path DIR_PATH = Paths.get(WORK_DIR, "data");
    private static final Path FILE_PATH = Paths.get(WORK_DIR, "data", "storage.txt");

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

    public static ArrayList<Task> loadStorage() {
        ArrayList<Task> storage = new ArrayList<Task>();
        try {
            String[] data = Files.readString(FILE_PATH).split("\n");
            for (String line : data) {
                String[] taskData = line.split(" \\| ");
                if (taskData[0].equals("T")) {
                    if (taskData.length == 3) {
                        ToDo newToDo = new ToDo(taskData[2]);
                        if (taskData[1].equals("1")) {
                            newToDo.markAsDone();
                        }
                        storage.add(newToDo);
                    }
                } else if (taskData[0].equals("D")) {
                    if (taskData.length == 4) {
                        Deadline newDeadline = new Deadline(taskData[2], taskData[3]);
                        if (taskData[1].equals("1")) {
                            newDeadline.markAsDone();
                        }
                        storage.add(newDeadline);
                    }
                } else if (taskData[0].equals("E")) {
                    if (taskData.length == 5) {
                        Event newEvent = new Event(taskData[2], taskData[3], taskData[4]);
                        if (taskData[1].equals("1")) {
                            newEvent.markAsDone();
                        }
                        storage.add(newEvent);
                    }
                }
            }
        } catch (IOException | TimmyDateParsingException e) {
            return storage;
        }

        return storage;
    }
}
