import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * Mano Toor
 * CS 420 - 8 Puzzle with A* and two types of heuristics
 * February 10th, 2018
 */
public class eightPuzzle {
	final static private String STATE = "120345678";
    final static private String GOAL_STATE = "012345678";
    static boolean heurtwo = true;
    
    public static void main(String[] args) {
    	int option = -1;
    	System.out.println("**************************************************");
    	System.out.println("\t\tA* Search for 8Puzzle");
    	System.out.println("\t1. File Path");
    	System.out.println("\t2. Type Puzzle");
    	System.out.println("\t3. Random Puzzle");
    	System.out.println("\t4. Change Default Heuristic");
    	Scanner input = new Scanner(System.in);
    	option = input.nextInt();
    	while(option < 1 || option >3) {
    		if(option == 4) {
    			heurtwo = !heurtwo;
    			if(heurtwo)
    				System.out.println("Now using heuristic two");
    			else
    				System.out.println("Now using heuristic one");
    		}else {
    			System.out.println("not a valid option");
    		}
    		option = input.nextInt();
    	}
    	switch(option){
	        case 1:
	           readFile();
	           break;
	        case 2:
	           typePuzzle();
	           break;
	        case 3:
	           generatePuzzle();
	           break;
    	}
    	System.out.println("Thank you!");
    }
    public static void readFile() {
    	System.out.println("Please enter file path, i.e. list.txt");
    	System.out.println("If program crashes, could not locate file path");
    	System.out.println("Each line should contain a valid puzzle");
    	System.out.println("no error checking is done");
    	Scanner input = new Scanner(System.in);
    	String filename = input.nextLine();
    	String line;
    	try {
    		FileReader fileReader = new FileReader(filename);
                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while((line = bufferedReader.readLine()) != null) {
                    if(!Character.isAlphabetic(line.charAt(0))){
                    	solve(line);
                    }
                }   
                // Always close files.
                bufferedReader.close();
    	}catch(Exception e) {
    		System.out.println("Some went wrong when trying to open or read the file");
    	}
    }
    public static void typePuzzle() {
    	System.out.println("Please enter a valid puzzle in the format");
    	System.out.println("012345678");
    	System.out.println("Do not include spaces, no error checking is done to ensure a valid puzzle");
    	System.out.println("if an exception occurs it was because of an invalid puzzle");
    	Scanner input = new Scanner(System.in);
    	String state = input.nextLine();
    	solve(state);
    }
    public static void generatePuzzle() {
    	while(true) {
	    	List<Integer> container = new ArrayList<Integer>();
	    	Integer randomNumber;
	    	for(int i =0; i < 9;i++)
	    		container.add(i);
	    	String state = "";
	    	Random generator = new Random();
	    	for(int i =0; i < 9;i++) {
	    		randomNumber = container.get(generator.nextInt(container.size()));
	    		container.remove(randomNumber);
	    		state += randomNumber;
	    	}
	    	System.out.println("State is: " + state);
	    	solve(state);
	    	System.out.println("Continue? Y");
	    	Scanner input = new Scanner(System.in);
	    	String cont = input.next();
	    	if(!(cont.toLowerCase().charAt(0) == 'y'))
	    		break;
    	}
    }
    public static void solve(String state) {
    	 String rootState = state;
         long startTime = System.currentTimeMillis();
         //Determine if Heuristic is solvable
         if(Solvable.canSolve(rootState)) {
 	        SearchTree search = new SearchTree(new Node(rootState), GOAL_STATE);
 	        if(heurtwo)
 	        	search.aStar(Heuristic.TWO);
 	        else
 	        	search.aStar(Heuristic.ONE);
 	        long finishTime = System.currentTimeMillis();
 	        long totalTime = finishTime - startTime;
 	        System.out.println("Time  :" + totalTime + "ms");
         }else {
         	System.out.println(rootState + " Is not solvable");
         }
    }
}
