import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Daniel Dang on 1/30/2017.
 */
public class SearchTree {
    protected Stack<Node> path;

    //default constructor
    public SearchTree(){}

    /*
     * This method return a successor that randomly swapped between the x character and other available
     * place in the array.
     */
    public Node getAccessor(Node state){
        //return if initial state is empty
        if (state.getCurrentState().equals(""))
            return null;

        //Get location of 'x'
        char[] currState = processStr(state.getCurrentState());
        int xLoc = 0;
        //Find X
        for (int i = 0; i < currState.length; i++){
            if (currState[i] == 'x' || currState[i] == 'X')
                xLoc = i;
        }

        //Generate random number, repeat if random equal mid index.
        int randNum = -1;
        do {
            //random generate a number between 0 to state.length
            //pattern for generating random number [min,max] => (min + Random.nextInt(max - min) + 1)
            Random rn = new Random();
            randNum = rn.nextInt(currState.length);      //generate random number from 0 to array.length - 1, bound checked.
        }while(randNum == xLoc);

        //swapping procedure; swap a x location to a different location in the array
        char temp = 0;
        currState[randNum] = temp;
        currState[randNum] = currState[xLoc];
        currState[xLoc] = temp;

        Node successor = new Node(currState.toString());
        successor.setParent(state);

        return successor;
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
            if (currState[i] == 'x' || currState[i] == 'X') {
                xLoc = i;
                break;
            }
        }

        //In every state, all possible successor states is string.length - 1
        ArrayList<Node> successors = new ArrayList<Node>();
        int stateCount = 0;
        int pivot = 0;
        int moveC = 0;
        while(stateCount < (currState.length - 1)){
            char temp = 0;
            if (pivot != xLoc){
                char tempArr[] = Arrays.copyOf(currState, currState.length);
                //swapping procedure; swap a x location to a different location in the array
                temp = tempArr[pivot];
                tempArr[pivot] = tempArr[xLoc];
                tempArr[xLoc] = temp;

                stateCount++;
                Node successor = new Node(new String(tempArr));
                successor.setMove(moveC);
                successor.setParent(state);
                successors.add(successor);
            }
            pivot++;
            moveC++;
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
