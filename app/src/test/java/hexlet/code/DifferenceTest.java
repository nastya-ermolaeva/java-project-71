package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferenceTest {

    @Test
    public void diffValuesTest() {
        Difference diff = new Difference("apple", "pear");
        assertTrue(diff.isChanged());
        assertFalse(diff.isUnchanged());
        assertFalse(diff.isAdded());
        assertFalse(diff.isRemoved());
    }

    @Test
    public void equalValuesTest() {
        Difference diff = new Difference(503, 503);
        assertFalse(diff.isChanged());
        assertTrue(diff.isUnchanged());
        assertFalse(diff.isAdded());
        assertFalse(diff.isRemoved());
    }

    @Test
    public void newValueTest() {
        Difference diff = new Difference(null, "watermelon");
        assertTrue(diff.isChanged());
        assertFalse(diff.isUnchanged());
        assertTrue(diff.isAdded());
        assertFalse(diff.isRemoved());
    }

    @Test
    void oldValueTest() {
        Difference diff = new Difference("cucumber", null);
        assertTrue(diff.isChanged());
        assertFalse(diff.isUnchanged());
        assertFalse(diff.isAdded());
        assertTrue(diff.isRemoved());
    }

    @Test
    void gettersTest() {
        String oldValue = "apple";
        String newValue = "pear";
        Difference diff = new Difference(oldValue, newValue);
        assertEquals(oldValue, diff.getOldValue());
        assertEquals(newValue, diff.getNewValue());
    }

    @Test
    void nullValuesTest() {
        Difference diff = new Difference(null, null);
        assertFalse(diff.isChanged());
        assertTrue(diff.isUnchanged());
        assertFalse(diff.isAdded());
        assertFalse(diff.isRemoved());
    }
}
