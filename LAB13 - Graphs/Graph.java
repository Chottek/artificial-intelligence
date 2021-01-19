import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.stream.IntStream;

public class Graph {

    private static final Logger LOG = LoggerFactory.getLogger(Graph.class);

    private static final double MIN_DISTANCE = 0.5D;
    private static final double MAX_DISTANCE = 15.0D;


    private final java.util.List<Node> nodeList;

    public Graph(int nodeCount, double[] distances){
        nodeList = new java.util.LinkedList<>();
        initNodes(nodeCount, distances);
    }

    private void initNodes(int nodeCount, double[] distances){
        if(distances == null || distances.length < nodeCount){
            LOG.error("Distances input is either null or not enough length to proceed, generating random array!");
            distances = initDistances(nodeCount);
        }

        java.util.stream.IntStream.range(0, nodeCount)
                .mapToObj(i -> new Node(null,null, String.valueOf(i))).forEach(nodeList::add);  //Initializing Nodes

        java.util.stream.IntStream.range(0, nodeCount - 1)
                .forEach(i -> nodeList.get(i).setNext(nodeList.get(i + 1)));   //Setting next node

        java.util.stream.IntStream.iterate(nodeCount - 1, i -> i > 0, i -> i - 1)
                .forEach(i -> nodeList.get(i).setPrevious(nodeList.get(i - 1))); //setting previous node

        nodeList.get(nodeList.size() - 1).setNext(nodeList.get(0));
        nodeList.get(0).setPrevious(nodeList.get(nodeList.size() - 1));

        for(int i = 0; i < nodeList.size(); i++){
            nodeList.get(i).setDistanceToNext(distances[i]);
        }

        LOG.info("Inited {} Nodes", nodeList.size());
    }

    private double[] initDistances(int nodeCount){
        Random rand = new Random();
        return IntStream.range(0, nodeCount).mapToDouble(i -> round((rand.nextDouble() * (MAX_DISTANCE - MIN_DISTANCE) + MIN_DISTANCE), 1)).toArray();
    }

    private double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    @Override
    public String toString(){
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        sb.append("digraph G {").append("\n").append("rankdir=LR;").append("\n");
        for(Node n : nodeList){
            sb.append(n.getName()).append("->").append(n.getNext().getName()).append("\t[ label = \"")
                    .append(n.getDistanceToNext()).append("\" ]").append("\n");
        }
        sb.append("}");

        copyToClipboard(sb.toString());
        LOG.info("Copied To Clipboard!");
        LOG.info("Visit http://www.webgraphviz.com/ to see graphical sight of this graph");
        return sb.toString();
    }

    private void copyToClipboard(String value){
        java.awt.datatransfer.StringSelection selection = new java.awt.datatransfer.StringSelection(value);
        java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
    }
}
