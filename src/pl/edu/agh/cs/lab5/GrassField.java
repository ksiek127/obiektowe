import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class GrassField extends AbstractWorldMap implements IWorldMap{
    private final LinkedHashMap<Vector2d, Grass> grassClumps = new LinkedHashMap<>();

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
            animals.put(animal.getPosition(), animal); //dodaje do zwierzakow na mapie
            return true;
        }
        throw new IllegalArgumentException("the animal cannot be placed at " + animal.getPosition());
    }

    public void place(Grass grass) {
        grassClumps.put(grass.getPosition(), grass);
    }

    public boolean isOccupiedByAnAnimal(Vector2d position){
        return animals.containsKey(position);
    }

    public boolean isOccupiedByGrass(Vector2d position) {
        if(isOccupiedByAnAnimal(position))  //jesli tam jest zwierze, to ma priorytet nad trawa czyli tej trawy tak naprawde nie ma chwilowo
            return false;
        return grassClumps.containsKey(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return isOccupiedByGrass(position) || isOccupiedByAnAnimal(position);
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        if(isOccupiedByAnAnimal(position))
            return Optional.of(animals.get(position));
        if(isOccupiedByGrass(position)) //jesli nie ma tam zwierzaka, to sprawdzam, czy jest kepka trawy
            return Optional.of(grassClumps.get(position));
        return Optional.empty();
    }

    private Vector2d bottomLeftCorner(){ //obliczam wspolrzedne lewego dolnego rogu mapy
        int left = grassClumps.entrySet().iterator().next().getKey().getX();
        int bottom = grassClumps.entrySet().iterator().next().getKey().getY();
        for(Map.Entry<Vector2d, Grass> grass: grassClumps.entrySet()){
            Vector2d position = grass.getKey();
            if(position.getX() < left)
                left = position.getX();
            if(position.getY() < bottom)
                bottom = position.getY();
        }
        for(Map.Entry<Vector2d, Animal> animal: animals.entrySet()){
            Vector2d position = animal.getKey();
            if(position.getX() < left)
                left = position.getX();
            if(position.getY() < bottom)
                bottom = position.getY();
        }
        return new Vector2d(left, bottom);
    }

    private Vector2d topRightCorner(){ //obliczam wspolrzedne prawego gornego rogu mapy
        int right = grassClumps.entrySet().iterator().next().getKey().getX();
        int top = grassClumps.entrySet().iterator().next().getKey().getY();
        for(Map.Entry<Vector2d, Grass> grass: grassClumps.entrySet()){
            Vector2d position = grass.getKey();
            if(position.getX() > right)
                right = position.getX();
            if(position.getY() > top)
                top = position.getY();
        }
        for(Map.Entry<Vector2d, Animal> animal: animals.entrySet()){
            Vector2d position = animal.getKey();
            if(position.getX() > right)
                right = position.getX();
            if(position.getY() > top)
                top = position.getY();
        }
        return new Vector2d(right, top);
    }

    @Override
    public String toString() {
        final MapVisualiser mapVisualisation = new MapVisualiser(this);
        return mapVisualisation.draw(bottomLeftCorner(), topRightCorner());
    }
}

