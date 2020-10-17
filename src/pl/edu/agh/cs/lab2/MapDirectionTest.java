import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {

    @Test
    void next() {
        assertEquals(MapDirection.EAST, MapDirection.next(MapDirection.NORTH), "Nastepnikiem polnocy jest wschod");
        assertEquals(MapDirection.SOUTH, MapDirection.next(MapDirection.EAST), "Nastepnikiem wschodu jest poludnie");
        assertEquals(MapDirection.WEST, MapDirection.next(MapDirection.SOUTH), "Nastepnikiem poludnia jest zachod");
        assertEquals(MapDirection.NORTH, MapDirection.next(MapDirection.WEST), "Nastepnikiem zachodu jest polnoc");
    }

    @Test
    void previous(){
        assertEquals(MapDirection.WEST, MapDirection.previous(MapDirection.NORTH), "Poprzednikiem polnocy jest zachod");
        assertEquals(MapDirection.SOUTH, MapDirection.previous(MapDirection.WEST), "Poprzednikiem zachodu jest poludnie");
        assertEquals(MapDirection.EAST, MapDirection.previous(MapDirection.SOUTH), "Poprzednikiem poludnia jest wschod");
        assertEquals(MapDirection.NORTH, MapDirection.previous(MapDirection.EAST), "Poprzednikiem wschodu jest polnoc");
    }
}
