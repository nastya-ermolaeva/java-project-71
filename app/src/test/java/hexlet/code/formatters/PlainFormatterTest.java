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

class PlainFormatterTest {

    private static final List<Character> CHARS1_VAL = List.of('a', 'b', 'c');
    private static final int ID_OLD = 45;
    private static final List<Integer> NUMBERS1_VAL = List.of(1, 2, 3, 4);
    private static final List<Integer> NUMBERS2_OLD = List.of(2, 3, 4, 5);
    private static final List<Integer> NUMBERS2_NEW = List.of(22, 33, 44, 55);
    private static final List<Integer> NUMBERS3_OLD = List.of(3, 4, 5);
    private static final List<Integer> NUMBERS4_NEW = List.of(4, 5, 6);
    private static final int SETTING2_OLD = 200;
    private static final int SETTING2_NEW = 300;
    private static final int FOR_EXTRA = 10;
    // auto hexlet-check made me do it

    @Test
    void formatWithNestedStructuresTest() throws IOException {
        Path expectedPath = Paths.get("src", "test", "resources", "fixtures", "nested", "expected_plain.txt");
        String expected = Files.readString(expectedPath).trim();

        Map<String, Difference> diffs = new TreeMap<>();

        diffs.put("chars1", new Difference(CHARS1_VAL, CHARS1_VAL, true, true, Difference.UNCHANGED));
        diffs.put("chars2", new Difference(List.of('d', 'e', 'f'), false, true, true, Difference.CHANGED));
        diffs.put("checked", new Difference(false, true, true, true, Difference.CHANGED));
        diffs.put("default", new Difference(null, List.of("value1", "value2"), true, true, Difference.CHANGED));
        diffs.put("id", new Difference(ID_OLD, null, true, true, Difference.CHANGED));
        diffs.put("key1", new Difference("value1", null, true, false, Difference.REMOVED));
        diffs.put("key2", new Difference(null, "value2", false, true, Difference.ADDED));
        diffs.put("numbers1", new Difference(NUMBERS1_VAL, NUMBERS1_VAL, true, true, Difference.UNCHANGED));
        diffs.put("numbers2", new Difference(NUMBERS2_OLD, NUMBERS2_NEW, true, true, Difference.CHANGED));
        diffs.put("numbers3", new Difference(NUMBERS3_OLD, null, true, false, Difference.REMOVED));
        diffs.put("numbers4", new Difference(null, NUMBERS4_NEW, false, true, Difference.ADDED));

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("nestedKey", "value");
        map.put("isNested", true);
        diffs.put("obj1", new Difference(null, map, false, true, Difference.ADDED));

        diffs.put("setting1", new Difference("Some value", "Another value", true, true, Difference.CHANGED));
        diffs.put("setting2", new Difference(SETTING2_OLD, SETTING2_NEW, true, true, Difference.CHANGED));
        diffs.put("setting3", new Difference(true, "none", true, true, Difference.CHANGED));

        String actual = PlainFormatter.format(diffs);
        assertEquals(expected.trim(), actual.trim());
    }

    @Test
    void formatWithExtraordinaryCasesTest() throws IOException {

        Path expectedPath = Paths.get("src/test/resources/fixtures/nested/plainformatter_extratest.txt");
        String expected = Files.readString(expectedPath).trim();

        Map<String, Difference> diffs = new TreeMap<>();

        diffs.put("numbers", new Difference(FOR_EXTRA, "10", true, true, Difference.CHANGED));
        diffs.put("nullTest", new Difference(null, "nonNull", true, true, Difference.CHANGED));
        diffs.put("removedKey", new Difference("gone", null, true, false, Difference.REMOVED));

        String actual = PlainFormatter.format(diffs).trim();
        assertEquals(expected, actual);
    }


    @Test
    void formatEmptyDiffTest() {
        Map<String, Difference> diffs = new LinkedHashMap<>();
        String expected = "";
        String actual = PlainFormatter.format(diffs);
        assertEquals(expected.trim(), actual.trim());
    }
}
