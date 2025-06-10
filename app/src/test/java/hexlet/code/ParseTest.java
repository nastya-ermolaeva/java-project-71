package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParseTest {

    @Test
    public void parseJsonTestSuccess() throws IOException {
        String json = """
               {
                  "host": "hexlet.io",
                  "timeout": 50,
                  "proxy": "123.234.53.22",
                  "follow": false
               }
            """;

        Map<String, Object> result = Parse.parseJson(json);

        assertEquals("hexlet.io", result.get("host"));
        assertEquals(50, result.get("timeout"));
        assertEquals("123.234.53.22", result.get("proxy"));
        assertEquals(false, result.get("follow"));
    }

    @Test
    void parseJsonFailTest() {
        String invalidJson = "{invalid json}";

        assertThrows(IOException.class, () -> Parse.parseJson(invalidJson));
    }
}
