import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Daniel Dang on 1/28/2017.
 * This file implements BFS (breadth first search algorithm)
 * The BFS search will spawn all of the possible successor state in every depth,
 * continue until a solution is found.
 */
public class BFS extends SearchTree{
    private String iniState;
    private Node root;
    private Node parent;

    private Queue<Node> nodeList = new LinkedList<>();

    //constructor add root node to nodeList~(L)
    public BFS(String input){
        this.iniState = input;
        this.root = new Node(iniState);
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
            //if goal test fail, do...
            if (!goalTest(currState)) {
                ArrayList<Node> successors = getAllSuccessors(currState);
                for (int i = 0; i < successors.size(); i++) {
                    nodeList.add(successors.get(i));
                }
            }
            //if goal test pass, print path
            else {
                return getPath(currState);
            }
        }
        //If no solution, return failure.
        return null;
    }

    public boolean goalTest(Node state){
        int mid = state.getCurrentState().length() / 2;

        /*
         * If middle character not 'x' => fail
         * If character [0, mid - 1] not 'B' => fail
         * If character [mid + 1, end] not 'W' => fail
         * If nothing happen, pass all test, return true. Goal test pass.
         */
        for (int i = 0; i < state.getCurrentState().length(); i++){
            if (i == mid && state.getCurrentState().charAt(mid) != 'x')
                return false;
            if ((i < mid) && (state.getCurrentState().charAt(i) != 'B'))
                return false;
            if ((i > mid) && (state.getCurrentState().charAt(i) != 'W'))
                return false;
        }
        return true;
    }
}
