package hexlet.code;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Difference {
    private final Object oldValue;
    private final Object newValue;
    private final boolean hasOldKey;
    private final boolean hasNewKey;

    public Difference(Object oldValue, Object newValue, boolean hasOldKey, boolean hasNewKey) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.hasOldKey = hasOldKey;
        this.hasNewKey = hasNewKey;
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
        if (!hasOldKey && hasNewKey) {
            return "added";
        }

        if (hasOldKey && !hasNewKey) {
            return "removed";
        }

        if (Objects.equals(oldValue, newValue)) {
            return "unchanged";
        }

        return "changed";
    }
}
