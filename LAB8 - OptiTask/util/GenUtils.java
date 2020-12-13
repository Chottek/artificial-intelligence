package util;

import function.IFunction;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static util.ArrUtils.*;

public class GenUtils {

    public static int[] toGray(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] = (arr[i] != arr[(i - 1)]) ? 1 : 0;
        }
        return arr;
    }

    public static int[] toGenotype(int size, double x, double y, int accuracy) {
        int[] arr = new int[size];
        int[] tempX = toBinary(size, x, accuracy);
        int[] tempY = toBinary(size, y, accuracy);

        for (int i = 0; i < arr.length / 2; i++) {
            arr[i] = tempX[i];
            arr[i + arr.length / 2] = tempY[i];
        }

        return arr;
    }

    public static double[] toFenotype(int[] arr, int size, int accuracy) {
        int[] tempX = new int[size / 2];
        int[] tempY = new int[size / 2];

        for (int i = 0; i < size / 2; i++) {
            tempX[i] = arr[i];
            tempY[i] = arr[i + size / 2];
        }

        return new double[]{toDecimal(tempX) / pow(10, accuracy), toDecimal(tempY) / pow(10, accuracy)};
    }

    public static int toDecimal(int[] arr) {
        int dec = 0;
        int[] rev = reverse(arr);
        for (int i = 0; i < arr.length / 2; i++) {
            dec += rev[i] * pow(2, i);
        }
        return dec;
    }

    public static int[] toBinary(int size, double x, int accuracy) {
        int result = (int) (x * pow(10, accuracy));
        int[] arr = new int[size / 2];
        for (int i = 0; i < arr.length / 2; i++) {
            arr[i] = result % 2;
            result /= 2;
        }
        return reverse(arr);
    }

    public static double getFitness(double[] fenotype, IFunction f) {
        return pow(abs(0 - f.count(fenotype[0], fenotype[1])), -1);
    }

}
