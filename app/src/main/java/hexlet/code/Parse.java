package hexlet.code;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class Parse {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> parseJson(String content) throws IOException {
        return mapper.readValue(content, Map.class);
    }
}
