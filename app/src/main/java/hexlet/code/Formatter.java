package hexlet.code;

import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;
import java.util.Map;

public class Formatter {

    public static String format(Map<String, Difference> diffs, String format) {
        return switch (format) {
            case "stylish" -> StylishFormatter.format(diffs);
            case "plain" -> PlainFormatter.format(diffs);
            default -> throw new IllegalArgumentException("Unexpected format: " + format);
        };
    }
}
