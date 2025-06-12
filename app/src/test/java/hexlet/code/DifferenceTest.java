package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import java.util.List;

public class DifferenceTest {

    @Test
    void testChangedMaps() {
        Map<String, Object> oldValue = Map.of("apple", 1, "banana", 2);
        Map<String, Object> newValue = Map.of("cucumber", 1, "banana", 3);

        Difference diff = new Difference(oldValue, newValue, true, true);
        assertEquals("changed", diff.getStatus());
        assertEquals(oldValue, diff.getOldValue());
        assertEquals(newValue, diff.getNewValue());
    }

    @Test
    void testUnchangedLists() {
        List<Integer> value = List.of(1, 2, 3);
        Difference diff = new Difference(value, value, true, true);
        assertEquals("unchanged", diff.getStatus());
    }

    @Test
    void testAddedMap() {
        Map<String, Object> newValue = Map.of("pear", true, "watermelon", 10);
        Difference diff = new Difference(null, newValue, false, true);
        assertEquals("added", diff.getStatus());
    }

    @Test
    void testRemovedList() {
        List<String> oldValue = List.of("one", "two", "three");
        Difference diff = new Difference(oldValue, null, true, false);
        assertEquals("removed", diff.getStatus());
    }

    @Test
    void testChangedListToBoolean() {
        List<Character> oldValue = List.of('a', 'b', 'c');
        boolean newValue = false;
        Difference diff = new Difference(oldValue, newValue, true, true);
        assertEquals("changed", diff.getStatus());
    }

    @Test
    void testNullWithBothKeysPresent() {
        Difference diff = new Difference(null, null, true, true);
        assertEquals("unchanged", diff.getStatus());
    }

    @Test
    void testNullOnlyNewKey() {
        Difference diff = new Difference(null, null, false, true);
        assertEquals("added", diff.getStatus());
    }

    @Test
    void testNullOnlyOldKey() {
        Difference diff = new Difference(null, null, true, false);
        assertEquals("removed", diff.getStatus());
    }

    @Test
    void testNullNoKeys() {
        Difference diff = new Difference(null, null, false, false);
        assertEquals("unchanged", diff.getStatus());
    }
}
