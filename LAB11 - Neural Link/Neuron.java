package pl.fox.neuralsnake.sqola;

import pl.fox.neuralsnake.sqola.util.NeuralMath;

import java.util.stream.IntStream;

public class Neuron {

    private final int[] weights;
    private final int bias;

    public Neuron(int[] weights, int bias) {
        this.weights = weights;
        this.bias = bias;
    }

    public double calculateOutput(int[] values){
        return NeuralMath.activate(bias +
                IntStream.range(0, weights.length).mapToDouble(i -> weights[i] * values[i]).sum());
    }
}
