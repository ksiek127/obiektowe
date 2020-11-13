import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public abstract class AbstractWorldMap implements IWorldMap{
    protected final LinkedHashMap<Vector2d, Animal> animals = new LinkedHashMap<>();

    @Override
    public LinkedHashMap<Vector2d, Animal> getAnimals() {
        return animals;
    }

    @Override
    public void run(List<MoveDirection> directions) {
        List<Animal> animalsList = new ArrayList<>(animals.values());
        int nrOfAnimals = animalsList.size();
        for(int i=0; i<directions.size(); i++){
            animals.remove(animalsList.get(i % nrOfAnimals).getPosition()); //usuwam z listy zwierzakow
            animalsList.get(i % nrOfAnimals).move(directions.get(i)); //wykonuje ruch
            animals.put(animalsList.get(i % nrOfAnimals).getPosition(), animalsList.get(i % nrOfAnimals)); //wstawiam spowrotem z aktualna pozycja
        }
    }

    @Override
    public abstract String toString();

    @Override
    public abstract boolean canMoveTo(Vector2d position);

    @Override
    public abstract boolean place(Animal animal);

    @Override
    public abstract boolean isOccupied(Vector2d position);

    @Override
    public abstract Optional<Object> objectAt(Vector2d position);
}
