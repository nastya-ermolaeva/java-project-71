package hexlet.code.formatters;

import hexlet.code.diff.Difference;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public final class PlainFormatter {

    private PlainFormatter() {
        // private constructor prevents from creating this class examples (Sonar made me do it)
    }

    public static String format(Map<String, Difference> diffs) {
        List<String> lines = new ArrayList<>();

        for (var entry : diffs.entrySet()) {
            var key = entry.getKey();
            var diff = entry.getValue();
            var status = diff.getStatus();
            var oldValue = intoPlainString(diff.getOldValue());
            var newValue = intoPlainString(diff.getNewValue());

            switch (status) {
                case Difference.ADDED -> lines.add("Property '" + key + "' was added with value: " + newValue);
                case Difference.REMOVED -> lines.add("Property '" + key + "' was removed");
                case Difference.CHANGED -> lines.add("Property '" + key + "' was updated. From "
                        + oldValue + " to " + newValue);
                case Difference.UNCHANGED -> { } // doesn't need to be printed on the screen
                default -> throw new IllegalStateException("Unexpected status: " + status);
            }
        }

        return String.join("\n", lines);
    }

    private static String intoPlainString(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof Map || value instanceof Iterable) {
            return "[complex value]";
        }

        return value.toString();
    }
}
