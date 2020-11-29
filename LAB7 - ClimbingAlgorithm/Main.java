import java.util.Scanner;

public class Main {

    private static final Scanner get = new Scanner(System.in);
    private static final Controller c = new Controller();

    public static void main(String[] args) {
        String s = "";
        while(!s.equals("exit")){
            s = get.next();

            if(isNumeric(s)){
                c.setNumber(Integer.parseInt(s));
                c.execute();
            }
        }

        System.out.println("Bye bye!");
    }

    private static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
