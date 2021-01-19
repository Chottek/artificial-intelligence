public class Node {

    private Node next;

    private final String name;

    public Node( Node next, String name) {
        this.name = name;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node {" +
                "name='" + name + '\'' +
                ", next=" + next.getName() +
                '}';
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

}
