package Objects;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    int[][] map;
    public ArrayList<Box> boxes=new ArrayList<Box>();
    public ArrayList<Wall> walls=new ArrayList<Wall>();
    public ArrayList<Target> targets=new ArrayList<Target>();
    public People people=new People(0, 0);
    public Map(){

    }

    public Map(String filename){
        map=readMap(filename);
    }

    public void printMap(){
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[i].length; j++){
                switch (map[i][j]){
                    case 0:
                    case 2:
                        System.out.print("  ");
                        break;
                    case 1:
                        System.out.print("■");
                        break;
                    case 3:
                        System.out.print("□");
                        break;
                    case 4:
                        System.out.print("◎");
                        break;
                    case 5:
                        System.out.print("☺");
                        break;
                    case 9:
                        System.out.print("░");
                        break;

                }

                System.out.print(" ");
            }

            System.out.println();
        }
    }

    public int[][] readMap(String filename){
        int[][] map=new int[1][1];
        try {
            FileReader reader=new FileReader(filename);
            Scanner scanner=new Scanner(reader);
            int n=scanner.nextInt();
            int m=scanner.nextInt();
            map=new int[m][n];
            String line=scanner.nextLine();
            for(int i=0; i<m; i++){
                line=scanner.nextLine();
                for(int j=0; j<n; j++){
                    int k=0;
                    while((k=line.charAt(j)-48)<0){

                    }

                    switch(k){
                        case 1:
                            map[i][j]=1;
                            walls.add(new Wall(i, j));
                            break;
                        case 3:
                            map[i][j]=3;
                            boxes.add(new Box(i, j));
                            break;
                        case 4:
                            map[i][j]=4;
                            targets.add(new Target(i, j));
                            break;
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            map[i][j]=5;
                            people=new People(i, j);
                            break;
                        case 9:
                            map[i][j]=9;
                            boxes.add(new Box(i, j));
                            targets.add(new Target(i, j));
                            break;
                        default:
                            map[i][j]=k;
                            break;
                    }
                }
            }
        } catch (IOException e){
            System.out.println("I/O Problem");
        }
            return map;
    }

    public void updateMap(){
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[i].length; j++){
                if(map[i][j]>1)
                    map[i][j]=2;
            }
        }

        for(Target target: targets){
            map[target.getX()][target.getY()]=4;
        }

        for (Box box:
             boxes) {
            int x=box.getX();
            int y=box.getY();
            switch (map[x][y]){
                case 2:
                    map[x][y]=3;
                    break;
                case 4:
                    map[x][y]=9;
                    break;
            }
        }

        int x=people.getX();
        int y=people.getY();
        map[x][y]=5;
    }

    public boolean isWin(){
        boolean isWin=true;
        for(Target target: targets){
            if(map[target.getX()][target.getY()]!=9)
                isWin=false;
        }

        return isWin;
    }

    public boolean isLose() throws DirectionException {
        for(Box box: boxes){
            int loseCount=0;
            int loseFlag=0;

            for(int i=0; i<4; i++){
                if(!box.isWallMovable(this, i) && loseCount<2){
                    loseCount++;
                    loseFlag+=i;
                }
            }

            if(loseCount>=2 && loseFlag%2==1 && map[box.getX()][box.getY()]!=9)
                return true;
        }

        return false;
    }
}
