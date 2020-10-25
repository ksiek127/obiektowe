import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalIntegrationTest {
    @Test
    public void moveIntegrationTest(){
        Animal experimentalRabbit = new Animal();
        Vector2d positionBefore = experimentalRabbit.getPosition();
        experimentalRabbit.move(MoveDirection.FORWARD);
        assertEquals(positionBefore.add(experimentalRabbit.getOrientation().toUnitVector()), experimentalRabbit.getPosition()); //sprawdzam, czy dziala chodzenie do przodu
        MapDirection orientationBefore = experimentalRabbit.getOrientation();
        experimentalRabbit.move(MoveDirection.LEFT);
        assertEquals(MapDirection.previous(orientationBefore), experimentalRabbit.getOrientation()); //sprawdzam, czy dziala obracanie w lewo
        positionBefore = experimentalRabbit.getPosition();
        experimentalRabbit.move(MoveDirection.BACKWARD);
        assertEquals(positionBefore.subtract(experimentalRabbit.getOrientation().toUnitVector()), experimentalRabbit.getPosition()); //sprawdzam, czy dziala chodzenie do tylu
        experimentalRabbit.setPosition(experimentalRabbit.getBottomLeft());
        assertEquals(experimentalRabbit.getBottomLeft(), experimentalRabbit.getPosition()); //sprawdzam, czy pozycja krolika doswiadczalnego jest ustawiona na lewy dolny rog mapy
        orientationBefore = experimentalRabbit.getOrientation();
        experimentalRabbit.move(MoveDirection.LEFT);
        assertEquals(MapDirection.previous(orientationBefore), experimentalRabbit.getOrientation()); //jeszcze raz sprawdzam, czy dziala obracanie w lewo
        positionBefore = experimentalRabbit.getPosition();
        experimentalRabbit.move(MoveDirection.FORWARD);
        assertEquals(positionBefore, experimentalRabbit.getPosition()); //nie powinien sie ruszyc, bo wyszedlby poza mape
    }
}
