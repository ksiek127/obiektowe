import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GrassField extends AbstractWorldMap implements IWorldMap{
//    private final List<Animal> animals = new ArrayList<>();
    private final List<Grass> grassClumps = new ArrayList<>();

    public GrassField(int grassClumpNr) {
        for(int i=0; i<grassClumpNr; i++){
            Grass grassClump = new Grass(new Vector2d((int)(Math.random() * Math.sqrt(10 * grassClumpNr)), (int)(Math.random() * Math.sqrt(10 * grassClumpNr)))); //losowanie pozycji pomiedzy (0,0), a (sqrt(10 * ilosc kep trawy), sqrt(10 * ilosc kep trawy)
            this.place(grassClump);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) { //w tej klasie ta metoda nie ma sensu, bo rozmiar jest nieskonczony, tzn ograniczony rozmiarem inta, ale Vector2d z zalozenia ma wspolrzedne typu int, jednak musze zaimplementowac caly interfejs takze zostawiam ta metode tak jak jest
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition()) && !isOccupiedByAnAnimal(animal.getPosition())){ //jesli nie wyjde poza mape i nie ma tam innego zwierzaka
            animals.add(animal); //dodaje do zwierzakow na mapie
            return true;
        }
        return false;
    }

    public void place(Grass grass) {
        grassClumps.add(grass);
    }

    public boolean isOccupiedByAnAnimal(Vector2d position){
        for(Animal animalOnTheMap: animals){
            if(animalOnTheMap.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public boolean isOccupiedByGrass(Vector2d position) {
        if(isOccupiedByAnAnimal(position))  //jesli tam jest zwierze, to ma priorytet nad trawa czyli tej trawy tak naprawde nie ma chwilowo
            return false;
        for(Grass grass: grassClumps){
            if(grass.getPosition().equals(position))
                return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return isOccupiedByGrass(position) || isOccupiedByAnAnimal(position);
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        if(isOccupiedByAnAnimal(position)){
            for(Animal animalOnTheMap: animals){ //sprawdzam dla kazdego zwierzaka, czy znajduje sie na danej pozycji
                if(animalOnTheMap.getPosition().equals(position))
                    return Optional.of(animalOnTheMap);
            }
        }
        if(isOccupiedByGrass(position)){ //jesli nie ma tam zwierzaka, to sprawdzam, czy jest kepka trawy
            for(Grass grass: grassClumps){
                if(grass.getPosition().equals(position))
                    return Optional.of(grass);
            }
        }

        return Optional.empty();
    }

    private Vector2d bottomLeftCorner(){ //obliczam wspolrzedne lewego dolnego rogu mapy
        int left = grassClumps.get(0).getPosition().getX();
        int bottom = grassClumps.get(0).getPosition().getY();
        for(Grass grass: grassClumps){
            if(grass.getPosition().getX() < left)
                left = grass.getPosition().getX();
            if(grass.getPosition().getY() < bottom)
                bottom = grass.getPosition().getY();
        }
        for(Animal animal: animals){
            if(animal.getPosition().getX() < left)
                left = animal.getPosition().getX();
            if(animal.getPosition().getY() < bottom)
                bottom = animal.getPosition().getY();
        }
        return new Vector2d(left, bottom);
    }

    private Vector2d topRightCorner(){ //obliczam wspolrzedne prawego gornego rogu mapy
        int right = grassClumps.get(0).getPosition().getX();
        int top = grassClumps.get(0).getPosition().getY();
        for(Grass grass: grassClumps){
            if(grass.getPosition().getX() > right)
                right = grass.getPosition().getX();
            if(grass.getPosition().getY() > top)
                top = grass.getPosition().getY();
        }
        for(Animal animal: animals){
            if(animal.getPosition().getX() > right)
                right = animal.getPosition().getX();
            if(animal.getPosition().getY() > top)
                top = animal.getPosition().getY();
        }
        return new Vector2d(right, top);
    }

    @Override
    public String toString() {
        final MapVisualiser mapVisualisation = new MapVisualiser(this);
        return mapVisualisation.draw(bottomLeftCorner(), topRightCorner());
    }
}

