package pl.fox.crazyneurons.util;

public class NeuralMath {

    public static double activate(double val){
        return 1 / (1 + Math.exp(-val));
    }

}
