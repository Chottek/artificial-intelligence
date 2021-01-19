public class Node {

    private Node previous;
    private Node next;

    private final String name;

    public Node(Node previous, Node next, String name) {
        this.previous = previous;
        this.next = next;
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
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

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
