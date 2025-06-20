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
import hexlet.code.diff.Difference;

class StylishFormatterTest {

    private static final int ID_OLD = 45;
    private static final List<Integer> NUMBERS1_VAL = List.of(1, 2, 3, 4);
    private static final List<Integer> NUMBERS2_OLD = List.of(2, 3, 4, 5);
    private static final List<Integer> NUMBERS2_NEW = List.of(22, 33, 44, 55);
    private static final List<Integer> NUMBERS3_OLD = List.of(3, 4, 5);
    private static final List<Integer> NUMBERS4_NEW = List.of(4, 5, 6);
    private static final int SETTING2_OLD = 200;
    private static final int SETTING2_NEW = 300;
    private static final int FOR_EXTRA = 12;
    // auto hexlet-check made me do it

    @Test
    void formatWithNestedStructuresTest() throws IOException {
        Path expectedPath = Paths.get("src", "test", "resources", "fixtures", "nested", "expected_stylish.txt");
        String expected = Files.readString(expectedPath).trim();

        Map<String, Difference> diffs = new TreeMap<>();

        diffs.put("chars1", new Difference(List.of('a', 'b', 'c'), List.of('a', 'b', 'c'), true, true));
        diffs.put("chars2", new Difference(List.of('d', 'e', 'f'), false, true, true));
        diffs.put("checked", new Difference(false, true, true, true));
        diffs.put("default", new Difference(null, List.of("value1", "value2"), true, true));
        diffs.put("id", new Difference(ID_OLD, null, true, true));
        diffs.put("key1", new Difference("value1", null, true, false));
        diffs.put("key2", new Difference(null, "value2", false, true));
        diffs.put("numbers1", new Difference(NUMBERS1_VAL, NUMBERS1_VAL, true, true));
        diffs.put("numbers2", new Difference(NUMBERS2_OLD, NUMBERS2_NEW, true, true));
        diffs.put("numbers3", new Difference(NUMBERS3_OLD, null, true, false));
        diffs.put("numbers4", new Difference(null, NUMBERS4_NEW, false, true));

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("nestedKey", "value");
        map.put("isNested", true);
        diffs.put("obj1", new Difference(null, map, false, true));

        diffs.put("setting1", new Difference("Some value", "Another value", true, true));
        diffs.put("setting2", new Difference(SETTING2_OLD, SETTING2_NEW, true, true));
        diffs.put("setting3", new Difference(true, "none", true, true));

        String actual = StylishFormatter.format(diffs);
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    void testExtraordinaryCases() {
        Map<String, Difference> diffs = new TreeMap<>();
        diffs.put("CAPS", new Difference(true, false, true, true));
        diffs.put("key", new Difference("12", FOR_EXTRA, true, true));
        diffs.put("  spaced", new Difference("one", "two", true, true));

        String expected = """
            {
              -   spaced: one
              +   spaced: two
              - CAPS: true
              + CAPS: false
              - key: 12
              + key: 12
            }""";

        String actual = StylishFormatter.format(diffs);
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    void formatEmptyDiffTest() {
        Map<String, Difference> diffs = new LinkedHashMap<>();
        String expected = "{\n}";
        String actual = StylishFormatter.format(diffs);
        assertEquals(expected.trim(), actual.trim());
    }
}
