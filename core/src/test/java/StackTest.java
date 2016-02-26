import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.EmptyStackException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Abstract testing class for {@link Stack} implementations, containing all black-box
 * test cases for {@link Stack} functionality.
 */
public abstract class StackTest {
    protected Stack<Integer> stack;

    /**
     * Returns a new empty {@link Stack}. Implementations should return corresponding {@code Stack}
     * implementations in this method.
     */
    public abstract <T> Stack<T> getStack();

    @Before
    public void setUp() {
        stack = getStack();
    }

    @Test(expected = EmptyStackException.class)
    public void testEmptyPop() {
        stack.pop();
    }

    @Test(expected = EmptyStackException.class)
    public void testEmptyPeek() {
        stack.peek();
    }

    @Test
    public void testIsEmpty() {
        assertThat(stack.isEmpty(), is(true));
        stack.push(1);
        assertThat(stack.isEmpty(), is(false));
        assertThat(stack.pop(), is(1));
        assertThat(stack.isEmpty(), is(true));
    }

    @Test(expected = EmptyStackException.class)
    public void testExhaustedPop() {
        stack.push(1);
        stack.pop();
        stack.pop(); // Should trigger EmptyStackException
    }

    @Test
    public void testPopOrder() {
        stack.push(2);
        stack.push(3);
        stack.push(1);
        assertThat(stack.pop(), is(1));
        assertThat(stack.pop(), is(3));
        stack.push(4);
        stack.push(6);
        stack.push(5);
        assertThat(stack.pop(), is(5));
        assertThat(stack.pop(), is(6));
        assertThat(stack.pop(), is(4));
        assertThat(stack.pop(), is(2));
    }

    @Test
    public void testPopOnHead() {
        stack.push(2);
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(6);
        stack.push(5);

        assertThat(stack.peek(), is(stack.pop()));
        assertThat(stack.peek(), is(stack.pop()));
        assertThat(stack.peek(), is(stack.pop()));
        assertThat(stack.peek(), is(stack.pop()));
        assertThat(stack.peek(), is(stack.pop()));
        assertThat(stack.peek(), is(stack.pop()));
        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void testEmptyIteratorHasNext() {
        Iterator<Integer> iter = stack.iterator();
        assertThat(iter.hasNext(), is(false));
    }

    @Test(expected = EmptyStackException.class)
    public void testEmptyIteratorNext() {
        Iterator<Integer> iter = stack.iterator();
        iter.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemove() {
        stack.push(1);
        stack.iterator().remove();
    }

    @Test
    public void testIteratorOrder() {
        stack.push(2);
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(6);
        stack.push(5);
        Iterator<Integer> iter = stack.iterator();

        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(5));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(6));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(4));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(3));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(2));
        assertThat(iter.hasNext(), is(false));
    }

    @Test
    public void testOnBigDataSet() {
        int NUM_ELEM = 1000000;

        for (int i = 0; i < NUM_ELEM; i++)
            stack.push(i);

        Iterator<Integer> iter = stack.iterator();
        for (int i = NUM_ELEM - 1; i >= 0; i--) {
            assertThat(iter.hasNext(), is(true));
            assertThat(iter.next(), is(i));
        }
        assertThat(iter.hasNext(), is(false));

        for (int i = NUM_ELEM - 1; i >= 0; i--) {
            assertThat(stack.isEmpty(), is(false));
            assertThat(stack.peek(), is(i));
            assertThat(stack.peek(), is(stack.pop()));
        }
        assertThat(stack.isEmpty(), is(true));
    }
    
}
