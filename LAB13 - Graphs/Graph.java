import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Graph {

    private static final Logger LOG = LoggerFactory.getLogger(Graph.class);

    protected static final double MIN_DISTANCE = 0.5D;
    protected static final double MAX_DISTANCE = 15.0D;
    protected static final int MAX_CONNECTIONS = 3;
    protected static final boolean isSimple = false;

    private final java.util.List<Node> nodeList;

    public Graph(int nodeCount, double[] distances) {
        nodeList = new java.util.LinkedList<>();
        initNodes(nodeCount, distances);
    }

    private void initNodes(int nodeCount, double[] distances) {
        java.util.stream.IntStream.range(0, nodeCount)
                .mapToObj(i -> new Node(String.valueOf(i))).forEach(nodeList::add);  //Initializing Nodes

        if (isSimple) {
            if (distances == null || distances.length < nodeCount) {
                LOG.error("Distances input is either null or not long enough to proceed, generating random array!");
                distances = initDistances(nodeCount);
            }

            java.util.stream.IntStream.range(0, nodeCount - 1)
                    .forEach(i -> nodeList.get(i).setNext(nodeList.get(i + 1)));   //Setting next node

            java.util.stream.IntStream.iterate(nodeCount - 1, i -> i > 0, i -> i - 1)
                    .forEach(i -> nodeList.get(i).setPrevious(nodeList.get(i - 1))); //setting previous node

            nodeList.get(nodeList.size() - 1).setNext(nodeList.get(0));
            nodeList.get(0).setPrevious(nodeList.get(nodeList.size() - 1));

            for (int i = 0; i < nodeList.size(); i++) {
                nodeList.get(i).setDistanceToNext(distances[i]);
            }
        } else {
            java.util.Random rand = new java.util.Random();
            java.util.List<Node> connList;

            for(Node n : nodeList){
                connList = new java.util.ArrayList<>();
                int connections = rand.nextInt(MAX_CONNECTIONS) + 1;

                for(int i = 0; i < connections; i++){
                    Node node = nodeList.get(rand.nextInt(nodeList.size() - 1));
                    while(node.getName().equals(n.getName()) || connList.contains(node)){
                        node = nodeList.get(rand.nextInt(nodeList.size() - 1));
                    }
                    connList.add(node);
                }
                n.setConnections(connList);
            }
        }

        LOG.info("Inited {} Nodes", nodeList.size());
    }

    private double[] initDistances(int nodeCount) {
        return java.util.stream.IntStream.range(0, nodeCount)
                .mapToDouble(i -> randomizeDouble()).toArray();
    }

    private double randomizeDouble(){
        java.util.Random rand = new java.util.Random();
        return round((rand.nextDouble() * (MAX_DISTANCE - MIN_DISTANCE) + MIN_DISTANCE), 1);

    }

    private double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    @Override
    public String toString() {
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        sb.append("digraph G {").append("\n").append("rankdir=LR;").append("\n");
        if (isSimple) {
            for (Node n : nodeList) {
                sb.append(n.getName()).append(" -> ").append(n.getNext().getName()).append("\t[ label = \"")
                        .append(n.getDistanceToNext()).append("\" ]").append("\n");
            }
        }else{
            for(Node n: nodeList){
                for(Node s: n.getConnections()){
                    sb.append(n.getName()).append(" -> ").append(s.getName()).append("\n");
                }
            }
        }
        sb.append("}");

        copyToClipboard(sb.toString());
        LOG.info("Copied To Clipboard!");
        LOG.info("Visit http://www.webgraphviz.com/ to see graphical sight of this graph");
        return sb.toString();
    }

    private void copyToClipboard(String value) {
        java.awt.datatransfer.StringSelection selection = new java.awt.datatransfer.StringSelection(value);
        java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
    }
}
