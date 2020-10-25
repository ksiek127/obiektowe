import java.util.LinkedList;

public class OptionsParser {
   public static LinkedList<MoveDirection> parse(String[] options){
       LinkedList<MoveDirection> parsed = new LinkedList<>();
        for(String option: options){
            switch (option){
                case "f", "forward" -> parsed.add(MoveDirection.FORWARD);
                case "b", "backward" -> parsed.add(MoveDirection.BACKWARD);
                case "l", "left" -> parsed.add(MoveDirection.LEFT);
                case "r", "right" -> parsed.add(MoveDirection.RIGHT);
            }
        }
        return parsed;
   }
}
