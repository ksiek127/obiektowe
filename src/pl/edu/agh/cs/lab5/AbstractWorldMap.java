import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected final LinkedHashMap<Vector2d, Animal> animals = new LinkedHashMap<>(); //lista zwierzat
    protected final LinkedHashMap<Vector2d, IMapElement> mapElements = new LinkedHashMap<>(); //lista wszystkich obiektow na mapie

    @Override
    public LinkedHashMap<Vector2d, Animal> getAnimals() { //zwracam kopie zwierzakow
        return new LinkedHashMap<>(animals);
    }

    @Override
    public void run(List<MoveDirection> directions) { //wykonuje ruchy po kolei
        List<Animal> animalsList = new ArrayList<>(animals.values());
        int nrOfAnimals = animalsList.size();
        for(int i=0; i<directions.size(); i++){
            Animal animal = animalsList.get(i % nrOfAnimals);
            animals.remove(animal.getPosition()); //usuwam z listy zwierzakow
            animal.move(directions.get(i)); //wykonuje ruch
            animals.put(animal.getPosition(), animalsList.get(i % nrOfAnimals)); //wstawiam spowrotem z aktualna pozycja
        }
    }

    @Override
    public abstract String toString();

    @Override
    public boolean canMoveTo(Vector2d position){
        return mapElements.get(position).getIsPassable();
    };

    @Override
    public abstract boolean place(Animal animal);

    @Override
    public abstract boolean isOccupied(Vector2d position);

    @Override
    public abstract Optional<Object> objectAt(Vector2d position);

    @Override
    public void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition) {
        mapElements.remove(oldPosition);
        mapElements.put(newPosition, movedElement);
    }
}
