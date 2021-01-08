package pl.fox.neuralsnake.sqola;

public class LifeBringer {

    public static void main(String[] args) {
        System.out.println(new Neuron(new int[]{0, 1, 2}, 4).calculateOutput(new int[]{2, 6, 2}));
    }
}
