package util;

public class ArrUtils {

    public static int[] generateArr(int size){
        int[] arr = new int[size];
        for(int i = 0; i < arr.length; i++){
            arr[i] = (Math.random() < 0.5) ? 0 : 1;
        }
        return arr;
    }

    public static int[] reverse(int[] arr){
        for(int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        return arr;
    }

}
