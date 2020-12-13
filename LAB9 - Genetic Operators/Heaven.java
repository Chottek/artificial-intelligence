import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

public class Heaven { //Takie heheszki że "Niechaj się dzieje wola Nieba, z Nią się zawsze zgadzać trzeba"

    private static final Logger EYE = LoggerFactory.getLogger(Heaven.class);

    public void ruleTheWorld(int maxGenerations){
        Random coincidence = new Random();
        Genetics god = new Genetics();            //Init genetic algorithm

        long start = System.currentTimeMillis();

        while(god.getGeneration() < maxGenerations){
            god.incrementGeneration();
            god.performSelection();
            god.performCrossOver();

            if(coincidence.nextInt() < 10){
                god.performMutation();
            }

            god.addFittestOffspring();
        }

        long exit = System.currentTimeMillis();

        EYE.info("Fitness: {}", god.getSociety().getFittestIndividual().getFitness());
        EYE.info("Genes: {}", Arrays.toString(god.getSociety().getFittestIndividual().getGenes()));
        EYE.info("Found in time: {} ms", (exit - start));
    }
}
