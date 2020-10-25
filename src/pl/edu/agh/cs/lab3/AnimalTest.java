import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    public static void moveLeftShouldChangeOrientation() {
        Animal experimentalRabbit = new Animal();
        MapDirection orientationBefore = experimentalRabbit.getOrientation();
        experimentalRabbit.move(MoveDirection.LEFT);
        assertEquals(MapDirection.previous(orientationBefore), experimentalRabbit.getOrientation()); //sprawdzam, czy dziala obracanie w lewo
    }

    @Test
    public static void moveRightShouldChangeOrientation() {
        Animal experimentalRabbit = new Animal();
        MapDirection orientationBefore = experimentalRabbit.getOrientation();
        experimentalRabbit.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.next(orientationBefore), experimentalRabbit.getOrientation()); //sprawdzam, czy dziala obracanie w prawo
    }

    @Test
    public static void moveForwardShouldChangePosition() {
        Animal experimentalRabbit = new Animal();
        Vector2d positionBefore = experimentalRabbit.getPosition();
        experimentalRabbit.move(MoveDirection.FORWARD);
        assertEquals(positionBefore.add(experimentalRabbit.getOrientation().toUnitVector()), experimentalRabbit.getPosition()); //sprawdzam, czy dziala chodzenie do przodu
    }

    @Test
    public static void moveBackwardShouldChangePosition() {
        Animal experimentalRabbit = new Animal();
        Vector2d positionBefore = experimentalRabbit.getPosition();
        experimentalRabbit.move(MoveDirection.BACKWARD);
        assertEquals(positionBefore.subtract(experimentalRabbit.getOrientation().toUnitVector()), experimentalRabbit.getPosition()); //sprawdzam, czy dziala chodzenie do tylu
    }

    @Test
    public static void moveForwardShouldNotBePossibleIfThisWouldMeanLeavingTheMap() {
        Animal experimentalRabbit = new Animal();
        experimentalRabbit.setPosition(experimentalRabbit.getUpperRight());
        Vector2d positionBefore = experimentalRabbit.getPosition();
        experimentalRabbit.move(MoveDirection.FORWARD);
        assertEquals(positionBefore, experimentalRabbit.getPosition()); //nie powinien sie ruszyc, bo wyszedlby poza mape
    }

    @Test
    public void moveBackwardShouldNotBePossibleIfThisWouldMeanLeavingTheMap() {
        Animal experimentalRabbit = new Animal();
        experimentalRabbit.setPosition(experimentalRabbit.getBottomLeft());
        Vector2d positionBefore = experimentalRabbit.getPosition();
        experimentalRabbit.move(MoveDirection.BACKWARD);
        assertEquals(positionBefore, experimentalRabbit.getPosition()); //nie powinien sie ruszyc, bo wyszedlby poza mape
    }
}