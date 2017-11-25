package dijkstras;

import java.util.ArrayList;

public class Thorup {

    public static void RunThorup(ArrayList<Node> graph, int start){
        System.out.println(calculateMSB(5001));
    }

    private static int calculateMSB(int weight){
        return (int)(Math.log((double)weight) / Math.log(2));
    }
}
