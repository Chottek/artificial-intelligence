import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

public class Heaven { //Takie heheszki że "Niechaj się dzieje wola Nieba, z Nią się zawsze zgadzać trzeba"

    private static final Logger EYE = LoggerFactory.getLogger(Heaven.class); //The eye of truth is watching you

    public void ruleTheWorld(int maxGenerations) {
        Random coincidence = new Random();
        Genetics god = new Genetics();                  //Init genetic algorithm

        Individual fittestEver = null;
        double fittestEverValue = 0D;
        int fittestGeneration = 0;

        long start = System.currentTimeMillis();        //Get time when everything begins

        while (god.getGeneration() < maxGenerations) {
            god.incrementGeneration();                  //generation++, starting from 0
            god.performSelection();
            god.performCrossOver();

            if (coincidence.nextInt() < 10) {
                god.performMutation();                  //randomly mutate
            }

            god.addFittestOffspring();

            if(Double.parseDouble(String.valueOf(god.getFittestOffspring().getFitness()).substring(0, 5)) > fittestEverValue){
                fittestEver = god.getFittestOffspring();
                fittestEverValue = Double.parseDouble(String.valueOf(god.getFittestOffspring().getFitness()).substring(0, 5));
                fittestGeneration = god.getGeneration();
            }

            EYE.info("Fittest ({}) on generation {}", String.valueOf(god.getFittestOffspring().getFitness()).substring(0, 5), god.getGeneration());
        }

        long exit = System.currentTimeMillis();         //Get time when everything ends

        EYE.info("_______________________________________");
        EYE.info("Fittest generation was {} with value {}", fittestGeneration, String.valueOf(fittestEverValue).substring(0, 5));
        EYE.info("Fittest ever genes: {}", Arrays.toString(fittestEver.getGenes()));
        //EYE.info("Fitness: {}", god.getSociety().getFittestIndividual().getFitness());
        //EYE.info("Last genes: {}", Arrays.toString(god.getSociety().getFittestIndividual().getGenes()));
        EYE.info("Runtime: {} ms", (exit - start));
    }
}
