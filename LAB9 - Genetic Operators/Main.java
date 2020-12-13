/**
 * Napisz operatory genetyczne.
 * Masz już przygotowane elementy zadania optymalizacyjnego do rozwiązania przez GA.Teraz czas na operatory genetyczne.Przygotuj i przetestuj:
 * -Krzyżowanie jednopunktowe
 * -Mutacja(wybierz)
 * -Selekcja turniejowa albo ruletkowa
 * Tak więc po tych zajęciach powinno być prawie wszystko – bez samej głównej pętli algorytmu.To zrobimy na następnym naszym spotkaniu
 */

public class Main {

    private static final int MAX_GENERATIONS = 100000;

    public static void main(String... args) {
        new Heaven().ruleTheWorld(MAX_GENERATIONS);
    }

}
