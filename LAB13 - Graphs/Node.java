

public class Node {

    private final String name;

    private Node previous;
    private Node next;
    private double distanceToNext;

    private java.util.List<Node> connections; //Double as distance, Node as Node :)

    public Node(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return Graph.isSimple ?
                "Node {" +
                "name='" + name + '\'' +
                ", previous=" + previous.getName() +
                ", next=" + next.getName() +
                ", distance to next=" + distanceToNext +
                '}' :
                "Node {" +
                 "name='" + name + '\'' +
                 ", connections=" + getConnNames() +
                 '}';
    }

    public Node getPrevious() {
        return previous;
    }

    public Node getNext() {
        return next;
    }

    public String getName() {
        return name;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public double getDistanceToNext() {
        return distanceToNext;
    }

    public void setDistanceToNext(double distanceToNext) {
        this.distanceToNext = distanceToNext;
    }

    //EXPERIMENTAL
    public java.util.List<Node> getConnections() {
        return connections;
    }

    public void setConnections(java.util.List<Node> connections) {
        this.connections = connections;
    }

    public String getConnNames(){
        StringBuilder sb = new StringBuilder();
        for(Node n : connections){
            sb.append(" (").append(n.getName()).append(" )");
        }
        sb.append("\n");

        return sb.toString();
    }
}
