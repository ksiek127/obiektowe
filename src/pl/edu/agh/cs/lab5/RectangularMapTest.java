import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest{

    @Test
    void canMoveToShouldAllowLegalMoves() {
        int testWidth = 10;
        int testHeight = 10;
        RectangularMap testMap = new RectangularMap(testWidth, testHeight);
        assertTrue(testMap.canMoveTo(new Vector2d(testWidth - 2, testHeight - 4)));
    }

    @Test
    void canMoveToShouldConsiderAMoveLegalIfItsOnTheEdge() {
        int testWidth = 10;
        int testHeight = 10;
        RectangularMap testMap = new RectangularMap(testWidth, testHeight);
        assertTrue(testMap.canMoveTo(new Vector2d(testWidth, testHeight)));
    }

    @Test
    void canMoveToShouldNotAllowIllegalMoves() {
        int testWidth = 10;
        int testHeight = 10;
        RectangularMap testMap = new RectangularMap(testWidth, testHeight);
        assertFalse(testMap.canMoveTo(new Vector2d(testWidth + 5, testHeight - 3)));
    }

    @Test
    void placeShouldReturnFalseIfThePositionWhereAnimalWasAboutToBePlacedIsBeyondTheMapBoundaries() {
        int testWidth = 10;
        int testHeight = 10;
        RectangularMap testMap = new RectangularMap(testWidth, testHeight);
        Animal experimentalRabbit = new Animal(testMap, new Vector2d(testWidth - 4, testHeight + 2));
        assertFalse(testMap.place(experimentalRabbit));
    }

    @Test
    void placeShouldReturnTrueIfTheAnimalIsPlacedOnTheMap() {
        int testWidth = 10;
        int testHeight = 10;
        RectangularMap testMap = new RectangularMap(testWidth, testHeight);
        Animal experimentalRabbit = new Animal(testMap, new Vector2d(testWidth - 4, testHeight - 2));
        assertTrue(testMap.place(experimentalRabbit));
    }

    @Test
    void placeShouldReturnFalseIfThePositionWhereAnimalWasAboutToBePlacedIsAlreadyOccupied() {
        int testWidth = 10;
        int testHeight = 10;
        RectangularMap testMap = new RectangularMap(testWidth, testHeight);
        Animal experimentalRabbit = new Animal(testMap, new Vector2d(testWidth - 4, testHeight - 2));
        Animal experimentalRabbitV2 = new Animal(testMap, new Vector2d(testWidth - 4, testHeight - 2));
        testMap.place(experimentalRabbit);
        assertFalse(testMap.place(experimentalRabbitV2));
    }

    @Test
    void isOccupiedShouldReturnTrueIfAPlaceIsOccupied() {
        int testWidth = 10;
        int testHeight = 10;
        RectangularMap testMap = new RectangularMap(testWidth, testHeight);
        Animal experimentalRabbit = new Animal(testMap, new Vector2d(testWidth - 4, testHeight - 2));
        testMap.place(experimentalRabbit);
        assertTrue(testMap.isOccupied(experimentalRabbit.getPosition()));
    }

    @Test
    void isOccupiedShouldReturnFalseIfAPlaceIsNotOccupied() {
        int testWidth = 10;
        int testHeight = 10;
        RectangularMap testMap = new RectangularMap(testWidth, testHeight);
        Animal experimentalRabbit = new Animal(testMap, new Vector2d(testWidth - 4, testHeight - 2));
        testMap.place(experimentalRabbit);
        assertFalse(testMap.isOccupied(new Vector2d(testWidth - 2, testHeight - 2)));
    }

    @Test
    void objectAtShouldReturnAnObjectAtAGivenPosition() {
        int testWidth = 10;
        int testHeight = 10;
        RectangularMap testMap = new RectangularMap(testWidth, testHeight);
        Animal experimentalRabbit = new Animal(testMap, new Vector2d(testWidth - 4, testHeight - 2));
        testMap.place(experimentalRabbit);
        assertEquals(testMap.objectAt(new Vector2d(testWidth - 4, testHeight - 2)), Optional.of(experimentalRabbit));
    }

    @Test
    void objectAtShouldReturnAnEmptyOptionalIfNothingIsThere() {
        int testWidth = 10;
        int testHeight = 10;
        RectangularMap testMap = new RectangularMap(testWidth, testHeight);
        Animal experimentalRabbit = new Animal(testMap, new Vector2d(testWidth - 4, testHeight - 2));
        assertEquals(testMap.objectAt(new Vector2d(testWidth - 4, testHeight - 1)), Optional.empty());
    }
}