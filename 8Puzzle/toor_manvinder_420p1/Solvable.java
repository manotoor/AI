
public class Solvable {
	public static boolean canSolve(String state) {
		int inversions = 0;
		for(int i =0; i < state.length();i++) {
			if(state.charAt(i) != '0') {
				for(int j = i+1; j < state.length();j++) {
					if(state.charAt(j) != '0') {
						if(Integer.valueOf(state.charAt(i)) > Integer.valueOf(state.charAt(j)))
							inversions++;
					}
				}
			}
		}
		if(inversions %2 == 0)
			return true;
		return false;
	}
}
