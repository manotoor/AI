import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Random;

public class Test {
	static int size;
	static int popmax;
	static float mutationRate;
	static Population population;
	public static void main(String[] args) throws IOException {
		/*
		 * Population(Number of Queens, Mutation Rate, Initial Size of Population)
		 */
		size =21;
		//For genetics
		popmax = 150;
		mutationRate = 0.5f;
		population = new Population(size,mutationRate,popmax);
		long startTime = System.currentTimeMillis();
		while(!population.finished()) {
			population.naturalSelection();
			population.generate();
			population.calcFitness();
			displayInfo();
		}
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);
		System.out.println(duration + "ms");
		//For RandomHillstart
		int runs = 50;
		int randomRestartNodes=0,randomRestartSuccesses=0;
		for(int i=0; i<runs; i++){
			Queen[] startBoard = generateBoard();
			startTime = System.currentTimeMillis();
			RandomRestart randomRestart = new RandomRestart(startBoard,size);	
			Node randomSolved = randomRestart.randomRestart();
			if(randomSolved.getHeuristic()==0){
				System.out.println(randomSolved);
				randomRestartSuccesses++;
				endTime = System.currentTimeMillis();
			}
			randomRestartNodes += randomRestart.getNodesGenerated();
			System.out.println(endTime - startTime);
			System.out.println("On iteration: " + i);
		}

		System.out.println("Random restart successes: "+randomRestartSuccesses);
		System.out.println();
		double randomRestartPercent = (double)(randomRestartSuccesses/runs);

		NumberFormat fmt = NumberFormat.getPercentInstance();

		System.out.println("Random Restart:\nNodes: "+randomRestartNodes);

		System.out.println("Percent successes: "+fmt.format(randomRestartPercent));
	}
	//For genetics
	public static void displayInfo() {
		DNA best = new DNA(population.getBest());
		System.out.println("Total generations: " + population.generations);
		System.out.println("Average fitness: " + population.getAverageFitness());
		System.out.println("Total population: " + popmax);
		System.out.println("mutation rate: " + mutationRate*100 + "%");
		if(population.finished()) {
			System.out.println("fitness: " + best.fitness);
			best.print();
			
		}
				
	}
	public static Queen[] generateBoard() {
		Queen[] temp = new Queen[size];
		Random gen = new Random();
		for(int i=0; i<size; i++){
			temp[i] = new Queen(gen.nextInt(size),i);
		}
		return temp;
	}
}
