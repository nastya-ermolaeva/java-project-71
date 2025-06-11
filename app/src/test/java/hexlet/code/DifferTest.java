package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class DifferTest {

    @Test
    public void gendiffJsonTest() throws Exception {
        Path expectedPath = Paths.get("src", "test", "resources", "fixtures", "expected.txt");
        String expected = Files.readString(expectedPath).trim();

        Path file1 = Paths.get("src", "test", "resources", "fixtures", "file1.json");
        Path file2 = Paths.get("src", "test", "resources", "fixtures", "file2.json");
        String actual = Differ.generate(file1.toString(), file2.toString()).trim();

        assertEquals(expected, actual);
    }

    @Test
    public void invalidJsonTest() {
        String file1 = "src/test/resources/fixtures/invalid.json";
        String file2 = "src/test/resources/fixtures/file2.json";

        assertThrows(IOException.class, () -> Differ.generate(file1, file2));
    }

    @Test
    public void gendiffYamlTest() throws Exception {
        Path expectedPath = Paths.get("src", "test", "resources", "fixtures", "expected.txt");
        String expected = Files.readString(expectedPath).trim();

        Path file1 = Paths.get("src", "test", "resources", "fixtures", "file1.yml");
        Path file2 = Paths.get("src", "test", "resources", "fixtures", "file2.yml");
        String actual = Differ.generate(file1.toString(), file2.toString()).trim();

        assertEquals(expected, actual);
    }

    @Test
    public void invalidYamlTest() {
        String file1 = "src/test/resources/fixtures/invalid.yml";
        String file2 = "src/test/resources/fixtures/file2.yml";

        assertThrows(IOException.class, () -> Differ.generate(file1, file2));
    }

    @Test
    public void gendiffJsonYamlTest() throws Exception {
        Path expectedPath = Paths.get("src", "test", "resources", "fixtures", "expected.txt");
        String expected = Files.readString(expectedPath).trim();

        Path file1 = Paths.get("src", "test", "resources", "fixtures", "file1.json");
        Path file2 = Paths.get("src", "test", "resources", "fixtures", "file2.yml");
        String actual = Differ.generate(file1.toString(), file2.toString()).trim();

        assertEquals(expected, actual);
    }

    @Test
    public void unsupportedExtensionTest() {
        String file1 = "src/test/resources/fixtures/file.unsupported";
        String file2 = "src/test/resources/fixtures/file2.json";

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                Differ.generate(file1, file2)
        );

        assertTrue(e.getMessage().contains("Unsupported file format"));
    }

    @Test
    public void fileNotFoundTest() {
        String file1 = "src/test/resources/fixtures/nonexistent1.json";
        String file2 = "src/test/resources/fixtures/nonexistent2.json";

        Exception e = assertThrows(IOException.class, () ->
                Differ.generate(file1, file2)
        );

        assertTrue(e.getMessage().contains("File is not found"));
    }
}
