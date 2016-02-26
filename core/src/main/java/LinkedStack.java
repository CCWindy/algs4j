import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * {@code Stack}-like structure, implemented by using a linked list.
 */
public class LinkedStack<E> implements Stack<E> {
    private Node first = null;
    private class Node{
        Node next;
        E item;
    }
    @Override
    public void push(E item) {
        Node current = new Node();
        current.next = first;
        current.item = item;
        first = current;
    }

    @Override
    public E pop() {
        if (isEmpty())
            throw new EmptyStackException();
        Node current = new Node();
        current.item = first.item;
        first = first.next;
        return current.item;
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new EmptyStackException();
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