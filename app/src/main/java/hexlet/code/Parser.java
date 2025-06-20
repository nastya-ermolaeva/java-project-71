package hexlet.code;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;

public final class Parser {

    private Parser() {
        // private constructor prevents from creating this class examples (Sonar made me do it)
    }

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parse(String content, String dataFormat) throws IOException {
        return switch (dataFormat.toLowerCase()) {
            case "json" -> JSON_MAPPER.readValue(content, new TypeReference<Map<String, Object>>() { });
            case "yml", "yaml" -> YAML_MAPPER.readValue(content, new TypeReference<Map<String, Object>>() { });
            default -> throw new IllegalArgumentException("Unsupported data format: " + dataFormat);
        };
    }
}
