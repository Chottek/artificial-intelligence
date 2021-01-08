import genetic.GeneticAlgorithm;

import java.util.Random;

/**
 * Zaimplementuj zadanie optymalizacyjne tak, aby można było je liczyć za pomocą algorytmu genetycznego.
 * Teraz bardziej szczegółowo:
 * Wybierz jedno z zadań optymalizacyjnych, ale tylko takie które ma wiele minimów lokalnych.
 * Na potrzeby ćwiczeń zmienne z dziedziny funkcji nazwę X oraz Y, ale oczywiście jeśli wybrana została przez Ciebie
 * funkcja która przyjmuje więcej argumentów, to oczywiście będzie tu X0, X1, .. Xn
 * <p>
 * Przygotuj funkcję która przekształci tablicę 128 liczb ze zbioru {0,1} na odpowiednio X oraz Y.
 * Na dodatkowy punkt - niech będzie to liczba w kodzie Greay-a (https://pl.wikipedia.org/wiki/Kod_Graya).
 * <p>
 * To będzie funkcja dekodująca genotyp do postaci fenotypu (czyli tak jak to na wykładzie było -
 * ciąg kodowy w konkretne rozwiązanie). Pamiętaj o ułamkach!
 * <p>
 * Napisz funkcję oceny (inaczej funkcję fitness), która będzie oceniała fenotyp.
 * W tym celu należy zrobić funkcję, która będzie na przykład odwracała funkcję celu
 * (funkcje testowe do optymalizacji są minimalizowane, a funkcja oceny jest maksymalizowana,
 * więc trzeba przekształcić jedną w drugą). Przy tym punkcie zachęcam do zadawania pytań.
 * Przetestuj ręcznie, czy faktycznie to działa.
 * Dodaj możliwość konwersji genotypu w fenotyp oraz fenotypu w genotyp.
 * (czyli tablica 128 liczb na X,Y oraz z X,Y na tablicę 128 liczb).
 * <p>
 * <p>
 * Przetestuj, czy algorytm losowego próbkowania da radę tu wygenerować jakieś sensowne rozwiązanie. - to na dodatkowe 0.5pkt
 */


public class Main {

    private static final int SIZE = 40;
    private static final int ACCURACY = 5;
    private static final int ITERATIONS = 100000;

    public static void main(String... args) {
        new Exec().fireUp(ITERATIONS, SIZE, ACCURACY);
    }

}
