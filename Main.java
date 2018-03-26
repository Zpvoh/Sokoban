import objects.Box;
import objects.DirectionException;
import objects.Map;
import objects.People;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Choose the map:");
        Scanner scanner=new Scanner(System.in);
        String index=scanner.next();
        Map map=new Map("maps/"+index+".map");
        try {
            play(map);
        } catch (DirectionException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void play(Map map) throws DirectionException {
        String command="a";
        Scanner scanner=new Scanner(System.in);
        while(!command.equals("q")){
            map.printMap();
            if(map.isWin()){
                System.out.println("You win!");
                System.exit(0);
            }
            if(map.isLose()){
                System.out.println("You lose!");
                System.exit(0);
            }
            command=scanner.nextLine();
            switch (command){
                case "w":
                    playerMove(map, People.W);
                    break;
                case "a":
                    playerMove(map, People.A);
                    break;
                case "s":
                    playerMove(map, People.S);
                    break;
                case "d":
                    playerMove(map, People.D);
                    break;
                case "q":
                    System.out.println("Good bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("You should input something meaningful.");
            }

            map.updateMap();
        }
    }

    public static boolean playerMove(Map map, int direction) throws DirectionException {
        for(Box box: map.boxes){
            if(map.people.related(box, direction)) {
                if(box.isMovable(map, direction))
                    map.people.push(box, direction);
                else
                    return false;
            }
        }

        if(!map.people.isWallMovable(map, direction)){
            return false;
        }

        map.people.move(direction);
        return true;
    }
}
