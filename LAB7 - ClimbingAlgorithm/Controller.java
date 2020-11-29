import functions.Beale;
import functions.Booth;
import functions.HimmelBlau;
import functions.IFunction;

public class Controller {

    private final IFunction beale = new Beale();
    private final IFunction booth = new Booth();
    private final IFunction himmelBlau = new HimmelBlau();

    private int number;

    public void execute(){

    }

    public void setNumber(int number) {
        this.number = number;
    }
}
