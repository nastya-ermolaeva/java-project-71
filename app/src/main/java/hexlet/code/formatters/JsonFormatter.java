package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Difference;
import java.util.Map;

public final class JsonFormatter {

    private JsonFormatter() {
        // private constructor prevents from creating this class examples (Sonar made me do it)
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String format(Map<String, Difference> diffs) throws JsonProcessingException {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(diffs);
    }
}
