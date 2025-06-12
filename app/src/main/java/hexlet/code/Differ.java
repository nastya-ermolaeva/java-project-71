package hexlet.code;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.LinkedHashMap;
import java.io.IOException;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {

        Map<String, Object> data1 = Parse.parse(FileUtils.readFile(filepath1), FileUtils.getFileExtension(filepath1));
        Map<String, Object> data2 = Parse.parse(FileUtils.readFile(filepath2), FileUtils.getFileExtension(filepath2));

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

        return switch (format) {
            case "stylish" -> Format.stylish(diffs);
            default -> Format.stylish(diffs);
        };
    }
}
