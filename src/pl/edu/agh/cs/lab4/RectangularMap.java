import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RectangularMap implements IWorldMap{
    private final int width;
    private final int height;
    private final List<Animal> animals = new ArrayList<>();

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0,0)) && position.precedes(new Vector2d(width, height)); //lewy dolny rog mapy znajduje sie na pozycji (0,0)
    }

    @Override
    public boolean place(Animal animal) { //zwraca informacje czy udalo sie postawic zwierze na mapie
        if(canMoveTo(animal.getPosition()) && !isOccupied(animal.getPosition())){ //jesli nie wyjde poza mape i nie ma tam innego zwierzaka
            animals.add(animal); //dodaje do zwierzakow na mapie
            return true;
        }
        return false;
    }

    @Override
    public void run(List<MoveDirection> directions) {
        int nrOfAnimals = animals.size();
        for(int i=0; i<directions.size(); i++){
            animals.get(i % nrOfAnimals).move(directions.get(i)); //wykonuje ruchy po kolei
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal animalOnTheMap: animals){
            if(animalOnTheMap.getPosition().equals(position))
                return true;
        }
        return false;
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        if(isOccupied(position)){
            for(Animal animalOnTheMap: animals){ //sprawdzam dla kazdego zwierzaka, czy znajduje sie na danej pozycji
                if(animalOnTheMap.getPosition().equals(position))
                    return Optional.of(animalOnTheMap);
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        MapVisualiser visualiser = new MapVisualiser(this);
        return visualiser.draw(new Vector2d(0,0), new Vector2d(width, height)); //przyjalem konwencje, ze (0,0) to lewy dolny rog mapy
    }
}
