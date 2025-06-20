package hexlet.code.diff;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.LinkedHashMap;

public final class DiffBuilder {

    private DiffBuilder() {
        // private constructor prevents from creating this class examples (Sonar made me do it)
    }

    public static Map<String, Difference> build(Map<String, Object> data1, Map<String, Object> data2) {

        Set<String> uniqueKeys = new TreeSet<>();
        uniqueKeys.addAll(data1.keySet());
        uniqueKeys.addAll(data2.keySet());

        Map<String, Difference> diffs = new LinkedHashMap<>();

        for (var key : uniqueKeys) {

            boolean oldKeyExists = data1.containsKey(key);
            boolean newKeyExists = data2.containsKey(key);
            Object value1 = oldKeyExists ? data1.get(key) : null;
            Object value2 = newKeyExists ? data2.get(key) : null;
            diffs.put(key, new Difference(value1, value2, oldKeyExists, newKeyExists));
        }

        return diffs;
    }
}
