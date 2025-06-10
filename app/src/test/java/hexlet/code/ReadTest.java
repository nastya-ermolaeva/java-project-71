package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class ReadTest {

    @Test
    public void readFileTestSuccess() throws IOException {
        String filePath = "src/test/resources/fixtures/file1.json";

        String expected = Files.readString(Path.of(filePath)).trim();
        String actual = Read.readFile(filePath);

        assertEquals(expected, actual);
    }

    @Test
    public void fileNotFoundTest() {
        String invalidPath = "src/test/resources/fixtures/nonexistent.json";

        IOException exception = assertThrows(IOException.class, () -> {
            Read.readFile(invalidPath);
        });

        assertTrue(exception.getMessage().contains("File is not found"));
    }
}
