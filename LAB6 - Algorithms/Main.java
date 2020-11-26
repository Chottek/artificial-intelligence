package pl.fox.nailabs;

import pl.fox.nailabs.functions.Beale;
import pl.fox.nailabs.functions.Booth;
import pl.fox.nailabs.functions.HimmelBlau;


public class Main {

    public static void main(String... args) {
        new Thread(new Sampling(new Booth(),  -10, 10)).start();
        new Thread(new Sampling(new Beale(),  -4.5, -4.5)).start();
        new Thread(new Sampling(new HimmelBlau(), -6, 6)).start();
    }

}
