public abstract class AbstractWorldMapElement implements IMapElement{
    protected Vector2d position; //pozycja na mapie
    protected boolean isPassable; //czy mozna na tym stanac

    @Override
    public boolean getIsPassable() {
        return isPassable;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public abstract String toString();
}
