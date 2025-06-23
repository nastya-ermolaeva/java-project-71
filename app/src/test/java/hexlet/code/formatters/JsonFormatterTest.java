package hexlet.code.formatters;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.List;
import hexlet.code.diff.Difference;
import hexlet.code.diff.Status;

class JsonFormatterTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final List<Character> CHARS1_VAL = List.of('a', 'b', 'c');
    private static final int ID_OLD = 45;
    private static final List<Integer> NUMBERS1_VAL = List.of(1, 2, 3, 4);
    private static final List<Integer> NUMBERS2_OLD = List.of(2, 3, 4, 5);
    private static final List<Integer> NUMBERS2_NEW = List.of(22, 33, 44, 55);
    private static final List<Integer> NUMBERS3_OLD = List.of(3, 4, 5);
    private static final List<Integer> NUMBERS4_NEW = List.of(4, 5, 6);
    private static final int SETTING2_OLD = 200;
    private static final int SETTING2_NEW = 300;
    // auto hexlet-check made me do it

    @Test
    void formatWithNestedStructuresTest() throws Exception {
        Path expectedPath = Paths.get("src", "test", "resources", "fixtures", "nested", "expected_json.txt");
        String expected = Files.readString(expectedPath).trim();

        Map<String, Difference> diffs = new TreeMap<>();

        diffs.put("chars1", new Difference(CHARS1_VAL, CHARS1_VAL, true, true, Status.UNCHANGED));
        diffs.put("chars2", new Difference(List.of('d', 'e', 'f'), false, true, true, Status.CHANGED));
        diffs.put("checked", new Difference(false, true, true, true, Status.CHANGED));
        diffs.put("default", new Difference(null, List.of("value1", "value2"), true, true, Status.CHANGED));
        diffs.put("id", new Difference(ID_OLD, null, true, true, Status.CHANGED));
        diffs.put("key1", new Difference("value1", null, true, false, Status.REMOVED));
        diffs.put("key2", new Difference(null, "value2", false, true, Status.ADDED));
        diffs.put("numbers1", new Difference(NUMBERS1_VAL, NUMBERS1_VAL, true, true, Status.UNCHANGED));
        diffs.put("numbers2", new Difference(NUMBERS2_OLD, NUMBERS2_NEW, true, true, Status.CHANGED));
        diffs.put("numbers3", new Difference(NUMBERS3_OLD, null, true, false, Status.REMOVED));
        diffs.put("numbers4", new Difference(null, NUMBERS4_NEW, false, true, Status.ADDED));

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("nestedKey", "value");
        map.put("isNested", true);
        diffs.put("obj1", new Difference(null, map, false, true, Status.ADDED));

        diffs.put("setting1", new Difference("Some value", "Another value", true, true, Status.CHANGED));
        diffs.put("setting2", new Difference(SETTING2_OLD, SETTING2_NEW, true, true, Status.CHANGED));
        diffs.put("setting3", new Difference(true, "none", true, true, Status.CHANGED));

        String actual = JsonFormatter.format(diffs).trim();
        assertEquals(MAPPER.readTree(expected), MAPPER.readTree(actual));
    }

    @Test
    void formatEmptyDiffTest() throws JsonProcessingException {
        Map<String, Difference> diffs = new LinkedHashMap<>();
        String expected = "{}";
        String actual = JsonFormatter.format(diffs);
        assertEquals(MAPPER.readTree(expected), MAPPER.readTree(actual));
    }
}
