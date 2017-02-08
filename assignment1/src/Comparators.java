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
        int fDiff = (n1.getRemC() + n1.getCost()) - (n2.getRemC() + n2.getCost());
        if(fDiff != 0)
            return fDiff;
        else {
            int depthDiff = getDepth(n1) - getDepth(n2);
            if(depthDiff != 0)
                return depthDiff;
            else {
                int moveDiff = n1.getMove() - n2.getMove();
                return moveDiff;
            }
        }

    }

    private int getDepth(Node n) {
        if(n.getParent() == null)
            return 0;
        else {
            int depth = 0;
            while(n.getParent() != null) {
                depth++;
                n = n.getParent();
            }
            return depth;
        }
    }
}

//Comparator to compare nodes based on movement cost (g(n))
class UCSComparator implements Comparator<Node> {

    public int compare(Node n1, Node n2) {
        int costDiff = n1.getCost() - n2.getCost();
        if(costDiff != 0)
            return costDiff;
        else {
            int depthDiff = getDepth(n1) - getDepth(n2);
            if(depthDiff != 0)
                return depthDiff;
            else {
                int moveDiff = n1.getMove() - n2.getMove();
                return moveDiff;
            }

        }

    }

    private int getDepth(Node n) {
        if(n.getParent() == null)
            return 0;
        else {
            int depth = 0;
            while(n.getParent() != null) {
                depth++;
                n = n.getParent();
            }
            return depth;
        }
    }
}