package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class DifferTest {

    @Test
    public void gendiffTest() throws Exception {
        Path expectedPath = Paths.get("src", "test", "resources", "fixtures", "expected_flat.txt");
        String expected = Files.readString(expectedPath).trim();

        Path file1 = Paths.get("src", "test", "resources", "fixtures", "file1.json");
        Path file2 = Paths.get("src", "test", "resources", "fixtures", "file2.json");
        String actual = Differ.generate(file1.toString(), file2.toString()).trim();

        assertEquals(expected, actual);
    }
}
