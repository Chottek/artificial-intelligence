import function.Beale;
import util.GenUtils;

public class Individual {

    private final int[] genes;

    public Individual(int[] genes){
        this.genes = genes;
    }

    public double getFitness(){
        return GenUtils.getFitness(GenUtils.toPhenotype(genes, 5), new Beale());
    }

    public int[] getGenes(){
        return genes;
    }

}
