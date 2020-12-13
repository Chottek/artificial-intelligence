import function.Beale;
import function.IFunction;
import util.GenUtils;

public class Individual {

    private final int[] genes;
    private final IFunction f;

    public Individual(int[] genes, IFunction f) {
        this.genes = genes;
        this.f = f;
    }

    public double getFitness() {
        return GenUtils.getFitness(GenUtils.toPhenotype(genes, 5), f);
    }

    public int[] getGenes() {
        return genes;
    }
}
