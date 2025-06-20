package hexlet.code.diff;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class DiffBuilderTest {

    private static final int MAP_SIZE = 3;

    @Test
    void buildMapOfDiffsTest() {
        Map<String, Object> data1 = Map.of(
                "apple", "sugar",
                "banana", true
        );
        Map<String, Object> data2 = Map.of(
                "banana", false,
                "cream", "butter"
        );

        Map<String, Difference> actual = DiffBuilder.build(data1, data2);

        assertEquals(MAP_SIZE, actual.size());

        Difference diffApple = actual.get("apple");
        assertEquals("sugar", diffApple.getOldValue());
        assertNull(diffApple.getNewValue());
        assertTrue(diffApple.hasOldKey());
        assertFalse(diffApple.hasNewKey());

        Difference diffBanana = actual.get("banana");
        assertEquals(true, diffBanana.getOldValue());
        assertEquals(false, diffBanana.getNewValue());
        assertTrue(diffBanana.hasOldKey());
        assertTrue(diffBanana.hasNewKey());

        Difference diffCream = actual.get("cream");
        assertNull(diffCream.getOldValue());
        assertEquals("butter", diffCream.getNewValue());
        assertFalse(diffCream.hasOldKey());
        assertTrue(diffCream.hasNewKey());
    }

    @Test
    void buildEmptyMapTest() {
        Map<String, Object> data1 = Map.of();
        Map<String, Object> data2 = Map.of();

        Map<String, Difference> actual = DiffBuilder.build(data1, data2);
        assertTrue(actual.isEmpty());
    }
}
