/**
 * Created by Daniel Dang on 1/28/2017.
 */
public class Node {
    private String currentState;        //current state
    private int move;
    private int cost;                   //cost: g(n)
    private int remC;                   //heuristic function

    private Node parent;                //reference to parent node

    //default constructor
    public Node(String stateInput){
        this.currentState = stateInput;
    }

    //---------------GETTERS AND SETTERS--------------------
    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRemC() {
        return remC;
    }

    public void setRemC(int remC) {
        this.remC = remC;
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
