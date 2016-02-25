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
        // TODO to be implemented
        Node current = new Node();
        current.next = first;
        current.item = item;
        first = current;
    }

    @Override
    public E pop() {
        // TODO to be implemented
        item = first.item;
        first = first.next;
        return item;
    }

    @Override
    public E peek() {
        // TODO to be implemented
        return first.item;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO to be implemented
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E>{
        private Node first;
        public boolean hasNext() {
            return first != null;
        }
        public void remove () {
            throw UnsupportedOperationException;    /* not supported */
        }
        public E next(){
            if (!hasNext())
                throw NoSuchElementException;
            item = first.item;
            first = first.next;
            return item;
        }
    }

    @Override
    public boolean isEmpty() {
        // TODO to be implemented
        return first == null;
    }

    public static void main(String[] args){
        LinkedStack<Integer> test = new LinkedStack<Integer>();
        for (int i = 1;i <= 10;i++)
        test.push(i);
        if(test.hasNext())
            test.peek();
        while(!test.isEmpty())
            System.out.println(test.pop() + " ");
    }

}
