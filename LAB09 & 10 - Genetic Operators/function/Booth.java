package function;

import static java.lang.Math.pow;

public class Booth implements IFunction {

    //f(x) = (x1 + 2x2 - 7)^2 + (2x1 + x2 - 5)^2
    //The function is usually evaluated on the square xi ∈ [-10, 10]

    @Override
    public double count(double x, double y) {
        return pow(x + (2 * y) - 7, 2) +
                pow((2 * x) + y - 5, 2);
    }

    @Override
    public String getName() {
        return "Booth";
    }


}
