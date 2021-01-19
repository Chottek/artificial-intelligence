public class Node {

    private Node previous;
    private Node next;
    private final String name;

    private double distanceToNext;

    public Node(Node previous, Node next, String name) {
        this.name = name;
        this.next = next;
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Node {" +
                "name='" + name + '\'' +
                ", previous=" + previous.getName() +
                ", next=" + next.getName() +
                ", distance to next=" + distanceToNext +
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
}
