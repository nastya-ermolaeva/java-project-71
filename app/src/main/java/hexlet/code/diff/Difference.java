package hexlet.code.diff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Difference {
    private final Object oldValue;
    private final Object newValue;
    private final boolean hasOldKey;
    private final boolean hasNewKey;
    private final String status;

    public static final String ADDED = "added";
    public static final String REMOVED = "removed";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    public Difference(Object oldValue, Object newValue, boolean hasOldKey, boolean hasNewKey, String status) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.hasOldKey = hasOldKey;
        this.hasNewKey = hasNewKey;
        this.status = status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    @JsonIgnore
    public boolean hasOldKey() {
        return hasOldKey;
    }

    @JsonIgnore
    public boolean hasNewKey() {
        return hasNewKey;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }
}
