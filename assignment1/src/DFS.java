import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Daniel Dang on 2/3/2017.
 */
public class DFS extends SearchTree {
    private String iniState;
    private Node root;
    private Node parent;

    private int absoluteDepth = 6;
    private int depthCutOff = 6;
    private Stack<Node> nodeList = new Stack<>();

    public DFS(String iniState){
        this.iniState = iniState;
        this.root = new Node(this.iniState);
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
        int currDepth = 0;
        //Loop until the queue is empty.
        //If empty, return failure.
        while(!nodeList.isEmpty() && absoluteDepth != currDepth) {
            Node currState = nodeList.pop();
            //if goal test fail, do...
            if (!goalTest(currState)) {
                Node successor;
                do {
                    successor = getSuccessor(currState);    //keep getting successor until the new successor is unique
                }while(nodeList.contains(successor));
                nodeList.push(successor);
                currDepth++;
            }
            //if goal test pass, return path
            else {
                return getPath(currState);
            }

            //Using iterative deepening technique, i.e increase depth if no solution found.
            if(currDepth == depthCutOff){
                nodeList.clear();       //wipe the stack
                nodeList.push(root);    //add back the root to the stack
                currDepth = 0;          //reset current depth, and start over
            }
        }
        return null;
    }
}
