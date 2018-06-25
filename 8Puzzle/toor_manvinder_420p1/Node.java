import java.util.ArrayList;
/*
 * Basic Node class for a state of the 8 Puzzle
 * Parent, children, state for it's current state, cost, estimated cost to goal h(n)
 * and it's depth (should be equal to cost)
 */
public class Node {

    private boolean visited;
    private String state;
    private ArrayList<Node> children;
    private Node parent;
    private int cost;
    private int estimatedCostToGoal;
    private int totalCost;
    private int depth;
    
    //Constructor for state
    public Node(String state) {
        this.state = state;
        children = new ArrayList<Node>();
    }
    
    //Visited this node before?
    public boolean isVisited() {
        return visited;
    }
    //Add child node
    public void addChild(Node child) {
        children.add(child);
    }
    /*
     * Setters and Getters
     */
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
  
    public int getTotalCost() {
        return totalCost;
    }
    
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
    
    public void setTotalCost(int cost, int estimatedCost) {
        this.totalCost = cost + estimatedCost;
    }
    
    public int getEstimatedCostToGoal() {
        return estimatedCostToGoal;
    }

    public void setEstimatedCostToGoal(int estimatedCostToGoal) {
        this.estimatedCostToGoal = estimatedCostToGoal;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getState() {
        return state;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

}
