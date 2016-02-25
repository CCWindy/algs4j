import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    /**
     * Class for each node of the linked list
     */
    private static class Node <Item> {
        public Item item;
        public Node<Item> next;
        public Node<Item> last;
    }

    private Node<Item> head = null;
    private Node<Item> tail = null;
    private int size;

    /**
     * Constructs an empty deque
     */
    public Deque() {
    }

    /**
     * Returns if the deque is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * Adds the given item to the front of the deque
     *
     * @param item the item to be added
     * @throws NullPointerException if the given item is {@code null}
     */
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException("The item cannot be null.");

        Node<Item> newHead = new Node<Item>();
        newHead.item = item;
        newHead.next = head;
        if (head == null)
            tail = newHead;
        head = newHead;
        size++;
    }

    /**
     * Adds the given item to the end of the deque.
     *
     * @param item the item to be added
     * @throws NullPointerException if the given item is {@code null}
     */
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("The item cannot be null.");

        Node<Item> newTail = new Node<Item>();
        newTail.item = item;
        newTail.next = null;
        if (tail != null)
            tail.next = newTail;
        else
            head = newTail;
        tail = newTail;
        size++;
    }

    /**
     * Removes and returns the item from the front of the deque
     *
     * @return the element that was just removed from the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item result = head.item;
        head = head.next;
        if (head != null)
            head.last = null;
        else
            tail = null;
        size--;
        return result;
    }

    /**
     * Removes and returns the item from the end of the deque
     *
     * @return the element that was just removed from the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item result = tail.item;
        tail = tail.last;
        if (tail != null)
            tail.next = null;
        else
            head = null;
        size--;
        return result;
    }

    /**
     * Returns an {@link Iterator} that can be used to traverse each item of the deque.
     */
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> cur = head;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public Item next() {
                if (cur == null)
                    throw new NoSuchElementException();

                Item result = cur.item;
                cur = cur.next;
                return result;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
