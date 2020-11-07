import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapIntegrationTest {
    @Test
    public void runIntegrationTest(){
        List<MoveDirection> directions = new OptionsParser().parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(10, 5);
        map.place(new Animal(map));
        assertEquals(map.getAnimals().get(0).getPosition(), new Vector2d(2,2));//sprawdzam, czy domyslnie sie pojawia na (2,2)
        map.place(new Animal(map, new Vector2d(3,4)));
        assertEquals(map.getAnimals().get(1).getPosition(), new Vector2d(3,4)); //sprawdzam, czy drugi zwierzak jest na (3,4)
        map.run(directions);
        assertEquals(map.getAnimals().get(0).getPosition(), new Vector2d(3,0)); //pozycja pierwszego zwierzaka po wykonaniu sekwencji ruchow
        assertEquals(map.getAnimals().get(0).getOrientation(), MapDirection.SOUTH); //orientacja pierwszego
        assertEquals(map.getAnimals().get(1).getPosition(), new Vector2d(2,5)); //pozycja drugiego
        assertEquals(map.getAnimals().get(1).getOrientation(), MapDirection.NORTH); //orientacja drugiego
    }
}