import java.util.Comparator;

/*
 * Compares two nodes by total cost, if the left node total cost is cheaper we return -1, if it is greater we return positive 1
 * otherwise they are equal and return 0
 */
public class NodePriorityComparator implements Comparator<Node> {
    @Override
    public int compare(Node x, Node y) {
        if (x.getTotalCost() < y.getTotalCost()) {
            return -1;
        }
        if (x.getTotalCost() > y.getTotalCost()) {
            return 1;
        }
        return 0;
    }
}