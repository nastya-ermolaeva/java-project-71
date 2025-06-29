package hexlet.code;

import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.JsonFormatter;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.LinkedHashMap;
import hexlet.code.diff.Difference;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FormatterTest {

    @Test
    void testStylishFormatter() throws Exception {
        Map<String, Difference> diffs = new LinkedHashMap<>();
        diffs.put("key", new Difference("apple", "pear", true, true, Difference.CHANGED));

        String expected = StylishFormatter.format(diffs);
        String actual = Formatter.format(diffs, "stylish");

        assertEquals(expected, actual);
    }

    @Test
    void testPlainFormatter() throws Exception {
        Map<String, Difference> diffs = new LinkedHashMap<>();
        diffs.put("key", new Difference("watermelon", "melon", true, true, Difference.CHANGED));

        String expected = PlainFormatter.format(diffs);
        String actual = Formatter.format(diffs, "plain");

        assertEquals(expected, actual);
    }

    @Test
    void testJsonFormatter() throws Exception {
        Map<String, Difference> diffs = new LinkedHashMap<>();
        diffs.put("key", new Difference("sugar", "salt", true, true, Difference.CHANGED));

        String expected = JsonFormatter.format(diffs);
        String actual = Formatter.format(diffs, "json");

        assertEquals(expected, actual);
    }

    @Test
    void testInvalidFormat() {
        Map<String, Difference> diffs = new LinkedHashMap<>();
        diffs.put("key", new Difference("bread", "milk", true, true, Difference.CHANGED));

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            Formatter.format(diffs, "invalid");
        });

        assertEquals("Unexpected format: invalid", e.getMessage());
    }
}
