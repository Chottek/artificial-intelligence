import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.IntStream;

public class Graph {

    private static final Logger LOG = LoggerFactory.getLogger(Graph.class);

    private final java.util.List<Node> nodeList;

    public Graph(int nodeCount){
        nodeList = new java.util.LinkedList<>();
        initNodes(nodeCount);
    }

    private void initNodes(int nodeCount){
        IntStream.range(0, nodeCount).mapToObj(i -> new Node(null, String.valueOf(i))).forEach(nodeList::add);  //Initializing Nodes
        IntStream.range(0, nodeCount - 1).forEach(i -> nodeList.get(i).setNext(nodeList.get(i + 1)));
        nodeList.get(nodeList.size() - 1).setNext(nodeList.get(0));

        for(Node n : nodeList){
            LOG.info(n.toString());
        }

        LOG.info("Inited {} Nodes", nodeList.size());
    }

    @Override
    public String toString(){
        java.lang.StringBuilder sb = new java.lang.StringBuilder();
        sb.append("digraph G {").append("\n").append("rankdir=LR;").append("\n");
        for(Node n : nodeList){
            sb.append(n.getName()).append("->").append(n.getNext().getName()).append("\n");
        }
        sb.append("}");

        copyToClipBoard(sb.toString());
        LOG.info("Coppied To ClipBoard!");
        return sb.toString();
    }

    private void copyToClipBoard(String value){
        java.awt.datatransfer.StringSelection selection = new java.awt.datatransfer.StringSelection(value);
        java.awt.datatransfer.Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
