public class Animal extends AbstractWorldMapElement implements IMapElement{
    private MapDirection orientation;
//    private Vector2d position;
    private final Vector2d bottomLeft = new Vector2d(0,0);
    private final Vector2d upperRight = new Vector2d(4,4);
    private IWorldMap map;

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
        String orientationArrow = switch (this.orientation){
            case NORTH -> "▲";
            case EAST -> "◄";
            case SOUTH -> "▼";
            case WEST -> "►";
        };
//        return "Position: " + this.position + ", Orientation: " + this.orientation;
        return orientationArrow;
    }

    public void move(MoveDirection direction){
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
}
