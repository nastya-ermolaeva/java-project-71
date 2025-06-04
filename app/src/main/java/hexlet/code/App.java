package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
    name = "gendiff",
    mixinStandardHelpOptions = true,
    version = "1.0",
    description = "Compares two configuration files and shows a difference."
)

public class App implements Runnable {

    @Override
    public void run() {
        System.out.printf("Hello World!");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
