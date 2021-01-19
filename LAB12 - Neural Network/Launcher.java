package pl.fox.crazyneurons;

/*Wypisywanie informacji dla nas
Dodaj funkcję lub metodę pozwalającą na wypisanie parametrów neuronu.
Implementacja mini sieci neuronowej
Zaimplementuj sieć neuronową składającą się z 3 neuronów. Niech będą one połączone w następujący sposób:

 N1 -
       \
        -- N3 --
       /
 N2 -


Wypisanie stanu
Dodaj możliwość wyświetlenia stanu tej sieci neuronowej, to znaczy:

Wejścia
	Wagi wejść
	Wartość sumy ważonej wejść
	Stan neuronu (wzbudzony lub nie, ewentualnie wartość po obliczeniu funkcji aktywacji)
	Wyświetlenie listy połączeń między neuronami (metoda dowolna)


Zadanie na dodatkowy punkt
Wyeksportuj sieć neuronową do formatu graficznego lub grafu. Może być też format graphviz i przykład wizualizacji.*/


public class Launcher {

    public static void main(String... args) {
        new Controller().run();
    }

}
