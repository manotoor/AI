import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class Population {
	
	float mutationRate;
	DNA[] population;
	ArrayList<DNA> matingPool;
	int generations;
	boolean finished;
	int perfectScore;
	int startingSize;
	float maxFitness;
	int fitnessSum;
	
	public Population(int numQ ,float mutationRate,int startingSize) {
		this.mutationRate = mutationRate;
		this.startingSize = startingSize;
		maxFitness = 0.0f;
		population = new DNA[startingSize];
		for(int i = 0; i < population.length;i++) {
			population[i] = new DNA(numQ);
		}
		calcFitness();
		matingPool = new ArrayList<DNA>();
		finished = false;
		generations = 0;
		perfectScore = numQ * (numQ-1)/2;
	}
	public void print() {
		for(int i = 0; i < population.length;i++) {
			population[i].print();
		}
	}
	//normalize fitness;
	public void calcFitness() {
		fitnessSum = 0;
		for(int i =0; i < population.length;i++) {
			population[i].calcFitness();
			fitnessSum+=population[i].fitness;
		}
	}
	//Make the mating pool
	public void naturalSelection() {
		//empty pool
		matingPool.clear();
		
		float maxFitness = 0;
		for(int i =0; i < population.length;i++) {
			if(population[i].fitness > maxFitness) {
				maxFitness = population[i].fitness;
			}
		}
		Comparator<DNA> comparator = new DnaComparator();
		PriorityQueue<DNA> queue =
		  new PriorityQueue<DNA>(comparator);
		for(int i = 0; i < population.length;i++) {
			queue.add(population[i]);
		}
		for(int i = 0; i < (int)(population.length/8);i++) {
			matingPool.add(queue.poll());
		}
//		for(int i = 0; i < population.length;i++) {
//			float fitness = (float)(population[i].fitness/(float)fitnessSum);
//			int n = (int)(fitness *population.length*100);
//			for(int j =0; j<n;j++) {
//				matingPool.add(population[i]);
//			}
//		}
	}
	
	public void generate() {
		Random rand = new Random();
		for(int i =0; i < population.length;i++) {
			int a = rand.nextInt(matingPool.size());
			int b = rand.nextInt(matingPool.size());
			DNA partnerA = matingPool.get(a);
			DNA partnerB = matingPool.get(b);
			DNA child = partnerA.crossOver(partnerB);
			child.mutate(mutationRate);
			population[i] = child;
		}
		generations++;
	}
	public int[] getBest() {
		float worldrecord = 0.0f;
		int index = 0;
		for(int i =0; i < population.length;i++) {
			if(population[i].fitness > worldrecord) {
				index = i;
				worldrecord = population[i].fitness;
			}
		}
		if(worldrecord == perfectScore) finished = true;
		return population[index].genes;
	}
	boolean finished() {
		return finished;
	}
	//average fitness for the population
	float getAverageFitness() {
		float total = 0;
		for(int i =0; i < population.length;i++) {
			total +=population[i].fitness;
		}
		return total / (population.length);
	}
	//Selection
	//Mutation
	//Crossover
//	private geneticAlgorithm(population,Fitness_fn) {
//		do {
//			//new_population <- empty set
//			//loop for i from 1 to SIZE(population) do
//				//x <- random selection population fitness
//				//y<- Random selection population, fitness function
//		}while(fit || elapsed time)
//			return best individual in population
//	}
//	private DNA reproduce(DNA x, DNA y) {
//		int n = x.length;
//		int c = Math.random(n)+1;
//		return DNA(Append(Substring(x,1,c),Substring(y,c+1,n)));
//	}
}
