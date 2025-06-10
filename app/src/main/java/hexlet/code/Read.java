package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Read {

    public static Path getPath(String filePath) throws IOException {
        Path path = Path.of(filePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new IOException("File is not found: " + path);
        }
        return path;
    }

    public static String readFile(String filePath) throws IOException {
        Path path = getPath(filePath);
        return Files.readString(path).trim();
    }
}
