package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

class DifferTest {

    @Test
    void gendiffJsonYamlTest() throws Exception {
        Path expectedPath = Paths.get("src", "test", "resources", "fixtures", "nested", "expected_stylish.txt");
        String expected = Files.readString(expectedPath).trim();

        Path file1 = Paths.get("src", "test", "resources", "fixtures", "nested", "file1.json");
        Path file2 = Paths.get("src", "test", "resources", "fixtures", "nested", "file2.yml");
        String actual = Differ.generate(file1.toString(), file2.toString()).trim();

        assertEquals(expected, actual);
    }

    @Test
    void gendiffPlainFormatTest() throws Exception {
        Path expectedPath = Paths.get("src", "test", "resources", "fixtures", "nested", "expected_plain.txt");
        String expected = Files.readString(expectedPath).trim();

        Path file1 = Paths.get("src", "test", "resources", "fixtures", "nested", "file1.json");
        Path file2 = Paths.get("src", "test", "resources", "fixtures", "nested", "file2.json");
        String actual = Differ.generate(file1.toString(), file2.toString(), "plain").trim();

        assertEquals(expected, actual);
    }

    @Test
    void gendiffStylishFormatTest() throws Exception {
        Path expectedPath = Paths.get("src", "test", "resources", "fixtures", "nested", "expected_stylish.txt");
        String expected = Files.readString(expectedPath).trim();

        Path file1 = Paths.get("src", "test", "resources", "fixtures", "nested", "file1.yml");
        Path file2 = Paths.get("src", "test", "resources", "fixtures", "nested", "file2.yml");
        String actual = Differ.generate(file1.toString(), file2.toString(), "stylish").trim();

        assertEquals(expected, actual);
    }

    @Test
    void gendiffJsonFormatTest() throws Exception {
        Path expectedPath = Paths.get("src", "test", "resources", "fixtures", "nested", "expected_json.txt");
        String expected = Files.readString(expectedPath).trim();

        Path file1 = Paths.get("src", "test", "resources", "fixtures", "nested", "file1.json");
        Path file2 = Paths.get("src", "test", "resources", "fixtures", "nested", "file2.yml");
        String actual = Differ.generate(file1.toString(), file2.toString(), "json").trim();

        assertEquals(expected, actual);
    }

    @Test
    void invalidJsonTest() {
        String file1 = "src/test/resources/fixtures/nested/invalid.json";
        String file2 = "src/test/resources/fixtures/nested/file2.json";

        assertThrows(IOException.class, () -> Differ.generate(file1, file2));
    }

    @Test
    void invalidYamlTest() {
        String file1 = "src/test/resources/fixtures/nested/invalid.yml";
        String file2 = "src/test/resources/fixtures/nested/file2.yml";

        assertThrows(IOException.class, () -> Differ.generate(file1, file2));
    }

    @Test
    void unsupportedExtensionTest() {
        String file1 = "src/test/resources/fixtures/nested/file.unsupported";
        String file2 = "src/test/resources/fixtures/nested/file2.json";

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                Differ.generate(file1, file2)
        );

        assertTrue(e.getMessage().contains("Unsupported file format"));
    }

    @Test
    void fileNotFoundTest() {
        String file1 = "src/test/resources/fixtures/nonexistent1.json";
        String file2 = "src/test/resources/fixtures/nonexistent2.json";

        Exception e = assertThrows(IOException.class, () ->
                Differ.generate(file1, file2)
        );

        assertTrue(e.getMessage().contains("File is not found"));
    }
}
