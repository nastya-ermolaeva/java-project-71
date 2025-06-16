package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public final class FileUtils {

    private FileUtils() {
        // private constructor prevents from creating this class examples (Sonar made me do it)
    }

    public static Path getPath(String filePath) throws IOException {
        Path path = Path.of(filePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new IOException("File is not found: " + path);
        }
        return path;
    }

    public static String getFileExtension(String filePath) {
        var dotIndex = filePath.lastIndexOf('.');

        if (dotIndex == -1 || dotIndex == filePath.length() - 1) {
            throw new IllegalArgumentException("Invalid file name or format");
        }
        return filePath.substring(dotIndex + 1).toLowerCase();
    }

    public static String readFile(String filePath) throws IOException {
        Path path = getPath(filePath);
        return Files.readString(path).trim();
    }
}
