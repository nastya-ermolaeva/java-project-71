package hexlet.code.formatters;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.io.IOException;
import hexlet.code.Difference;

public class PlainFormatterTest {

    @Test
    public void formatWithNestedStructuresTest() throws IOException {
        Path expectedPath = Paths.get("src", "test", "resources", "fixtures", "nested", "expected_plain.txt");
        String expected = Files.readString(expectedPath).trim();

        Map<String, Difference> diffs = new TreeMap<>();

        diffs.put("chars1", new Difference(List.of('a', 'b', 'c'), List.of('a', 'b', 'c'), true, true));
        diffs.put("chars2", new Difference(List.of('d', 'e', 'f'), false, true, true));
        diffs.put("checked", new Difference(false, true, true, true));
        diffs.put("default", new Difference(null, List.of("value1", "value2"), true, true));
        diffs.put("id", new Difference(45, null, true, true));
        diffs.put("key1", new Difference("value1", null, true, false));
        diffs.put("key2", new Difference(null, "value2", false, true));
        diffs.put("numbers1", new Difference(List.of(1, 2, 3, 4), List.of(1, 2, 3, 4), true, true));
        diffs.put("numbers2", new Difference(List.of(2, 3, 4, 5), List.of(22, 33, 44, 55), true, true));
        diffs.put("numbers3", new Difference(List.of(3, 4, 5), null, true, false));
        diffs.put("numbers4", new Difference(null, List.of(4, 5, 6), false, true));

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("nestedKey", "value");
        map.put("isNested", true);
        diffs.put("obj1", new Difference(null, map, false, true));

        diffs.put("setting1", new Difference("Some value", "Another value", true, true));
        diffs.put("setting2", new Difference(200, 300, true, true));
        diffs.put("setting3", new Difference(true, "none", true, true));

        String actual = PlainFormatter.format(diffs);
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    public void formatWithExtraordinaryCasesTest() {
        Map<String, Difference> diffs = new TreeMap<>();

        diffs.put("numbers", new Difference(10, "10", true, true));
        diffs.put("nullTest", new Difference(null, "nonNull", true, true));
        diffs.put("removedKey", new Difference("gone", null, true, false));

        String expected = """
            Property 'nullTest' was updated. From null to 'nonNull'
            Property 'numbers' was updated. From 10 to '10'
            Property 'removedKey' was removed
            """;

        String actual = PlainFormatter.format(diffs);
        assertEquals(expected.trim(), actual.trim());
    }


    @Test
    public void formatEmptyDiffTest() {
        Map<String, Difference> diffs = new LinkedHashMap<>();
        String expected = "";
        String actual = PlainFormatter.format(diffs);
        assertEquals(expected.trim(), actual.trim());
    }
}
