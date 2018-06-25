import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/*
 * Helper Class for nodes to add successors for each position
 * Also Prints the solution
 */
public class NodeUtil {
    public static List<String> getSuccessors(String state) {
    	char tmp = '*';
        List<String> successors = new ArrayList<String>();
        switch (state.indexOf("0")) {
        	//Movements are Right or down
            case 0: {
                successors.add(state.replace(state.charAt(0), tmp).replace(state.charAt(1), state.charAt(0)).replace(tmp, state.charAt(1)));
                successors.add(state.replace(state.charAt(0), tmp).replace(state.charAt(3), state.charAt(0)).replace(tmp, state.charAt(3)));
                break;
            }
            //Movements are left right or down
            case 1: {
                successors.add(state.replace(state.charAt(1), tmp).replace(state.charAt(0), state.charAt(1)).replace(tmp, state.charAt(0)));
                successors.add(state.replace(state.charAt(1), tmp).replace(state.charAt(2), state.charAt(1)).replace(tmp, state.charAt(2)));
                successors.add(state.replace(state.charAt(1), tmp).replace(state.charAt(4), state.charAt(1)).replace(tmp, state.charAt(4)));
                break;
            }
            //Movements are left or down
            case 2: {

                successors.add(state.replace(state.charAt(2), tmp).replace(state.charAt(1), state.charAt(2)).replace(tmp, state.charAt(1)));
                successors.add(state.replace(state.charAt(2), tmp).replace(state.charAt(5), state.charAt(2)).replace(tmp, state.charAt(5)));
                break;
            }
            //Movements are Up Right or Down
            case 3: {
                successors.add(state.replace(state.charAt(3), tmp).replace(state.charAt(0), state.charAt(3)).replace(tmp, state.charAt(0)));
                successors.add(state.replace(state.charAt(3), tmp).replace(state.charAt(4), state.charAt(3)).replace(tmp, state.charAt(4)));
                successors.add(state.replace(state.charAt(3), tmp).replace(state.charAt(6), state.charAt(3)).replace(tmp, state.charAt(6)));
                break;
            }
            //Movements are Up, Left, Right or Down
            case 4: {
                successors.add(state.replace(state.charAt(4), tmp).replace(state.charAt(1), state.charAt(4)).replace(tmp, state.charAt(1)));
                successors.add(state.replace(state.charAt(4), tmp).replace(state.charAt(3), state.charAt(4)).replace(tmp, state.charAt(3)));
                successors.add(state.replace(state.charAt(4), tmp).replace(state.charAt(5), state.charAt(4)).replace(tmp, state.charAt(5)));
                successors.add(state.replace(state.charAt(4), tmp).replace(state.charAt(7), state.charAt(4)).replace(tmp, state.charAt(7)));
                break;
            }
            //Movements are Up, Left or Down
            case 5: {
                successors.add(state.replace(state.charAt(5), tmp).replace(state.charAt(2), state.charAt(5)).replace(tmp, state.charAt(2)));
                successors.add(state.replace(state.charAt(5), tmp).replace(state.charAt(4), state.charAt(5)).replace(tmp, state.charAt(4)));
                successors.add(state.replace(state.charAt(5), tmp).replace(state.charAt(8), state.charAt(5)).replace(tmp, state.charAt(8)));
                break;
            }
            //Movements are Up or Right
            case 6: {
                successors.add(state.replace(state.charAt(6), tmp).replace(state.charAt(3), state.charAt(6)).replace(tmp, state.charAt(3)));
                successors.add(state.replace(state.charAt(6), tmp).replace(state.charAt(7), state.charAt(6)).replace(tmp, state.charAt(7)));
                break;

            }
            //Movments are Up Left or Down
            case 7: {
                successors.add(state.replace(state.charAt(7), tmp).replace(state.charAt(4), state.charAt(7)).replace(tmp, state.charAt(4)));
                successors.add(state.replace(state.charAt(7), tmp).replace(state.charAt(6), state.charAt(7)).replace(tmp, state.charAt(6)));
                successors.add(state.replace(state.charAt(7), tmp).replace(state.charAt(8), state.charAt(7)).replace(tmp, state.charAt(8)));
                break;
            }
            //Movements are Up or Left
            case 8: {
                successors.add(state.replace(state.charAt(8), tmp).replace(state.charAt(5), state.charAt(8)).replace(tmp, state.charAt(5)));
                successors.add(state.replace(state.charAt(8), tmp).replace(state.charAt(7), state.charAt(8)).replace(tmp, state.charAt(7)));
                break;
            }
        }
        //Return all successors
        return successors;
    }

    /*
     * Prints the transitions and the depth + number of steps to get there
     */
    public static void print(Node goalNode, Set<String> visitedNodes, Node root, int time) {
    	//get total cost and depth
        int totalCost = 0;
        //get the depth before traversing through stack
        int depth = goalNode.getDepth();
        //push goal to stack
        Stack<Node> solutionStack = new Stack<Node>();
        solutionStack.push(goalNode);
        //until we get to root, keep pushing parents
        while (!goalNode.getState().equals(root.getState())) {
            solutionStack.push(goalNode.getParent());
            goalNode = goalNode.getParent();
        }
        //set source to root
        String sourceState = root.getState();
        String destinationState;
        int cost = 0;
        //print the moves and the cost and the state
        for (int i = solutionStack.size() - 1; i >= 0; i--) {
            System.out.println("~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|");
            destinationState = solutionStack.get(i).getState();
            //Get the transition and number we are moving with the zero
            if (!sourceState.equals(destinationState)) {
                System.out.println("Move " + destinationState.charAt(sourceState.indexOf('0')) + " " + findTransition(sourceState, destinationState));
            }
            //Print the board
            sourceState = destinationState;
            System.out.println("~~~~~~~");
            System.out.println("| " + solutionStack.get(i).getState().substring(0, 3)+" |");
            System.out.println("| " + solutionStack.get(i).getState().substring(3, 6)+" |");
            System.out.println("| " + solutionStack.get(i).getState().substring(6, 9)+" |");
            System.out.println("~~~~~~~");

        }
        try {
	        FileWriter pw = new FileWriter(new File("test.csv"),true);
	        StringBuilder sb = new StringBuilder();
//	        sb.append("d");
//	        sb.append(',');
//	        sb.append("A* One");
//	        sb.append('\n');
//	
	        sb.append(depth);
	        sb.append(',');
	        sb.append(visitedNodes.size());
	        sb.append('\n');
	
	        pw.write(sb.toString());
	        pw.close();
        }catch(Exception e) {
        	//
        }

        System.out.println("~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|");
        System.out.println("|| Number of nodes generated:  " + (visitedNodes.size()));
        System.out.println("|| Number of Nodes poped out of the queue: " + time);
        System.out.println("|| Depth of the solution: " + depth);
        System.out.println("~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|~|");

    }

    /*
     * @return returns the transition between two states.
	*/
    
    public static MovementType findTransition(String source, String destination) {
        int zeroPositionDifference = destination.indexOf('0') - source.indexOf('0');
        switch (zeroPositionDifference) {
            case -3:
                return MovementType.DOWN;
            case 3:
                return MovementType.UP;
            case 1:
                return MovementType.LEFT;
            case -1:
                return MovementType.RIGHT;
        }
        return null;
    }
}


