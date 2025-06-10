package hexlet.code;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;

public class Parse {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Map<String, Object> parseJson(String content) throws IOException {
        return MAPPER.readValue(content, new TypeReference<Map<String, Object>>() { });
    }
}
