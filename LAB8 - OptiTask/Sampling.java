import function.IFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static util.ArrUtils.*;
import static util.GenUtils.*;

public class Sampling implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Sampling.class);

    private final int size;
    private final int iterations;
    private final int accuracy;
    private final IFunction f;

    public Sampling(int size, int iterations, int accuracy, IFunction f) {
        this.size = size;
        this.iterations = iterations;
        this.accuracy = accuracy;
        this.f = f;
    }

    @Override
    public void run() {
        int[] arr = generateArr(size);

        double[] fen = toFenotype(arr, size, accuracy);

        double fitness = getFitness(fen, f);

        for (int i = 0; i < iterations; i++) {
            int[] tempTab = generateArr(size);

            double[] fen2 = toFenotype(tempTab, size, accuracy);

            double tempFitness = getFitness(fen2, f);

            if (fitness < tempFitness) {
                fitness = tempFitness;
            }
        }

        LOG.info("Best Fitness: {}", fitness);
    }

}
