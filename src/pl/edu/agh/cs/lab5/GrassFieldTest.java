import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void placeShouldReturnTrueIfTheAnimalIsPlacedOnTheMap() {
        GrassField testField = new GrassField(10);
        Animal experimentalRabbit = new Animal(testField, new Vector2d(4, 2));
        assertTrue(testField.place(experimentalRabbit));
    }

    @Test
    void isOccupiedShouldReturnTrueIfAPlaceIsOccupied() {
        GrassField testField = new GrassField(0);
        Animal experimentalRabbit = new Animal(testField, new Vector2d(4, 2));
        testField.place(experimentalRabbit);
        assertTrue(testField.isOccupied(experimentalRabbit.getPosition()));
    }

    @Test
    void isOccupiedShouldReturnFalseIfAPlaceIsNotOccupied() {
        GrassField testField = new GrassField(0);
        Animal experimentalRabbit = new Animal(testField, new Vector2d(4, 2));
        testField.place(experimentalRabbit);
        assertFalse(testField.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    void isOccupiedByGrassShouldReturnTrueIfAPlaceIsOccupiedByGrass() {
        GrassField testField = new GrassField(0);
        Grass testGrass = new Grass(new Vector2d(2, 3));
        testField.place(testGrass);
        assertTrue(testField.isOccupiedByGrass(new Vector2d(2, 3)));
    }

    @Test
    void isOccupiedByGrassShouldReturnFalseIfAPlaceIsOccupiedByAnAnimal() {
        GrassField testField = new GrassField(0);
        Grass testGrass = new Grass(new Vector2d(2, 3));
        testField.place(testGrass);
        Animal experimentalRabbit = new Animal(testField, new Vector2d(2, 3));
        testField.place(experimentalRabbit);
        assertFalse(testField.isOccupiedByGrass(new Vector2d(2, 3)));
    }

    @Test
    void isOccupiedByGrassShouldReturnFalseIfNothingIsThere() {
        GrassField testField = new GrassField(0);
        assertFalse(testField.isOccupiedByGrass(new Vector2d(2, 3)));
    }

    @Test
    void isOccupiedByAnAnimalShouldReturnTrueIfAPlaceIsOccupiedByAnAnimal() {
        GrassField testField = new GrassField(0);
        Grass testGrass = new Grass(new Vector2d(2, 3));
        testField.place(testGrass);
        Animal experimentalRabbit = new Animal(testField, new Vector2d(2, 3));
        testField.place(experimentalRabbit);
        assertTrue(testField.isOccupiedByAnAnimal(new Vector2d(2, 3)));
    }

    @Test
    void isOccupiedByAnimalShouldReturnFalseIfAPlaceIsOccupiedByGrass() {
        GrassField testField = new GrassField(0);
        Grass testGrass = new Grass(new Vector2d(2, 3));
        testField.place(testGrass);
        assertFalse(testField.isOccupiedByAnAnimal(new Vector2d(2, 3)));
    }

    @Test
    void isOccupiedByAnimalShouldReturnFalseIfNothingIsThere() {
        GrassField testField = new GrassField(0);
        assertFalse(testField.isOccupiedByAnAnimal(new Vector2d(2, 3)));
    }

    @Test
    void objectAtShouldReturnAnObjectAtAGivenPosition() {
        GrassField testField = new GrassField(0);
        Animal experimentalRabbit = new Animal(testField, new Vector2d(4, 2));
        testField.place(experimentalRabbit);
        assertEquals(testField.objectAt(new Vector2d(4, 2)), Optional.of(experimentalRabbit));
    }

    @Test
    void objectAtShouldReturnAnEmptyOptionalIfNothingIsThere() {
        GrassField testField = new GrassField(0);
        Animal experimentalRabbit = new Animal(testField, new Vector2d(4, 2));
        assertEquals(testField.objectAt(new Vector2d(4, 1)), Optional.empty());
    }
}