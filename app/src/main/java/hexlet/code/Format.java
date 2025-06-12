package hexlet.code;

import java.util.ArrayList;
import java.util.Map;

public class Format {
    public static String stylish(Map<String, Difference> diffs) {
        var lines = new ArrayList<String>();
        lines.add("{");

        for (var entry : diffs.entrySet()) {
            var key = entry.getKey();
            var diff = entry.getValue();
            var oldValue = diff.getOldValue();
            var newValue = diff.getNewValue();
            var status = diff.getStatus();

            switch (status) {
                case "unchanged" -> lines.add("    " + key + ": " + intoString(oldValue));
                case "removed" -> lines.add("  - " + key + ": " + intoString(oldValue));
                case "added" -> lines.add("  + " + key + ": " + intoString(newValue));
                case "changed" -> {
                    lines.add("  - " + key + ": " + intoString(oldValue));
                    lines.add("  + " + key + ": " + intoString(newValue));
                }
                default -> throw new IllegalStateException("Unexpected status: " + status);
            }
        }

        lines.add("}");
        return String.join("\n", lines);
    }

    public static String intoString(Object value) {
        return value == null ? "null" : value.toString();
    }
}
