import function.Beale;
import function.Booth;
import function.HimmelBlau;
import function.IFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static util.ArrUtils.*;
import static util.GenUtils.*;

public class Exec {

    private static final Logger LOG = LoggerFactory.getLogger(Exec.class);

    private final IFunction beale = new Beale();
    private final IFunction booth = new Booth();
    private final IFunction himmel = new HimmelBlau();

    public void fireUp(int iterations, int size, int accuracy){
        int[] arr = generateArr(size);
        LOG.info("Generated array: \n{}", Arrays.toString(arr));

        int[] gray = toGray(arr);
        LOG.info("Grayed array: \n{}", Arrays.toString(gray));

        double[] pheno = toFenotype(arr, size, accuracy);
        LOG.info("Phenotyped array: \n{}", Arrays.toString(pheno));

        int[] geno = toGenotype(size, pheno[0], pheno[1], accuracy);
        LOG.info("Genotyped array: \n{}\n", Arrays.toString(geno));

        LOG.info("<---------- SAMPLING BELOW ---------->\n");

        var t1 = new Thread(new Sampling(size, iterations, accuracy, himmel));
        t1.setName(himmel.getName());
        t1.start();

        var t2 = new Thread(new Sampling(size, iterations, accuracy, booth));
        t2.setName(booth.getName());
        t2.start();

        var t3 = new Thread(new Sampling(size, iterations, accuracy, beale));
        t3.setName(beale.getName());
        t3.start();
    }
}
