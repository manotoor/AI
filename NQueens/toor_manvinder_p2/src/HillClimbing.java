import java.util.ArrayList;
import java.util.Random;

public class HillClimbing {
	private static int N;
	private Queen[] startState;
	private Node start;
	private int nodesGenerated;
	public HillClimbing(int size) {
		N = size;
		start = new Node(N);
		startState = new Queen[N];
		startState();
		nodesGenerated = 0;
	}
	public HillClimbing(Queen[] s) {
		N = s.length;
		start = new Node(N);
		startState = new Queen[N];
		for(int i =0; i < s.length;i++) {
			startState[i] = new Queen(s[i].getRow(), s[i].getColumn());
		}
		start.setState(startState);
		start.computeHeuristic();
		
		nodesGenerated=0;
	}
	public void startState() {
		Random gen = new Random();
		for(int i =0; i < N;i++) {
			startState[i] = new Queen(gen.nextInt(N),i);
		}
		start.setState(startState);
		start.computeHeuristic();
	}
	public Node hillClimbing() {
		Node currentNode = start;
		while(true) {
			ArrayList<Node> successors = currentNode.generateNeighbours(currentNode);
			nodesGenerated+=successors.size();
			Node nextNode = null;
			
			for(int i =0; i < successors.size();i++) {
				if(successors.get(i).compareTo(currentNode)<0) {
					nextNode = successors.get(i);
				}
			}
			if(nextNode == null)
				return currentNode;
			currentNode = nextNode;
		}
	}
	public Node getStartNode() {
		return start;
	}
	public int getNodesGenerated() {
		return nodesGenerated;
	}
}
