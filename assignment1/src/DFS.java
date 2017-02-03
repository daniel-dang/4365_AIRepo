import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Daniel Dang on 2/3/2017.
 */
public class DFS extends SearchTree {
    private String iniState;
    private Node root;
    private Node parent;

    private int absoluteDepth = 10;
    private int depthCutOff = 6;
    private Stack<Node> nodeList = new Stack<>();

    public DFS(String iniState){
        this.iniState = iniState;
        this.root = new Node(this.iniState, 0);
        this.root.setParent(null);
        nodeList.push(root);
        this.path = new Stack<>();
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    /* Pseudocode
     * 1) Pop a node from nodeList (L)
     * 2) Goal test, if true -> return path to goal else,
     * 3) Insert (S,L) where S = successor state, L = nodeList
     * 4) if L is empty, return failure
     */
    public Stack<Node> search(){
        Node currState = null;
        //Loop until the queue is empty.
        //If empty, return failure.
        while(!nodeList.isEmpty()) {// && nodeList.peek().getDepth() != currDepth) {
            currState = nodeList.pop();
            if(currState.getDepth() < depthCutOff) {
                //if goal test fail, do...
                if (!goalTest(currState)) {
                    ArrayList<Node> successors = getAllSuccessors(currState, currState.getDepth());
                    for (int i = 0; i < successors.size(); i++) {
                        nodeList.push(successors.get(i));
                    }
                }
                //if goal test pass, return path
                else {
                    return getPath(currState);
                }
            }
        }

        //Using iterative deepening technique, i.e increase depth if no solution found.
        if(currState.getDepth() == depthCutOff && depthCutOff <= absoluteDepth){
            nodeList.clear();       //wipe the stack
            nodeList.push(root);    //add back the root to the stack
            depthCutOff++;          //increment depth cutoff
            return search();        //start over
        }

        return null;
    }
}
