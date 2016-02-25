/**
 * Unit test cases for {@link ArrayQueue}
 */
public final class ArrayQueueTest extends QueueTest {
    @Override
    public <T> Queue<T> getQueue() {
        return new ArrayQueue<>();
    }
}
