import java.util.Comparator;

/**
 * Created by Mavis Francia on 2/3/17.
 */

//Comparator to compare nodes based on characters out of place (heuristic h(n)), break ties by FIFO
class GreedyComparator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        int hDiff =  n1.getRemC() - n2.getRemC();
        if(hDiff != 0)
            return hDiff;
        else
            return n1.getNodeNum() - n2.getNodeNum();
    }
}

//Comparator to compare nodes based on heuristic + movement cost, break ties by FIFO
class AStarComparator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        int fDiff = (n1.getRemC() + n1.getCost()) - (n2.getRemC() + n2.getCost());
        if(fDiff != 0)
            return fDiff;
        else
            return n1.getNodeNum() - n2.getNodeNum();

    }
}

//Comparator to compare nodes based on movement cost (g(n)), break ties by FIFO
class UCSComparator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {

        int costDiff = n1.getCost() - n2.getCost();
        if(costDiff != 0)
            return costDiff;
        else {
            return n1.getNodeNum() - n2.getNodeNum();
        }
    }
}