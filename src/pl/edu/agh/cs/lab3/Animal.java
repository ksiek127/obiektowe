public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private final Vector2d bottomLeft = new Vector2d(0,0);
    private final Vector2d upperRight = new Vector2d(4,4);
    private IWorldMap map;

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    public Animal(IWorldMap map) {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.orientation = MapDirection.NORTH;
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

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        char orientationArrow = switch (this.orientation){
            case NORTH -> '▲';
            case EAST -> '◄';
            case SOUTH -> '▼';
            case WEST -> '►';
        };
        return "Position: " + this.position + ", Orientation: " + this.orientation;
    }

//    public boolean isMoveLegal(Vector2d pos){
//        //sprawdzam, czy nie wyjdzie poza plansze
//        return pos.follows(bottomLeft) && pos.precedes(upperRight);
//    }

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
