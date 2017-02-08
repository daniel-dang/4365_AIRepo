import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Daniel Dang on 2/3/2017.
 * Co-authored by Mavis Francia.
 */
public class DFS extends SearchTree {
    private String iniState;
    private Node root;

    private Stack<Node> nodeList = new Stack<>(); //FILO data structure

    public DFS(String iniState){
        this.iniState = iniState;
        this.root = new Node(this.iniState, 0);
        this.root.setParent(null);
        nodeList.push(root);
        this.path = new Stack<>();
    }

    //--------------------GETTER AND SETTER FOR ROOT---------------------

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    //--------------------GENERIC SEARCH ALGORITHM (modified for depth cut off)---------------------
    /* Pseudocode
     * 1) Pop a node from nodeList (L)
     * 2) Goal test, if true -> return path to goal else,
     * 3) Check if node state is at depth cutoff; if not,
     * 4) Check if node state has already been expanded; if not,
     * 4) Insert (S,L) where S = successor state, L = nodeList
     * 5) if L is empty, return failure
     */
    public Stack<Node> search(){

        //Loop until the queue is empty.
        //If empty, return failure.
        while (!nodeList.isEmpty()) {// && nodeList.peek().getDepth() != currDepth) {
            Node currState = nodeList.pop();
            //if goal test fail, do...
            if (!goalTest(currState)) {
                //check if state already expanded
                if(!expandedStates.contains(currState.getState())) {
                    expandedStates.add(currState.getState());
                    ArrayList<Node> successors = getAllSuccessors(currState);
                    //push successors onto stack in reverse order (so, for example, 'move 0' is popped before 'move 4')
                    for (int i = successors.size() - 1; i >= 0; i--) {
                        nodeList.push(successors.get(i));
                    }
                }
            }
            //if goal test pass, return path
            else {
                return getPath(currState);
            }
        }
        return null;
    }
}
