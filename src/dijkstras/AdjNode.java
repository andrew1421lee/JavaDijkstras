package dijkstras;

/**
 * AdjNode Class
 * Adjacent node objects
 * @author Anchu
 */
public class AdjNode{
    // identifying number
    private int number;
    // cost to travel to node
    private int cost;

    /**
     * AdjNode Constructor
     * @param num Identifying number
     * @param c Cost
     */
    public AdjNode(int num, int c){
        this.number = num;
        this.cost = c;
    }

    /**
     * getNumber Method
     * Returns identifying number
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * getCost
     * Returns cost to travel
     * @return cost
     */
    public int getCost() {
        return cost;
    }
}
