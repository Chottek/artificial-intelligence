import java.util.Random;

public class Genetics {

    private final Random rand;

    private static final int SOCIETY_SIZE = 10;         //Initial society size
    private static final int GENE_LENGTH = 50;          //Initial gene length
    private Individual fittest;                         //First fittest
    private Individual fittest_2;                       //Second fittest
    private int generation;                             //Generation

    private final Society s;                            //Init society (population) here

    public Genetics() {
        rand = new Random();
        s = new Society(SOCIETY_SIZE, GENE_LENGTH);
    }

    public void performSelection() {
        fittest = s.getFittestIndividual();
        fittest_2 = s.getSecondFittestIndividual();
    }

    public void performCrossOver() {
        for (int i = 0; i < rand.nextInt(s.getIndividuals()[0].getGenes().length); i++) {
            int temp = fittest.getGenes()[i];
            fittest.getGenes()[i] = fittest_2.getGenes()[i];
            fittest_2.getGenes()[i] = temp;
        }
    }

    public void performMutation() {
        int mutationIndex = rand.nextInt(s.getIndividuals()[0].getGenes().length);
        fittest.getGenes()[mutationIndex] = (fittest.getGenes()[mutationIndex] == 0) ? 1 : 0;

        mutationIndex = rand.nextInt(s.getIndividuals()[0].getGenes().length);
        fittest_2.getGenes()[mutationIndex] = (fittest_2.getGenes()[mutationIndex] == 0) ? 1 : 0;
    }

    public Individual getFittestOffspring() {
        return (fittest.getFitness() > fittest_2.getFitness()) ? fittest : fittest_2;
    }

    public void addFittestOffspring() {
        s.getIndividuals()[s.getLeastFitIndex()] = getFittestOffspring();  //Replace least fit individual from the fittest offspring
    }


    //Getters

    public void incrementGeneration() {
        this.generation++;
    }

    public int getGeneration() {
        return generation;
    }

    public Society getSociety() {
        return s;
    }
}
