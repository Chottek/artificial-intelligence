package pl.fox.crazyneurons;

import static pl.fox.crazyneurons.util.NeuralMath.activate;

public class Neuron {

    private final int[] weights;
    private final int bias;

    public Neuron(int[] weights, int bias) {
        this.weights = weights;
        this.bias = bias;
    }

    public double calculateOutput(int[] values){
        return activate(bias +
                java.util.stream.IntStream.range(0, weights.length).mapToDouble(i -> weights[i] * values[i]).sum());
    }
}
