import java.util.Comparator;

/**
 * Created by Mavis Francia on 2/3/17.
 */

//Comparator to compare nodes based on characters out of place (heuristic h(n))
class GreedyComparator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        return n1.getRemC() - n2.getRemC();
    }
}

//Comparator to compare nodes based on heuristic + movement cost
class AStarComparator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        return (n1.getRemC() + n1.getCost()) - (n2.getRemC() + n2.getCost());
    }
}

//Comparator to compare nodes based on movement cost (g(n))
class UCSComparator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        return n1.getCost() - n2.getCost();
    }
}