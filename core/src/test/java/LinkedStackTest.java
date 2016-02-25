/**
 * Unit test cases for {@link LinkedStack}
 */
public final class LinkedStackTest extends StackTest {
    @Override
    public <T> Stack<T> getStack() {
        return new LinkedStack<>();
    }
}
