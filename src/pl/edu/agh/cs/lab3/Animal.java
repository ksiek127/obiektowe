import java.util.ArrayList;

public class Animal extends AbstractWorldMapElement implements IMapElement, IPositionChangedPublisher{
    private MapDirection orientation; //orientaja (N/E/S/W)
    private final Vector2d bottomLeft = new Vector2d(0,0); //lewy dolny rog prostokatnej mapy
    private final Vector2d upperRight = new Vector2d(4,4); //prawy gorny rog
    private IWorldMap map; //mapa, na ktorej znajduje sie dane zwierze
    private final ArrayList<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    public Animal(IWorldMap map) {
        this();
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this(map);
        this.position = initialPosition;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    public Vector2d getBottomLeft() {
        return bottomLeft;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return switch (this.orientation){
            case NORTH -> "▲";
            case EAST -> "◄";
            case SOUTH -> "▼";
            case WEST -> "►";
        };
    }

    public void move(MoveDirection direction){ //wykonanie pojedynczego ruchu
        switch (direction){
            case RIGHT -> this.orientation = MapDirection.next(this.orientation);
            case LEFT -> this.orientation = MapDirection.previous(this.orientation);
            case FORWARD -> {
                if(map.canMoveTo(this.position.add(this.orientation.toUnitVector()))){ //sprawdzam, czy nie wyjdzie poza plansze
                    this.position = this.position.add(this.orientation.toUnitVector());
                }
            }
            case BACKWARD -> {
                if(map.canMoveTo(this.position.subtract(this.orientation.toUnitVector()))){
                    this.position = this.position.subtract(this.orientation.toUnitVector());
                }
            }
        }
    }

    @Override
    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    public void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer: observers){ //notyfikacja o zmianie pozycji dla kazdego obserwatora
            observer.positionChanged(movedElement, oldPosition, newPosition);
        }
    }
}
