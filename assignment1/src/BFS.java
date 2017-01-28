import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Daniel Dang on 1/28/2017.
 * This file implements BFS (breadth first search algorithm)
 * The BFS search will spawn all of the possible successor state in every depth,
 * continue until a solution is found.
 */
public class BFS {
    private String input;
    private Node root;

    private Queue<Node> nodeList = new LinkedList<>();

    //constructor add root node to nodeList~(L)
    public BFS(String input){
        this.input = input;
        this.root = new Node(input);
        nodeList.add(root);
    }

    /* Pseudocode
     * 1) Pop a node from nodeList (L)
     * 2) Goal test, if true -> return path to goal else,
     * 3) Insert (S,L) where S = successor state, L = nodeList
     * 4) if L is empty, return failure
     */
    public void search(){

    }

    public void insert(Node s){

    }
}
