public interface IPositionChangeObserver {
    void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition);
}
