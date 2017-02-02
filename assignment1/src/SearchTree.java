import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Daniel Dang on 1/30/2017.
 */
public class SearchTree {
    private String iniState = "";
    private Node root = null;

    public SearchTree(String iniState){
        this.iniState = iniState;
        this.root = new Node(iniState);
        this.root.setParent(null);
    }

    public Node getAccessor(){
        if (iniState.equals(""))
            return null;
        else{

        }

        return null;
    }

    public ArrayList<Node> getAllSuccessors(Node state){
        //return if initial state is empty
        if (state.getCurrentState().equals(""))
            return null;

        //generate all the successors
        char[] currState = processStr(state.getCurrentState());
        int xLoc = 0;
        //Find X
        for (int i = 0; i < currState.length; i++){
            if (currState[i] == 'x' || currState[i] == 'X')
                xLoc = i;
        }

        //In every state, all possible successor states is string.length - 1 - 1 = string.length - 2
        ArrayList<Node> successors = new ArrayList<Node>();
        int stateCount = 0;
        int pivot = 0;
        while(stateCount < (currState.length - 2)){
            char temp = 0;
            if (pivot != xLoc){
                //swapping procedure; swap a x location to a different location in the array
                currState[pivot] = temp;
                currState[pivot] = currState[xLoc];
                currState[xLoc] = temp;
            }
            pivot++;
            stateCount++;
            Node successor = new Node(currState.toString());
            successor.setParent(state);
            successors.add(successor);
        }
        return successors;
    }

    private char[] processStr(String stateDesc){
        char strArr[] = new char[stateDesc.length()];
        for (int i = 0; i < strArr.length; i++){
            strArr[i] = stateDesc.charAt(i);
        }
        return strArr;
    }
    /*
     * Only return true if the first half of the word is 'B' and second half of the word is 'W'
     * return false otherwise.
     */
    public boolean checkGoalState(Node state){
        boolean finalState = false;
        int mid = state.getCurrentState().length() / 2;
        for (int i = 0; i < mid; i++){
            if (state.getCurrentState().charAt(i) == 'B')
                finalState = true;
            else
                return false;
        }
        for (int i = mid + 1; i < state.getCurrentState().length(); i++){
            if (state.getCurrentState().charAt(i) == 'W')
                finalState = true;
            else
                return false;
        }
        return finalState;
    }

    /*
     * Traverse from goal node back to root node, each recursive call
     * put the node inside of the stack
     * Root node always null for parent pointer.
     */
    public Stack<Node> getPath(Node goal){
        Stack<Node> path = new Stack<>();
        if (goal.getParent() == null)
            return path;
        else {
            path.push(goal);
            getPath(goal.getParent());
        }
        return null;
    }
}
