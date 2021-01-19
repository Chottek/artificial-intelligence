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
        IntStream.range(0, nodeCount).mapToObj(i -> new Node(null, null, String.valueOf(i))).forEach(nodeList::add);  //Initializing Nodes
        for(int i = 0; i < nodeCount - 1; i++){
            if(i == 0){
                nodeList.get(i).setPrevious(nodeList.get(nodeCount - 1));
            }else{
                nodeList.get(i).setPrevious(nodeList.get(i - 1));
            }
            nodeList.get(i).setNext(nodeList.get(i + 1));
        }
        LOG.info("Inited {} Nodes", nodeList.size());
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    @Override
    public String toString(){
        return "graph";
    }

    private void copyToClipBoard(String value){
        java.awt.datatransfer.StringSelection selection = new java.awt.datatransfer.StringSelection(value);
        java.awt.datatransfer.Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
