import java.util.*;

/**
 * Created by Mavis Francia on 2/3/2017.
 * This file implements UCS (uniform cost search algorithm)
 */
public class UCS extends SearchTree{
    private String iniState;
    private Node root;
    private Node parent;

    private PriorityQueue<Node> nodeList = new PriorityQueue<>(10, new UCSComparator());

    //constructor add root node to nodeList~(L)
    public UCS(String iniState){
        this.iniState = iniState;
        this.root = new Node(this.iniState);
        this.root.setParent(null);
        nodeList.add(root);
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
        //Loop until the queue is empty.
        //If empty, return failure.
        while(!nodeList.isEmpty()) {
            Node currState = nodeList.poll();
            //if goal test fails, do...
            if (!goalTest(currState)) {
                ArrayList<Node> successors = getAllSuccessors(currState);
                for (int i = 0; i < successors.size(); i++) {
                    nodeList.add(successors.get(i));
                }
            }
            //if goal test passes, return path
            else {
                return getPath(currState);
            }
        }
        return null;
    }
}
