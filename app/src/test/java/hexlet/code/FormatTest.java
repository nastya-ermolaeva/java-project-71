package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;

public class FormatTest {

    @Test
    public void formatWithAllDiffTypesTest() throws Exception {
        Path expectedPath = Paths.get("src", "test", "resources", "fixtures", "expected.txt");
        String expected = Files.readString(expectedPath).trim();

        Map<String, Difference> diffs = new LinkedHashMap<>();

        diffs.put("follow", new Difference(false, null));
        diffs.put("host", new Difference("hexlet.io", "hexlet.io"));
        diffs.put("proxy", new Difference("123.234.53.22", null));
        diffs.put("timeout", new Difference(50, 20));
        diffs.put("verbose", new Difference(null, true));

        String actual = Format.format(diffs);
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    public void formatEmptyDiffTest() {
        Map<String, Difference> diffs = new LinkedHashMap<>();
        String expected = "{\n}";
        assertEquals(expected.trim(), Format.format(diffs).trim());
    }
}
