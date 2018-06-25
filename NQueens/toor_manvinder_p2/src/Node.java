import java.util.ArrayList;
import java.util.Random;

public class Node implements Comparable<Node>{
	private static int n;
	public Queen[] state;
	private ArrayList<Node> neighbours;
	private int hn;
	
	public Node(int n) {
		this.n= n;
		state = new Queen[n];
		neighbours = new ArrayList<Node>();
	}
	public Node(Node node) {
		state = new Queen[n];
		neighbours = new ArrayList<Node>();
		for(int i =0;i<n;i++)
			state[i] = new Queen(node.state[i].getRow(),node.state[i].getColumn());
	}
	public ArrayList<Node> generateNeighbours(Node startState){
		int count = 0;
		
		for(int i =0; i < n;i++) {
			for(int j=1;j<n;j++) {
				neighbours.add(count, new Node(startState));
				neighbours.get(count).state[i].moveDown(j);
				neighbours.get(count).computeHeuristic();
				count++;
			}
		}
		return neighbours;
			
	}
	public Node getRandomNeighbour(Node startState) {
		Random rand = new Random();
		int col = rand.nextInt(n);
		int d = rand.nextInt(n-1)+1;
		
		Node neighbour = new Node(startState);
		neighbour.state[col].moveDown(d);
		neighbour.computeHeuristic();
		
		return neighbour;
	}
	public int computeHeuristic() {
		for(int i =0; i < n-1; i++) {
			for(int j=i+1;j<n;j++) {
				if(state[i].canAttack(state[j])) {
					hn++;
				}
			}
		}
		return hn;
	}
	
	public int getHeuristic() {
		return hn;
	}
	public int compareTo(Node n) {
		if(this.hn < n.getHeuristic())
			return -1;
		if(this.hn > n.getHeuristic())
			return 1;
		return 0;
	}
	public void setState(Queen[] s) {
		for(int i =0; i < n;i++) {
			state[i] = new Queen(s[i].getRow(), s[i].getColumn());
		}
	}
	public Queen[] getState() {
		return state;
	}
	public String toString() {
		String result = "";
		String[][] board = new String[n][n];
		for(int i =0; i <n;i++) {
			for(int j=0;j<n;j++) {
				board[i][j]="\t-";
			}
		}
		for(int i =0; i < n;i++) {
			board[state[i].getRow()][state[i].getColumn()]="Q ";
		}
		for(int i =0; i <n;i++) {
			for(int j=0; j<n;j++) {
				result += board[i][j];
			}
			result +="\n";
		}
		return result;
	}
}
