/**
 * Created by Daniel Dang on 1/28/2017.
 */
public class Node {
    private String state;        //current state
    private int move;
    private int cost;                   //total cost: g(n)
    private int moveCost;               //cost of the single move
    private int remC;                   //heuristic function
    private int depth;                  //used for DFS
    private Node parent;                //reference to parent node

    //default constructor
    public Node(String stateInput){
        this.state = stateInput;
        depth = -1;
        calcRemC();
    }

    //overloaded constructor to keep track of node depth
    public Node(String stateInput, int depth){
        this.state = stateInput;
        this.depth = depth;
    }

    //calculates how many characters are out of place [h(n)]
    public void calcRemC() {
        char strArr[] = new char[state.length()];
        for (int i = 0; i < strArr.length; i++){
            strArr[i] = state.charAt(i);
        }
        char goalArr[] = new char[state.length()];
        int mid = state.length()/2;
        for (int i = 0; i < goalArr.length; i++){
            if(i < mid)
                goalArr[i] = 'B';
            else if(i == mid)
                goalArr[i] = 'X';
            else
                goalArr[i] = 'W';
        }
        int numOutOfPlace = 0;
        for (int i = 0; i < strArr.length; i++){
            //normalize string for comparison (change lowercase x to uppercase)
            if(strArr[i] == 'x')
                strArr[i] = 'X';
            //increment counter if character is out of place
            if(strArr[i] != goalArr[i])
                numOutOfPlace++;
        }
        remC = numOutOfPlace;
    }

    public void calcCost() {
        //calculate cost of move from parent to this node
        if(Main.hasVariableCosts() == false) {
            //cost of move is constant
            this.cost = parent.getCost() + 1;
        }
        else {
            //cost of move is number of places X tile moves
            int parentXPos = -1;
            int xPos = -1;
            for(int i = 0; i < state.length(); i++) {
                if(state.charAt(i) == 'x' || state.charAt(i) == 'X')
                    xPos = i;
                if(parent.getState().charAt(i) == 'x' || parent.getState().charAt(i) == 'X')
                    parentXPos = i;
            }
            moveCost = Math.abs(xPos - parentXPos);
            this.cost = parent.getCost() + moveCost;
        }
    }

    //---------------GETTERS AND SETTERS--------------------
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getMoveCost() {
        return moveCost;
    }

    public void setMoveCost(int moveCost) {
        this.moveCost = moveCost;
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
        if(parent != null)
            calcCost();
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }
}
