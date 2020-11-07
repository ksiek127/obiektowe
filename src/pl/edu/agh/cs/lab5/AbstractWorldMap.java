import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractWorldMap implements IWorldMap{
    protected List<Animal> animals = new ArrayList<>();

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public void run(List<MoveDirection> directions) {
        int nrOfAnimals = animals.size();
        for(int i=0; i<directions.size(); i++){
            animals.get(i % nrOfAnimals).move(directions.get(i)); //wykonuje ruchy po kolei
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
