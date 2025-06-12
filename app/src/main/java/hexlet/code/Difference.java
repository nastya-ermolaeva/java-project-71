package hexlet.code;

import java.util.Objects;

public class Difference {
    private final Object oldValue;
    private final Object newValue;
    private final boolean hasOldKey;
    private final boolean hasNewKey;
    private final String status;

    public Difference(Object oldValue, Object newValue, boolean hasOldKey, boolean hasNewKey) {
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.hasOldKey = hasOldKey;
        this.hasNewKey = hasNewKey;
        this.status = getStatus();
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

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
