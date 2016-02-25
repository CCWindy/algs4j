/**
 * Unit test cases for {@link ArrayStack}
 */
public final class ArrayStackTest extends StackTest {
    @Override
    public <T> Stack<T> getStack() {
        return new ArrayStack<>();
    }
}
