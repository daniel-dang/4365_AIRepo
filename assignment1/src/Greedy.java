import java.util.*;

/**
 * Created by Mavis Francia on 2/3/2017.
 * This file implements GS (greedy best first search algorithm) using a Priority Queue based on h(n)
 */
public class Greedy extends SearchTree{
    private String iniState;
    private Node root;
    private Node parent;

    //priority queue based on greedy heuristic function: least estimated cost remaining
    private PriorityQueue<Node> nodeList = new PriorityQueue<>(10, new GreedyComparator());

    //constructor add root node to nodeList~(L)
    public Greedy(String iniState){
        this.iniState = iniState;
        this.root = new Node(this.iniState);
        this.root.setParent(null);
        nodeList.add(root);
        this.path = new Stack<>();
    }

    //--------------------GETTER AND SETTER FOR ROOT---------------------
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    //--------------------GENERIC SEARCH ALGORITHM---------------------
    /* Pseudocode
     * 1) Pop a node from nodeList (L)
     * 2) Goal test, if true -> return path to goal else,
     * 3) Check if node state has already been expanded; if not,
     * 4) Insert (S,L) where S = successor state, L = nodeList
     * 5) if L is empty, return failure
     */
    public Stack<Node> search(){
        //Loop until the queue is empty.
        //If empty, return failure.
        while(!nodeList.isEmpty()) {
            Node currState = nodeList.poll();

            //if goal test fail, do...
            if (!goalTest(currState)) {
                //check if current state has been expanded...
                if(!expandedStates.contains(currState.getState())) {
                    //...if not,
                    //add current state to set of expanded states
                    expandedStates.add(currState.getState());
                    //expand node by generating successors
                    ArrayList<Node> successors = getAllSuccessors(currState);
                    for (int i = 0; i < successors.size(); i++) {
                        //add successors to priority queue
                        nodeList.add(successors.get(i));
                    }
                }
            }
            //if goal test pass, return path
            else {
                return getPath(currState);
            }
        }
        return null; // goal state not found
    }
}
