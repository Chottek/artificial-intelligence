package pl.fox.nailabs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.fox.nailabs.functions.IFunction;
import pl.fox.nailabs.util.GraphShower;

import static java.lang.Math.*;


public class Sampling implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Sampling.class);

    private final IFunction F;
    private final double MIN;
    private final double MAX;

    private final double[] xArr;
    private final double[] yArr;

    private static final int ITERATIONS = 100;

    public Sampling(IFunction f, double min, double max){
        this.F = f;
        this.MIN = min;
        this.MAX = max;

        xArr = new double[ITERATIONS];
        yArr = new double[ITERATIONS];
    }

    @Override
    public void run() {
        double best = 10;
        double bestX = 0;
        double bestY = 0;

        for (int i = 0; i < ITERATIONS; i++) {
            double x = random() * (MAX - MIN + 1) + MIN;
            double y = random() * (MAX - MIN + 1) + MIN;
            xArr[i] =  x;
            yArr[i] =  y;

            double temp = F.count(x, y);
            if (temp == 0) {
                bestX = x;
                bestY = y;
                break;
            } else if (abs(temp) <= best) {
                best = temp;
                bestX = x;
                bestY = y;
            }
        }

        LOG.info("\n" +
                "FUNCTION {}\n" +
                "Best X: {}\n" +
                "Best Y: {}\n" +
                "Diff: {} \n",F.getName(), bestX, bestY, best);

        if(bestX == 0 && bestY == 0 && best == 10){
            return;
        }

        new GraphShower().run(F.getName(), xArr, yArr);
    }
}
