package pl.fox.neuralsnake.sqola.util;

public class NeuralMath {

    public static double activate(double val){
        return 1 / (1 + Math.exp(-val));
    }


}
