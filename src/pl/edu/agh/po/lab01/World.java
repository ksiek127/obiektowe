package pl.edu.agh.po.lab01;
import pl.edu.agh.cs.lab1.Direction.Directions;

public class World {
    public static void run(Directions[] moves){
        for(Directions move: moves){
            switch (move){
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
                case LEFT -> System.out.println("Zwierzak skreca w lewo");
                case RIGHT -> System.out.println("Zwierzak skreca w prawo");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Start");
        Directions[] m = new Directions[args.length];
        for(int i=0; i<args.length; i++){
            switch (args[i]){
                case "f" -> m[i] = Directions.FORWARD;
                case "b" -> m[i] = Directions.BACKWARD;
                case "l" -> m[i] = Directions.LEFT;
                case "r" -> m[i] = Directions.RIGHT;
            }
        }
        run(m);
        System.out.println("Stop");
    }
}
