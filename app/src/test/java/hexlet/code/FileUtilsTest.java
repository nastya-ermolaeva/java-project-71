package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class FileUtilsTest {

    @Test
    public void readJsonFileTestSuccess() throws IOException {
        String filePath = "src/test/resources/fixtures/nested/file1.json";

        String expected = Files.readString(Path.of(filePath)).trim();
        String actual = FileUtils.readFile(filePath);

        assertEquals(expected, actual);
    }

    @Test
    public void readYamlFileTestSuccess() throws IOException {
        String filePath = "src/test/resources/fixtures/nested/file1.yml";

        String expected = Files.readString(Path.of(filePath)).trim();
        String actual = FileUtils.readFile(filePath);

        assertEquals(expected, actual);
    }

    @Test
    public void fileNotFoundTest() {
        String invalidPath = "src/test/resources/fixtures/nonexistent.json";

        IOException exception = assertThrows(IOException.class, () -> {
            FileUtils.readFile(invalidPath);
        });

        assertTrue(exception.getMessage().contains("File is not found"));
    }

    @Test
    public void getFileExtensionSuccess() {
        assertEquals("json", FileUtils.getFileExtension("src/test/resources/fixtures/nested/file1.json"));
        assertEquals("yml", FileUtils.getFileExtension("src/test/resources/fixtures/flat/file1.yml"));
        assertEquals("yaml", FileUtils.getFileExtension("multi.dots.file.yaml"));
        assertEquals("txt", FileUtils.getFileExtension("src/test/resources/fixtures/nested/expected.txt"));
    }

    @Test
    public void getFileExtensionFailTest() {
        assertThrows(IllegalArgumentException.class, () -> FileUtils.getFileExtension("filewithnoformat"));
        assertThrows(IllegalArgumentException.class, () -> FileUtils.getFileExtension("fileendswithdot."));
    }
}
