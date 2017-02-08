/**
 * Created by Daniel Dang on 1/28/2017.
 * Co-authored by Mavis Francia.
 */
public class Node {

    private static int numNodes = 0;     //number of nodes generated so far

    private String state;        //current state
    private int move;            //move executed to arrive at state
    private int cost;            //total cost: g(n)
    private int moveCost;        //cost of the single move
    private int remC;            //heuristic function; number of characters out of place
    private Node parent;         //reference to parent node
    private int nodeNum;         //order node created (also order node added to queue)

    //default constructor
    public Node(String stateInput){
        this.state = stateInput;
        calcRemC(); //calculate heuristic
        numNodes++; //increment number of nodes
        nodeNum = numNodes; //assign node number to node

    }

    //calculates how many characters are out of place [h(n)]
    public void calcRemC() {
        //convert state string to character array
        char strArr[] = new char[state.length()];
        for (int i = 0; i < strArr.length; i++){
            strArr[i] = state.charAt(i);
        }
        //create character array of goal state
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
        int numOutOfPlace = 0; //counter for number of characters out of place
        for (int i = 0; i < strArr.length; i++){
            //normalize string for comparison (change lowercase x to uppercase)
            if(strArr[i] == 'x')
                strArr[i] = 'X';
            //increment counter if character does not match goal state
            if(strArr[i] != goalArr[i])
                numOutOfPlace++;
        }
        this.remC = numOutOfPlace;
    }

    public void calcCost() {
        //calculate cost of move from parent to this node
        if(!Main.hasVariableCosts()) {
            //cost of move is constant
            this.cost = parent.getCost() + 1;
        }
        else {
            //cost of move is difference between X positions of parent and child states
            int parentXPos = -1;
            int xPos = -1;
            for(int i = 0; i < state.length(); i++) {
                if(state.charAt(i) == 'x' || state.charAt(i) == 'X')
                    xPos = i;
                if(parent.getState().charAt(i) == 'x' || parent.getState().charAt(i) == 'X')
                    parentXPos = i;
            }
            this.moveCost = Math.abs(xPos - parentXPos);
            //cost from root node to current node
            //same as parent cost + move cost
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

    public Node getParent() {
        return parent;
    }

    //sets reference to parent, then sets movement cost of this state based on parent state
    //null parent means root, has 0 cost
    public void setParent(Node parent) {
        this.parent = parent;
        if(parent != null)
            calcCost();
        else
            cost = 0;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public int getNodeNum() {
        return nodeNum;
    }
}
