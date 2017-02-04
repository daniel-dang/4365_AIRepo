import java.util.Comparator;

/**
 * Created by mavis on 2/3/17.
 */
class GreedyComparator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        return n1.getRemC() - n2.getRemC();
    }
}

class AStarComparator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        return (n1.getRemC() + n1.getCost()) - (n2.getRemC() + n2.getCost());
    }
}

class UCSComparator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        return n1.getCost() - n2.getCost();
    }
}