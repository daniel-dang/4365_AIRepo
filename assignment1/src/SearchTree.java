import java.util.*;

/**
 * Created by Daniel Dang on 1/30/2017.
 */
public class SearchTree {
    protected Stack<Node> path;             //stores path from goal state back to root
    protected Set<String> expandedStates;   //stores states that have already been expanded

    //default constructor
    public SearchTree(){
        this.expandedStates = new HashSet<>();  //initialize set of explored states
    }

    /*
     * This method takes a Node as a parameter, generates all the successors of that Node's state,
     * and returns an ArrayList of all the successors
     */
    public ArrayList<Node> getAllSuccessors(Node state){
        //return if initial state is empty
        if (state.getState().equals(""))
            return null;

        //generate all the successors
        char[] currState = processStr(state.getState());
        int xLoc = 0;
        //Find 'x'
        for (int i = 0; i < currState.length; i++){
            if (currState[i] == 'x' || currState[i] == 'X') {
                xLoc = i;
                break;
            }
        }

        //In every state, number of all possible successor states is string.length - 1
        ArrayList<Node> successors = new ArrayList<Node>();
        int stateCount = 0; //number of successor states generated
        int pivot = 0;  //index to be swapped with X
        int moveC = 0;  //tile moved to generate successor
        while(stateCount < (currState.length - 1)){
            char temp = 0;
            if (pivot != xLoc){
                char tempArr[] = Arrays.copyOf(currState, currState.length);
                //swapping procedure; swap a x location to a different location in the array
                temp = tempArr[pivot];
                tempArr[pivot] = tempArr[xLoc];
                tempArr[xLoc] = temp;

                //adding newly created state to a new node
                stateCount++;
                Node successor = new Node(new String(tempArr));
                successor.setMove(moveC);
                successor.setParent(state);
                successors.add(successor); //add new successor to ArrayList of successors
            }
            pivot++;
            moveC++;
        }
        return successors;
    }

    //convert state string into char array
    private char[] processStr(String stateDesc){
        char strArr[] = new char[stateDesc.length()];
        for (int i = 0; i < strArr.length; i++){
            strArr[i] = stateDesc.charAt(i);
        }
        return strArr;
    }

    public boolean goalTest(Node state){
        int mid = state.getState().length() / 2;
        /*
         * If middle character not 'x' => fail
         * If character [0, mid - 1] not 'B' => fail
         * If character [mid + 1, end] not 'W' => fail
         * If nothing happen, passed all test, return true. Goal test achieved.
         */
        for (int i = 0; i < state.getState().length(); i++){
            if (i == mid && state.getState().charAt(mid) != 'x' &&
                            state.getState().charAt(mid) != 'X')
                return false;
            if ((i < mid) && (state.getState().charAt(i) != 'B'))
                return false;
            if ((i > mid) && (state.getState().charAt(i) != 'W'))
                return false;
        }
        return true;
    }

    /*
     * Traverse from goal node back to root node, each recursive call
     * put the node inside of the stack
     * Root node always null for parent pointer.
     */
    public Stack<Node> getPath(Node goal){
        if (goal.getParent() == null) {
            path.push(goal);
        }
        else {
            path.push(goal);
            getPath(goal.getParent());
        }
        return path;
    }
}
