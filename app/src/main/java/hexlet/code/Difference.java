package hexlet.code;

import java.util.Objects;

public class Difference {
    private final Object oldValue;
    private final Object newValue;

    public Difference(Object oldValue, Object newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public boolean isChanged() {
        return !Objects.equals(oldValue, newValue);
    }

    public boolean isAdded() {
        return oldValue == null && newValue != null;
    }

    public boolean isRemoved() {
        return oldValue != null && newValue == null;
    }

    public boolean isUnchanged() {
        return Objects.equals(oldValue, newValue);
    }
}
