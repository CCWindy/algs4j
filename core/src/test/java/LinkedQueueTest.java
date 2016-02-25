/**
 * Unit test cases for {@link LinkedQueue}
 */
public final class LinkedQueueTest extends QueueTest {
    @Override
    public <T> Queue<T> getQueue() {
        return new LinkedQueue<>();
    }
}
