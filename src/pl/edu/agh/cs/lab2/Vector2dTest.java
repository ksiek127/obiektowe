import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void testToString() {
        Vector2d testVector1 = new Vector2d(2, 3);
        assertEquals("(2,3)", testVector1.toString());
        Vector2d testVector2 = new Vector2d(7, 4);
        assertEquals("(7,4)", testVector2.toString());
    }

    @Test
    void precedes() {
        Vector2d testVector1 = new Vector2d(2, 3);
        Vector2d testVector2 = new Vector2d(7, 4);
        Vector2d testVector3 = new Vector2d(1, 2);
        assertTrue(testVector1.precedes(testVector2));
        assertFalse(testVector1.precedes(testVector3));
    }

    @Test
    void follows() {
        Vector2d testVector1 = new Vector2d(2, 3);
        Vector2d testVector2 = new Vector2d(7, 4);
        Vector2d testVector3 = new Vector2d(1, 2);
        assertTrue(testVector1.follows(testVector3));
        assertFalse(testVector1.follows(testVector2));
    }

    @Test
    void upperRight() {
        Vector2d testVector1 = new Vector2d(2, 3);
        Vector2d testVector2 = new Vector2d(7, 1);
        Vector2d upperRight12 = new Vector2d(7, 3);
        assertEquals(upperRight12, testVector1.upperRight(testVector2));
    }

    @Test
    void lowerLeft() {
        Vector2d testVector1 = new Vector2d(2, 3);
        Vector2d testVector2 = new Vector2d(7, 1);
        Vector2d lowerLeft12 = new Vector2d(2, 1);
        assertEquals(lowerLeft12, testVector1.lowerLeft(testVector2));
    }

    @Test
    void add() {
        Vector2d testVector1 = new Vector2d(2, 3);
        Vector2d testVector2 = new Vector2d(7, 4);
        Vector2d sum12 = new Vector2d(9, 7);
        assertEquals(sum12, testVector1.add(testVector2));
    }

    @Test
    void subtract() {
        Vector2d testVector1 = new Vector2d(2, 3);
        Vector2d testVector2 = new Vector2d(7, 4);
        Vector2d difference12 = new Vector2d(-5, -1);
        assertEquals(difference12, testVector1.subtract(testVector2));
    }

    @Test
    void testEquals() {
        Vector2d testVector1 = new Vector2d(2, 3);
        Vector2d testVector2 = new Vector2d(2, 3);
        Vector2d testVector3 = new Vector2d(9, 7);
        assertTrue(testVector1.equals(testVector2));
        assertFalse(testVector1.equals(testVector3));
    }

    @Test
    void opposite() {
        Vector2d testVector1 = new Vector2d(2, 3);
        Vector2d oppositeVector = new Vector2d(-2, -3);
        assertEquals(oppositeVector, testVector1.opposite());
    }
}