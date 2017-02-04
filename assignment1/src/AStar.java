import java.util.*;

/**
 * Created by Mavis Francia on 2/3/2017.
 * This file implements A-Star search
 */
public class AStar extends SearchTree{
    private String iniState;
    private Node root;
    private Node parent;

    private PriorityQueue<Node> nodeList = new PriorityQueue<>(10, new AStarComparator());

    //constructor add root node to nodeList~(L)
    public AStar(String iniState){
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

            //skip if state already expanded
            if(expandedStates.contains(currState.getState()))
                continue;

            //if goal test fail, do...
            if (!goalTest(currState)) {
                expandedStates.add(currState.getState());
                ArrayList<Node> successors = getAllSuccessors(currState);
                for (int i = 0; i < successors.size(); i++) {
                    //skip if state already expanded
                    if(!expandedStates.contains(successors.get(i).getState()))
                        nodeList.add(successors.get(i));
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
