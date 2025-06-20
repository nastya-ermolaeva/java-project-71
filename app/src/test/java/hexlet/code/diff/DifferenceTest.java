package hexlet.code.diff;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import java.util.List;

class DifferenceTest {

    private static final Map<String, Object> OLD_VAL = Map.of("apple", 1, "banana", 2);
    private static final Map<String, Object> NEW_VAL = Map.of("cucumber", 1, "banana", 3);
    private static final List<Integer> LIST = List.of(1, 2, 3); // auto hexlet-check made me do it

    @Test
    void testChangedMaps() {
        Difference diff = new Difference(OLD_VAL, NEW_VAL, true, true);
        assertEquals(Status.CHANGED, diff.getStatus());
        assertEquals(OLD_VAL, diff.getOldValue());
        assertEquals(NEW_VAL, diff.getNewValue());
    }

    @Test
    void testUnchangedLists() {
        Difference diff = new Difference(LIST, LIST, true, true);
        assertEquals(Status.UNCHANGED, diff.getStatus());
    }

    @Test
    void testAddedMap() {
        Difference diff = new Difference(null, NEW_VAL, false, true);
        assertEquals(Status.ADDED, diff.getStatus());
    }

    @Test
    void testRemovedList() {
        List<String> oldValue = List.of("one", "two", "three");
        Difference diff = new Difference(oldValue, null, true, false);
        assertEquals(Status.REMOVED, diff.getStatus());
    }

    @Test
    void testChangedListToBoolean() {
        List<Character> oldValue = List.of('a', 'b', 'c');
        boolean newValue = false;
        Difference diff = new Difference(oldValue, newValue, true, true);
        assertEquals(Status.CHANGED, diff.getStatus());
    }

    @Test
    void testNullWithBothKeysPresent() {
        Difference diff = new Difference(null, null, true, true);
        assertEquals(Status.UNCHANGED, diff.getStatus());
    }

    @Test
    void testNullOnlyNewKey() {
        Difference diff = new Difference(null, null, false, true);
        assertEquals(Status.ADDED, diff.getStatus());
    }

    @Test
    void testNullOnlyOldKey() {
        Difference diff = new Difference(null, null, true, false);
        assertEquals(Status.REMOVED, diff.getStatus());
    }

    @Test
    void testNullNoKeys() {
        Difference diff = new Difference(null, null, false, false);
        assertEquals(Status.UNCHANGED, diff.getStatus());
    }
}
