import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Filer {
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

        }


    }
}
