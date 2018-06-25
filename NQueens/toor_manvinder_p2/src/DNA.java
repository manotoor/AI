import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DNA {
	//genes is the positions of the queens per column
	int[] genes;
	float fitness;
	int MAX_NUMBER;
	//Random Generator Constructor
	DNA(int size){
		MAX_NUMBER = size * (size-1)/2;
		genes = new int[size];
		Random rand = new Random();
		for(int i = 0;i<size;i++) {
			genes[i] = rand.nextInt(size);
		}
		//fitness = myFitness();
		//remove pointer to rand in hope garbage collection deletes it from memory
		rand = null;
	}
	DNA(int[] best){
		genes = best;
		MAX_NUMBER = best.length * (best.length-1)/2;
		fitness = myFitness();
	}
	//CrossOver Constructor
	DNA(DNA parent1, DNA parent2){
		Random rand = new Random();
		int size = parent1.genes.length;
		int midpoint = rand.nextInt(size);
		genes = new int[size];
		MAX_NUMBER = size * (size-1)/2;
		//fitness = -1;
		for(int i =0;i<size;i++) {
			if(i > midpoint)
				genes[i] = parent1.genes[i];
			else
				genes[i] = parent2.genes[i];
		}
		//fitness = myFitness();
	}
	public void mutate(double mutationRate) {
		Random rand = new Random();
		if((double)(rand.nextInt(genes.length) / (double)genes.length) >mutationRate) {
			genes[rand.nextInt(genes.length)] = rand.nextInt(genes.length);
		}
		this.fitness = myFitness();
	}
	public void calcFitness() {
		int clashes = 0;
		for(int i =genes.length-1; i >=0;i--) {
			for(int j=i-1;j>=0;j--) {
				//Gives us horizontal
				if(genes[i] == genes[j]) {
					clashes++;
				}
				//Check vertical
				if(Math.abs(i-j) == Math.abs(genes[i]-genes[j]))
					clashes++;
			}
		}
		this.fitness = (float)(MAX_NUMBER - clashes);
	}
	private float myFitness() {
		int clashes = 0;
		for(int i =genes.length-1; i >=0;i--) {
			for(int j=i-1;j>=0;j--) {
				//Gives us horizontal
				if(genes[i] == genes[j]) {
					clashes++;
				}
				//Check vertical
				if(Math.abs(i-j) == Math.abs(genes[i]-genes[j]))
					clashes++;
			}
		}
		return (float)(MAX_NUMBER -clashes)/(float)MAX_NUMBER;
	}
	public DNA crossOver(DNA partner) {
		DNA child;
		child = new DNA(this,partner);
		return child;
	}
	public void print() {
		System.out.println("Printing sequence: ");
		if(genes.length < 1) {
			System.out.println("No solution");
			return;
		}
		for(int row = genes.length-1; row >=0;row--) {
			for(int col = 0; col<genes.length;col++) {
				if(genes[col] != row)
					System.out.print("\t-");
				else
					System.out.print("\tQ");
			}
			System.out.println("");
		}
	}
}