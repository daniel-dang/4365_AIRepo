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
    public Node getSuccessor(Node state){
        //return if initial state is empty
        if (state.getState().equals(""))
            return null;

        //Get location of 'x'
        char[] currState = processStr(state.getState());
        int xLoc = 0;
        //Find 'x'
        for (int i = 0; i < currState.length; i++){
            if (currState[i] == 'x' || currState[i] == 'X') {
                xLoc = i;
                break;
            }
        }

        //Generate random number, repeat if random equal mid index.
        int randNum = -1;
        do {
            //random generate a number between 0 to state.length
            //pattern for generating random number [min,max] => (min + Random.nextInt(max - min) + 1)
            Random rn = new Random();
            randNum = rn.nextInt(currState.length);      //generate random number from 0 to array.length - 1, bound checked.
        }while(randNum == xLoc);

        //swapping procedure; swap an x location to a different location in the array
        char temp = 0;
        temp = currState[randNum];
        currState[randNum] = currState[xLoc];
        currState[xLoc] = temp;

        //make new node using the new swapped state
        Node successor = new Node(new String(currState));
        successor.setMove(state.getMove() + 1);
        successor.setParent(state);

        return successor;
    }

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

                //adding newly created state to a new node
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

    //overloaded version of previous function to add depth information to nodes
    public ArrayList<Node> getAllSuccessors(Node state, int currDepth){
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

                //adding newly created state to a new node
                stateCount++;
                Node successor = new Node(new String(tempArr), currDepth + 1);
                successor.setMove(moveC);
                successor.setParent(state);
                successors.add(successor);
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
