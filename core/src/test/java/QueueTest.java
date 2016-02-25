import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Abstract testing class for {@link Queue} implementations, containing all black-box
 * test cases for {@link Queue} functionality.
 */
public abstract class QueueTest {
    protected Queue<Integer> queue;

    /**
     * Returns a new empty {@link Queue}. Implementations should return corresponding {@code Queue}
     * implementations in this method.
     */
    public abstract <T> Queue<T> getQueue();

    @Before
    public void setUp() {
        queue = getQueue();
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyDequeue() {
        queue.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyHead() {
        queue.head();
    }

    @Test
    public void testIsEmpty() {
        assertThat(queue.isEmpty(), is(true));
        queue.enqueue(1);
        assertThat(queue.isEmpty(), is(false));
        assertThat(queue.dequeue(), is(1));
        assertThat(queue.isEmpty(), is(true));
    }

    @Test(expected = NoSuchElementException.class)
    public void testExhaustedDequeue() {
        queue.enqueue(1);
        queue.dequeue();
        queue.dequeue(); // Should trigger NoSuchElementException
    }

    @Test
    public void testDequeueOrder() {
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        assertThat(queue.dequeue(), is(2));
        assertThat(queue.dequeue(), is(3));
        queue.enqueue(4);
        queue.enqueue(6);
        queue.enqueue(5);
        assertThat(queue.dequeue(), is(1));
        assertThat(queue.dequeue(), is(4));
        assertThat(queue.dequeue(), is(6));
        assertThat(queue.dequeue(), is(5));
    }

    @Test
    public void testDequeueOnHead() {
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(4);
        queue.enqueue(6);
        queue.enqueue(5);

        assertThat(queue.head(), is(queue.dequeue()));
        assertThat(queue.head(), is(queue.dequeue()));
        assertThat(queue.head(), is(queue.dequeue()));
        assertThat(queue.head(), is(queue.dequeue()));
        assertThat(queue.head(), is(queue.dequeue()));
        assertThat(queue.head(), is(queue.dequeue()));
        assertThat(queue.isEmpty(), is(true));
    }

    @Test
    public void testEmptyIteratorHasNext() {
        Iterator<Integer> iter = queue.iterator();
        assertThat(iter.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyIteratorNext() {
        Iterator<Integer> iter = queue.iterator();
        iter.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemove() {
        queue.enqueue(1);
        queue.iterator().remove();
    }

    @Test
    public void testIteratorOrder() {
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(4);
        queue.enqueue(6);
        queue.enqueue(5);
        Iterator<Integer> iter = queue.iterator();

        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(2));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(3));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(4));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(6));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(5));
        assertThat(iter.hasNext(), is(false));
    }

    @Test
    public void testOnBigDataSet() {
        int NUM_ELEM = 1000000;

        for (int i = 0; i < NUM_ELEM; i++)
            queue.enqueue(i);

        Iterator<Integer> iter = queue.iterator();
        for (int i = 0; i < NUM_ELEM; i++) {
            assertThat(iter.hasNext(), is(true));
            assertThat(iter.next(), is(i));
        }
        assertThat(iter.hasNext(), is(false));

        for (int i = 0; i < NUM_ELEM; i++) {
            assertThat(queue.isEmpty(), is(false));
            assertThat(queue.head(), is(i));
            assertThat(queue.head(), is(queue.dequeue()));
        }
        assertThat(queue.isEmpty(), is(true));
    }

}
