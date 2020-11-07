public class Grass extends AbstractWorldMapElement implements IMapElement{
//    private final Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "*";
    }
}
