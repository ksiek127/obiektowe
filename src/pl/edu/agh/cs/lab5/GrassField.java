import java.util.*;

public class GrassField extends AbstractWorldMap implements IWorldMap{
    private final LinkedHashMap<Vector2d, Grass> grassClumps = new LinkedHashMap<>();
    private MapBoundary boundary = new MapBoundary();

    public GrassField(int grassClumpNr) {
        for(int i=0; i<grassClumpNr; i++){
            Grass grassClump = new Grass(new Vector2d((int)(Math.random() * Math.sqrt(10 * grassClumpNr)), (int)(Math.random() * Math.sqrt(10 * grassClumpNr)))); //losowanie pozycji pomiedzy (0,0), a (sqrt(10 * ilosc kep trawy), sqrt(10 * ilosc kep trawy)
            this.place(grassClump);
        }
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){ //jesli nie ma tam innego zwierzaka
            animals.put(animal.getPosition(), animal); //dodaje do zwierzakow na mapie
            mapElements.put(animal.getPosition(), animal); //dodaje do elementow na mapie
            boundary.addElement(animal);
            return true;
        }
        throw new IllegalArgumentException("the animal cannot be placed at " + animal.getPosition());
    }

    public void place(Grass grass) {
        grassClumps.put(grass.getPosition(), grass);
        boundary.addElement(grass);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position) || grassClumps.containsKey(position);
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        if(isOccupied(position))
            return Optional.of(mapElements.get(position));
        return Optional.empty();
    }

    private Vector2d bottomLeftCorner(){ //obliczam wspolrzedne lewego dolnego rogu mapy
        return boundary.bottomLeft();
    }

    private Vector2d topRightCorner(){ //obliczam wspolrzedne prawego gornego rogu mapy
        return boundary.topRight();
    }

    @Override
    public String toString() {
        final MapVisualiser mapVisualisation = new MapVisualiser(this);
        return mapVisualisation.draw(bottomLeftCorner(), topRightCorner());
    }
}

