package pl.fox.crazyneurons.util;

public class Sigmoid implements IActivation {

    @Override
    public double function(double val) {
        return 1 / (1 + Math.exp(-val));
    }

    @Override
    public double derivative(double val) {
        return 0;
    }

}
