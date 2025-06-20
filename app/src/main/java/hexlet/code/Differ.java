package hexlet.code;

import hexlet.code.diff.Difference;
import hexlet.code.diff.DiffBuilder;
import java.util.Map;
import java.util.LinkedHashMap;
import java.io.IOException;

public final class Differ {

    private Differ() {
        // private constructor prevents from creating this class examples (Sonar made me do it)
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {

        Map<String, Object> data1 = Parser.parse(FileUtils.readFile(filepath1), FileUtils.getFileExtension(filepath1));
        Map<String, Object> data2 = Parser.parse(FileUtils.readFile(filepath2), FileUtils.getFileExtension(filepath2));

        Map<String, Difference> diffs = new LinkedHashMap<>();
        diffs = DiffBuilder.build(data1, data2);

        return Formatter.format(diffs, format);
    }
}
