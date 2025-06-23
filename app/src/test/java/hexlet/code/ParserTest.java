package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    private static final int TIMEOUT_VAL = 50; // auto hexlet-check made me do it

    @Test
    void parseJsonTestSuccess() throws IOException {

        Path jsonPath = Paths.get("src", "test", "resources", "fixtures", "nested", "json_parsertest.txt");
        String json = Files.readString(jsonPath).trim();

        Map<String, Object> result = Parser.parse(json, "json");

        assertEquals("hexlet.io", result.get("host"));

        Map<String, Object> settings = (Map<String, Object>) result.get("settings");
        assertEquals(TIMEOUT_VAL, settings.get("timeout"));
        assertEquals("123.234.53.22", settings.get("proxy"));

        List<String> features = (List<String>) result.get("features");
        assertEquals(List.of("follow", "verbose"), features);
    }

    @Test
    void parseJsonFailTest() {
        String invalidJson = "{ invalid json }";

        assertThrows(IOException.class, () -> Parser.parse(invalidJson, "json"));
    }

    @Test
    void parseYamlTestSuccess() throws IOException {

        Path yamlPath = Paths.get("src", "test", "resources", "fixtures", "nested", "yaml_parsertest.txt");
        String yaml = Files.readString(yamlPath).trim();

        Map<String, Object> result = Parser.parse(yaml, "yml");

        assertEquals("hexlet.io", result.get("host"));

        Map<String, Object> settings = (Map<String, Object>) result.get("settings");
        assertEquals(TIMEOUT_VAL, settings.get("timeout"));
        assertEquals("123.234.53.22", settings.get("proxy"));

        List<String> features = (List<String>) result.get("features");
        assertEquals(List.of("follow", "verbose"), features);
    }

    @Test
    void parseYamlFailTest() {
        String invalidYaml = "invalid: :yaml: hex";

        assertThrows(IOException.class, () -> Parser.parse(invalidYaml, "yaml"));
    }

    @Test
    void parseUnsupportedFormatTest() {
        String random = "some random string";

        assertThrows(IllegalArgumentException.class, () -> Parser.parse(random, "txt"));
    }
}
