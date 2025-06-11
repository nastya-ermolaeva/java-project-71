package hexlet.code;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.LinkedHashMap;
import java.io.IOException;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {

        Map<String, Object> data1 = Parse.parse(FileUtils.readFile(filepath1), FileUtils.getFileExtension(filepath1));
        Map<String, Object> data2 = Parse.parse(FileUtils.readFile(filepath2), FileUtils.getFileExtension(filepath2));

        Set<String> uniqueKeys = new TreeSet<>();
        uniqueKeys.addAll(data1.keySet());
        uniqueKeys.addAll(data2.keySet());

        Map<String, Difference> diffs = new LinkedHashMap<>();

        for (var key : uniqueKeys) {

            Object value1 = data1.get(key);
            Object value2 = data2.get(key);
            diffs.put(key, new Difference(value1, value2));
        }

        return Format.format(diffs);
    }
}
