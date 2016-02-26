import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@code Queue}-like structure, implemented by using a linked list.
 */
public class LinkedQueue<E> implements Queue<E> {
    private Node first = null;

    private class Node{
        Node next;
        E item;
    }

    @Override
    public void enqueue(E item) {
        Node current = first;
        while (current!= null)
            current = current.next;
        current = new Node();
        current.item = item;
        current.next = null;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        Node current = new Node();
        current.item = first.item;
        first = first.next;
        return current.item;
    }

    @Override
    public E head() {
        if (isEmpty())
            throw new NoSuchElementException();
        return first.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E>{
        private Node first;
        public boolean hasNext() {
            return first != null;
        }
        public void remove () {
               /* not supported */
        }
        public E next(){
            Node current = new Node();
            current.item = first.item;
            first = first.next;
            return current.item;
        }
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

}