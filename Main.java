import Objects.Box;
import Objects.DirectionException;
import Objects.Map;
import Objects.People;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Choose the map:");
        try {
            play();
        } catch (DirectionException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void play() throws DirectionException {
        Scanner scanner=new Scanner(System.in);
        String index=scanner.nextLine();
        Map map=new Map("maps/"+index+".map");
        Map originalMap=new Map("maps/"+index+".map");
        String command="a";
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
                case "r":
                    map=originalMap;
                    originalMap=new Map("maps/"+index+".map");
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
