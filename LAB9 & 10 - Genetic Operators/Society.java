import function.Beale;
import function.IFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Society {

    private final Random rand = new Random();

    private final IFunction f = new Beale();    //Optimisation function used in calculating fitness

    private Individual[] individuals;           //Individuals array

    public Society(int initialSize, int geneLength) {
        initPopulation(initialSize, geneLength);   //Initializing population in constructor
    }

    public Individual[] performTournamentSelection(int max){
        List<Individual> inds = new ArrayList<>();
        List<Individual> winners = new ArrayList<>();

        for(int i = 0; i < max; i++){
            inds.add(individuals[rand.nextInt(individuals.length)]);
        }

        for(int j = 0; j < max / 2; j++){
            Individual i1 = inds.get(j);
            Individual i2 = inds.get(j+1);

            winners.add(i1.getFitness() < i2.getFitness() ? i2 : i1);
        }


        return new Individual[]{ winners.get(rand.nextInt(winners.size())), winners.get(rand.nextInt(winners.size()))};
    }


    public int getLeastFitIndex() {
        double minFitVal = Double.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (minFitVal >= individuals[i].getFitness()) {
                minFitVal = individuals[i].getFitness();
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    private void initPopulation(int initialSize, int geneLength) {
        Random rand = new Random();
        individuals = new Individual[initialSize];
        for (int j = 0; j < initialSize; j++) {
            int[] genes = new int[geneLength];
            for (int k = 0; k < geneLength; k++) {
                genes[k] = rand.nextInt(2);
            }
            individuals[j] = new Individual(genes, f);
        }
    }

    public Individual[] getIndividuals() {
        return individuals;
    }

}
