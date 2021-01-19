package pl.fox.crazyneurons;

import java.util.List;
import java.util.stream.IntStream;

public class Layer {

    private final java.util.List<Neuron> neurons;

    public Layer(int size, int weightsSize){
        neurons = new java.util.ArrayList<>();
        initNeurons(size, weightsSize);
    }

    private void initNeurons(int size, int weightsSize){
        IntStream.range(0, size).mapToObj(i -> new Neuron(weightsSize)).forEach(neurons::add);
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }
}
