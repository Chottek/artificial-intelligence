package functions;

import static java.lang.Math.pow;

public class Beale implements IFunction {

    //f(x) = (1.5 - x1 + x1x2)^2 + (2.25 - x1 + x1x2^2)^2 + (2.625 - x1 + x1x2^3)^2
    //The function is usually evaluated on the square xi ∈ [-4.5, 4.5]

    @Override
    public double count(double x, double y) {
        return pow((1.5 - x + (x * y)), 2)
                + pow((2.25 - x + (x * pow(y, 2))), 2)
                + pow((2.626 - x + (x * pow(y, 3))), 2);
    }

    @Override
    public String getName() {
        return "Beale";
    }
}
