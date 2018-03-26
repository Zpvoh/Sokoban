package objects;

import java.util.ArrayList;

public class Movement {
    private ArrayList<ObjectMovementPair> pairs;

    public Movement(){
        pairs=new ArrayList<>();
    }

    public Movement(ObjectMovementPair pair){
        pairs=new ArrayList<>();
        pairs.add(pair);
    }

    public Movement(ArrayList<ObjectMovementPair> pairs){
        this.pairs=pairs;
    }


}
