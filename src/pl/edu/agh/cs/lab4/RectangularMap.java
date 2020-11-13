import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RectangularMap extends AbstractWorldMap implements IWorldMap{
//    private final List<Animal> animals = new ArrayList<>();
    private final Vector2d lowerLeft = new Vector2d(0,0); //przyjalem konwencje, ze (0,0) to lewy dolny rog mapy
    private final Vector2d upperRight;

    public RectangularMap(int width, int height) {
        upperRight = new Vector2d(width, height);
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeft) && position.precedes(upperRight); //lewy dolny rog mapy znajduje sie na pozycji (0,0)
    }

    @Override
    public boolean place(Animal animal) { //zwraca informacje czy udalo sie postawic zwierze na mapie
        if(canMoveTo(animal.getPosition()) && !isOccupied(animal.getPosition())){ //jesli nie wyjde poza mape i nie ma tam innego zwierzaka
            animals.put(animal.getPosition(), animal); //dodaje do zwierzakow na mapie
            return true;
        }
        throw new IllegalArgumentException("the animal cannot be placed at " + animal.getPosition());
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        if(isOccupied(position))
            return Optional.of(animals.get(position));
        return Optional.empty();
    }

    @Override
    public String toString() {
        MapVisualiser visualiser = new MapVisualiser(this);
        return visualiser.draw(lowerLeft, upperRight);
    }
}
