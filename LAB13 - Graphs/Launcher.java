public class Launcher {

    private static final int NODE_COUNT = 10;
    private static final double[] DISTANCES = {5, 7, 3, 2, 10, 9, 8, 13, 8, 5};

    public static void main(String[] args) {
        Graph g = new Graph(NODE_COUNT, null);
        g.toString();
    }

}
