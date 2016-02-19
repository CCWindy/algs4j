import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * The {@code Stack} class represents a last-in-first-out
 * (LIFO) stack of objects.
 */
public interface Stack<E> extends Iterable<E> {

    /**
     * Pushes an item onto the top of this stack.
     * @param item the item to be pushed onto this stack.
     */
    public void push(E item);

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return The object at the top of this stack.
     * @throws EmptyStackException  if this stack is empty.
     */
    public E pop();

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return  the object at the top of this stack.
     * @throws  EmptyStackException  if this stack is empty.
     */
    public E peek();

    /**
     * Returns an {@code Iterator} which iterates all elements in LIFO order.
     *
     * @return an {@code Iterator} which iterates all elements in LIFO order.
     */
    public Iterator<E> iterator();

    /**
     * Returns if the stack is empty.
     * @return {@code true} if the stack is empty; {@code false} otherwise.
     */
    public boolean isEmpty();

}
