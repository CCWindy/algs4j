import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code Queue} class represents a first-in-first-out
 * (FIFO) stack of objects.
 */
public interface Queue<E> extends Iterable<E> {

    /**
     * Puts an item into this queue.
     * @param item the item to be put into this queue.
     */
    public void enqueue(E item);

    /**
     * Removes the object at the head of this queue and returns that
     * object as the value of this function.
     *
     * @return The object at the head of this queue.
     * @throws NoSuchElementException if this stack is empty.
     */
    public E dequeue();

    /**
     * Looks at the object at the head of this queue without removing it
     * from the queue.
     *
     * @return  the object at the head of this queue.
     * @throws  NoSuchElementException if this queue is empty.
     */
    public E head();

    /**
     * Returns an {@code Iterator} which iterates all elements in FIFO order.
     *
     * @return an {@code Iterator} which iterates all elements in FIFO order.
     */
    public Iterator<E> iterator();

    /**
     * Returns if the queue is empty.
     * @return {@code true} if the queue is empty; {@code false} otherwise.
     */
    public boolean isEmpty();

}
