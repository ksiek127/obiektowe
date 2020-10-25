import java.util.LinkedList;

public class World {
    public static void main(String[] args) {
        Animal klaus = new Animal();
        System.out.println("Position: " + klaus.getPosition());
        String[] moves = {"f", "l", "backward"};
        LinkedList<MoveDirection> parsedMoves = OptionsParser.parse(moves);
        for(MoveDirection singleMove: parsedMoves){
            klaus.move(singleMove);
        }
        System.out.println("Position: " + klaus.getPosition());
    }
}
