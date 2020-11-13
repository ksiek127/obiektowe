import java.util.List;

public class World {
    public static void main(String[] args) {
        try{
            List<MoveDirection> directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            map.place(new Animal(map));
            map.place(new Animal(map, new Vector2d(3, 4)));
            map.run(directions);
            System.out.println(map.toString());
        } catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
            System.exit(-1);
        }
    }
}
