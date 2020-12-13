public class Individual {

    private final int[] genes;

    public Individual(int[] genes){
        this.genes = genes;
    }

    public int getFitness(){
        int fitness = 0;

        for (int gene : genes) {
            if (gene == 1) {
                fitness++;
            }
        }
        return fitness;
    }

    public int[] getGenes(){
        return genes;
    }


}
