import java.util.ArrayList;
import java.util.Comparator;

public class MapBoundary implements IPositionChangeObserver{
    private final ArrayList<IMapElement> elementsSortedByX = new ArrayList<>();
    private final ArrayList<IMapElement> elementsSortedByY = new ArrayList<>();

    private void updatePosition(IMapElement element){
        if(element.getPosition().getX() < elementsSortedByX.get(0).getPosition().getX())
            elementsSortedByX.add(0, element);
        if(element.getPosition().getY() < elementsSortedByY.get(0).getPosition().getY())
            elementsSortedByY.add(0, element);
        if(element.getPosition().getX() > elementsSortedByX.get(elementsSortedByX.size() - 1).getPosition().getX())
            elementsSortedByX.add(elementsSortedByX.size() - 1, element);
        if(element.getPosition().getY() > elementsSortedByY.get(elementsSortedByY.size() - 1).getPosition().getY())
            elementsSortedByY.add(elementsSortedByY.size() - 1, element);
    }

    public void addElement(IMapElement element){
        elementsSortedByX.add(element);
        elementsSortedByY.add(element);
        updatePosition(element);
    }

    @Override
    public void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition) {
        elementsSortedByX.remove(movedElement);
        elementsSortedByY.remove(movedElement);
        updatePosition(movedElement);
    }

    public Vector2d topRight(){
        int nrOfElements = elementsSortedByX.size();
        return new Vector2d(elementsSortedByX.get(nrOfElements - 1).getPosition().getX(), elementsSortedByY.get(nrOfElements - 1).getPosition().getY());
    }

    public Vector2d bottomLeft(){
        return new Vector2d(elementsSortedByX.get(0).getPosition().getX(), elementsSortedByY.get(0).getPosition().getY());
    }
}
