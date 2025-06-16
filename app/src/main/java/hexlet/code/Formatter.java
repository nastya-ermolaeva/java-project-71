package hexlet.code;

import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.JsonFormatter;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;

public final class Formatter {

    private Formatter() {
        // private constructor prevents from creating this class examples (Sonar made me do it)
    }

    public static String format(Map<String, Difference> diffs, String format) throws JsonProcessingException {
        return switch (format) {
            case "stylish" -> StylishFormatter.format(diffs);
            case "plain" -> PlainFormatter.format(diffs);
            case "json" -> JsonFormatter.format(diffs);
            default -> throw new IllegalArgumentException("Unexpected format: " + format);
        };
    }
}
