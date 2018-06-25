import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class SearchTree {
	
	//Root node and goalState 012345678
    private Node root;
    private String goalState;
    
    //Setter Getters
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String getgoalState() {
        return goalState;
    }

    public void setgoalState(String goalState) {
        this.goalState = goalState;
    }
    //Constructor
    public SearchTree(Node root, String goalState) {
        this.root = root;
        this.goalState = goalState;
    }


    /* A* heuristic uses the step cost g(n) + the heuristic cost h(n) to determine the function cost f(n)
     * then expands that path using a priority queue
     */

    public void aStar(Heuristic heuristic) {
        // stateSet is the set of nodes put on Frontier
        Set<String> stateSets = new HashSet<String>();
        int totalCost = 0;
        int time = 0;
        Node node = new Node(root.getState());
        node.setTotalCost(0);
        node.setCost(0);
        node.setDepth(0);

        //compare the cost of nodes
        NodePriorityComparator nodePriorityComparator = new NodePriorityComparator();

        // sorted queue by priority of cost
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<Node>(10, nodePriorityComparator);
        Node currentNode = node;
        //grab node from priority queue, see if it is goal state, if it is return
        //otherwise iterate and get successor until we reach goalstate
        while (!currentNode.getState().equals(goalState)) {
            stateSets.add(currentNode.getState());
            List<String> nodeSuccessors = NodeUtil.getSuccessors(currentNode.getState());
            for (String n : nodeSuccessors) {
				if (stateSets.contains(n))
				    continue;
				stateSets.add(n);
                Node child = new Node(n);
                currentNode.addChild(child);
                child.setParent(currentNode);
                child.setDepth(currentNode.getDepth() + 1);
                child.setCost(currentNode.getDepth() + 1);
                if (heuristic == Heuristic.ONE) {
                	child.setTotalCost(currentNode.getDepth() + 1, heuristicOne(child.getState(), goalState));
                }else if (heuristic == Heuristic.TWO) {
                	child.setTotalCost(currentNode.getDepth() + 1, heuristicTwo(child.getState(), goalState));
                }
                nodePriorityQueue.add(child);
                
            }
            currentNode = nodePriorityQueue.poll();
            //number of nodes popped from queue
            time += 1;
        }
        NodeUtil.print(currentNode, stateSets, root, time);
    }

    // This heuristic estimates the cost to the goal from current state by counting the number of misplaced tiles
    private int heuristicOne(String currentState, String goalState) {
        int difference = 0;
        for (int i = 0; i < currentState.length(); i += 1)
            if (currentState.charAt(i) != goalState.charAt(i))
                difference += 1;
        return difference;
    }

    // This heuristic estimates the cost to the goal from current state by calculating the Manhathan distance from each misplaced
    // tile to its right position in the goal state
    private int heuristicTwo(String currentState, String goalState) {
        int difference = 0;
        for (int i = 0; i < currentState.length(); i += 1)
            for (int j = 0; j < goalState.length(); j += 1)
                if (currentState.charAt(i) == goalState.charAt(j))
                    difference = difference + ((Math.abs(i / 3 - j / 3)) + Math.abs(i % 3 - j % 3));
        return difference;
    }
}