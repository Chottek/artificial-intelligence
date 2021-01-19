package pl.fox.crazyneurons;

import pl.fox.crazyneurons.util.Sigmoid;

import java.util.stream.IntStream;

public class Neuron {

    private final java.util.List<Double> weights;

    public Neuron(int weightSize) {
        weights = new java.util.ArrayList<>();
        initWeights(weightSize);
    }

    private void initWeights(int weightSize){
        IntStream.range(0, weightSize).forEach(i -> weights.add(Math.random()));
    }

    public double calculateOutput(java.util.List<Double> values){
        return new Sigmoid().function( NeuralNetwork.BIAS +
                IntStream.range(0, weights.size()).mapToDouble(i -> weights.get(i) * values.get(i)).sum());
    }

}
