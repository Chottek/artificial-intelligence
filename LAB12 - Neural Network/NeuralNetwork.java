package pl.fox.crazyneurons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class NeuralNetwork {

    private static final Logger LOG = LoggerFactory.getLogger(NeuralNetwork.class);

    public static final double BIAS = 3.0D;
    public static final int WEIGHT_SIZE = 10;

    private static final Double[] VALUES = {0D, 1D, 2D, 3D, 4D, 5D, 6D, 7D, 8D, 9D, 10D};

    private final Layer inputLayer;
    private final Layer outputLayer;

    public NeuralNetwork(){
        inputLayer = new Layer(2, WEIGHT_SIZE);
        outputLayer = new Layer(1, inputLayer.getNeurons().size());
    }

    public void think(){
        java.util.List<Double> values = new java.util.ArrayList<>();

        for(Neuron n : inputLayer.getNeurons()){
            values.add(n.calculateOutput(Arrays.asList(VALUES)));
        }

        for(Neuron n: outputLayer.getNeurons()){
            LOG.info("{}", n.calculateOutput(values));
        }
    }




}
