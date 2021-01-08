import java.util.List;
import java.util.Random;

public class Genetics {

    private final Random rand;

    private static final int SOCIETY_SIZE = 10;         //Initial society size
    private static final int GENE_LENGTH = 50;          //Initial gene length
    private Individual iOne;                            //First and second individual
    private Individual iTwo;                            //Second fittest
    private int generation;                             //Generation

    private final Society s;                            //Init society (population) here

    public Genetics() {
        rand = new Random();
        s = new Society(SOCIETY_SIZE, GENE_LENGTH);
    }

    public void performSelection() {//TODO: Implement tournament selection
        Individual[] inds = s.performTournamentSelection(6);
        iOne = inds[0];
        iTwo = inds[1];
    }

    public void performCrossOver() {
        for (int i = 0; i < rand.nextInt(s.getIndividuals()[0].getGenes().length); i++) {
            int temp = iOne.getGenes()[i];
            iOne.getGenes()[i] = iTwo.getGenes()[i];
            iTwo.getGenes()[i] = temp;
        }
    }

    public void performMutation() {
        int mutationIndex = rand.nextInt(s.getIndividuals()[0].getGenes().length);
        iOne.getGenes()[mutationIndex] = (iOne.getGenes()[mutationIndex] == 0) ? 1 : 0;

        mutationIndex = rand.nextInt(s.getIndividuals()[0].getGenes().length);
        iTwo.getGenes()[mutationIndex] = (iTwo.getGenes()[mutationIndex] == 0) ? 1 : 0;
    }

    public Individual getFittestOffspring() {
        return (iOne.getFitness() > iTwo.getFitness()) ? iOne : iTwo;
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

}
