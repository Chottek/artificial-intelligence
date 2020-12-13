import java.util.Random;

public class Society {

    private Individual[] individuals;
    private double fittestNumber;
    private final int geneLength = 40;

    public Society(int initialSize){
        this.fittestNumber = 0;

        initPopulation(initialSize);
    }

    public Individual getFittestIndividual(){
        double fittest = -1D;
        int index = 0;

        for(int i = 0; i < individuals.length; i++){
            if (fittest <= individuals[i].getFitness()) {
                fittest = individuals[i].getFitness();
                index = i;
            }
        }

        fittestNumber = individuals[index].getFitness();
        return individuals[index];
    }

    public Individual getSecondFittestIndividual() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i].getFitness() > individuals[maxFit1].getFitness()) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (individuals[i].getFitness() > individuals[maxFit2].getFitness()) {
                maxFit2 = i;
            }
        }
        return individuals[maxFit2];
    }

    public int getLeastFitIndex(){
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

    private void initPopulation(int initialSize){
        Random rand = new Random();
        individuals = new Individual[initialSize];
        for(int j = 0; j < initialSize; j++){
            int[] genes = new int[geneLength];
            for (int k = 0; k < geneLength; k++) {
                genes[k] = rand.nextInt(2);
            }
            individuals[j] = new Individual(genes);
        }
    }

    public Individual[] getIndividuals() {
        return individuals;
    }

    public double getFittestNumber(){
        return fittestNumber;
    }

    public int getGeneLength() {
        return geneLength;
    }
}
