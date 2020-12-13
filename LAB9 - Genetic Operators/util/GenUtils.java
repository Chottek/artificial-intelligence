package util;

import function.IFunction;

import static java.lang.Math.abs;
import static java.lang.StrictMath.pow;

public class GenUtils {

    public static void reverseArr(int[] tab) {
        for (int i = 0; i < tab.length / 2; i++) {
            int temp = tab[i];
            tab[i] = tab[tab.length - i - 1];
            tab[tab.length - i - 1] = temp;
        }
    }

    public static double toDecimal(int[] tab, int size) {
        double result = 0;
        reverseArr(tab);
        for (int i = 0; i < size / 2; i++) {
            result += tab[i] * pow(2, i);
        }
        return result;
    }

    public static double[] toPhenotype(int[] arr, int accuracy) {
        int[] tempX = new int[arr.length / 2];
        int[] tempY = new int[arr.length / 2];
        for (int i = 0; i < arr.length / 2; i++) {
            tempX[i] = arr[i];
            tempY[i] = arr[i + arr.length / 2];
        }

        return new double[]{ toDecimal(tempX, arr.length) / pow(10, accuracy), toDecimal(tempY, arr.length) / pow(10, accuracy)};
    }

    public static double getFitness(double[] fenotype, IFunction f) {
        return Math.pow(abs(0 - f.count(fenotype[0], fenotype[1])), -1);
    }

}
