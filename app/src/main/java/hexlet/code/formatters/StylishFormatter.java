package hexlet.code.formatters;

import hexlet.code.diff.Difference;
import java.util.ArrayList;
import java.util.Map;

public final class StylishFormatter {

    private StylishFormatter() {
        // private constructor prevents from creating this class examples (Sonar made me do it)
    }

    public static String format(Map<String, Difference> diffs) {
        var lines = new ArrayList<String>();
        lines.add("{");

        for (var entry : diffs.entrySet()) {
            var key = entry.getKey();
            var diff = entry.getValue();
            var oldValue = diff.getOldValue();
            var newValue = diff.getNewValue();
            var status = diff.getStatus();

            switch (status) {
                case Difference.UNCHANGED -> lines.add("    " + key + ": " + intoString(oldValue));
                case Difference.REMOVED -> lines.add("  - " + key + ": " + intoString(oldValue));
                case Difference.ADDED -> lines.add("  + " + key + ": " + intoString(newValue));
                case Difference.CHANGED -> {
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
