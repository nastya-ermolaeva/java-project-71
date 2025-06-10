package hexlet.code;

import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.io.IOException;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {

        Map<String, Object> data1 = Parse.parseJson(Read.readFile(filepath1));
        Map<String, Object> data2 = Parse.parseJson(Read.readFile(filepath2));

        var keys1 = new ArrayList<String>(data1.keySet());
        var keys2 = new ArrayList<String>(data2.keySet());

        var allKeys = new ArrayList<String>();
        allKeys.addAll(keys1);
        allKeys.addAll(keys2);

        Collections.sort(allKeys);

        var uniqueKeys = allKeys.stream().distinct().toList();

        Map<String, Difference> diffs = new LinkedHashMap<>();

        for (var key : uniqueKeys) {

            Object value1 = data1.get(key);
            Object value2 = data2.get(key);
            diffs.put(key, new Difference(value1, value2));
        }

        return Format.format(diffs);
    }
}
