package hexlet.code;

import java.util.ArrayList;
import java.util.Map;

public class Format {
    public static String format(Map<String, Difference> diffs) {
        var lines = new ArrayList<String>();
        lines.add("{");

        for (var entry : diffs.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();

            if (value.isUnchanged()) {
                lines.add("    " + key + ": " + value.getOldValue());
            } else if (value.isRemoved()) {
                lines.add("  - " + key + ": " + value.getOldValue());
            } else if (value.isAdded()) {
                lines.add("  + " + key + ": " + value.getNewValue());
            } else if (value.isChanged()) {
                lines.add("  - " + key + ": " + value.getOldValue());
                lines.add("  + " + key + ": " + value.getNewValue());
            }
        }

        lines.add("}");
        return String.join("\n", lines);
    }
}
