/**
 * Created by Daniel Dang on 1/28/2017.
 */
public class Node {
    private String state;               //current state
    private int move;                   //number of moved made
    private int costWeighted;           //costWeighted: g(n) [-cost] function
    private int costUnWeighted;         //cost unweighted:  g(n)
    private int remC;                   //heuristic function
    private int depth;                  //used for DFS
    private Node parent;                //reference to parent node

    //default constructor
    public Node(String stateInput){
        this.state = stateInput;
        depth = -1;
    }

    //overloaded constructor to keep track of node depth
    public Node(String stateInput, int depth){
        this.state = stateInput;
        this.depth = depth;
    }

    //---------------GETTERS AND SETTERS--------------------
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCostWeighted() {
        return costWeighted;
    }

    public void setCostWeighted(int costWeighted) {
        this.costWeighted = costWeighted;
    }

    public int getCostUnWeighted() {
        return costUnWeighted;
    }

    public void setCostUnWeighted(int costUnWeighted) {
        this.costUnWeighted = costUnWeighted;
    }

    public int getRemC() {
        return remC;
    }

    public void setRemC(int remC) {
        this.remC = remC;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }
}
