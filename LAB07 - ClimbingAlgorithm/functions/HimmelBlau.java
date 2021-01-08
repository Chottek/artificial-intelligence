package functions;

import static java.lang.Math.pow;

public class HimmelBlau implements IFunction {

    //f(x,y) = (x^2 + y - 11)^2 + (x + y^2 - 7)^2
    //The function can be defined on any input domain but it is usually evaluated on xi ∈ [-6,6]

    @Override
    public double count(double x, double y) {
        return pow(pow(x, 2) + y - 11, 2) +
                pow(x + pow(y, 2) - 7, 2);
    }

    @Override
    public String getName() {
        return "HimmelBlau";
    }

}
