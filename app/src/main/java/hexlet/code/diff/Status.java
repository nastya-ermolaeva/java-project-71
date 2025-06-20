package hexlet.code.diff;

public final class Status {

    public static final String ADDED = "added";
    public static final String REMOVED = "removed";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private Status() {
        // private constructor prevents from creating this class examples (Sonar made me do it)
    }
}
