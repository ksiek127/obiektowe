import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() { // (x,y)
        return "(" +
                x + "," + y +  ')';
    }

    public boolean precedes(Vector2d other){
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d upperRight(Vector2d other){
        int upperX = this.x;
        if(other.x > upperX){
            upperX = other.x;
        }
        int upperY = this.y;
        if(other.y > upperY){
            upperY = other.y;
        }
        return new Vector2d(upperX, upperY);
    }

    public Vector2d lowerLeft(Vector2d other){
        int lowerX = this.x;
        if(other.x < lowerX){
            lowerX = other.x;
        }
        int lowerY = this.y;
        if(other.y < lowerY){
            lowerY = other.y;
        }
        return new Vector2d(lowerX, lowerY);
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return this.x == vector2d.x &&
                this.y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector2d opposite(){
        return new Vector2d((-1) * this.x, (-1) * this.y);
    }
}
