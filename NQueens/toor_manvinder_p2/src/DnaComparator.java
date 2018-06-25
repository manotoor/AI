import java.util.Comparator;

public class DnaComparator implements Comparator<DNA> {

	@Override
	public int compare(DNA o1, DNA o2) {
			if(o1.fitness > o2.fitness)
				return -1;
			if(o1.fitness < o2.fitness)
				return 1;
			return 0;
	}

}
