import java.util.Iterator;

/**
 * {@code Stack}-like structure, implemented by using a resizing array.
 */
public class ArrayStack<E> implements Stack<E> {
    private E[] s;
    private int n = 0;

    public ArrayStack(){
        s = new E[1];
    }

    @Override
    public void push(E item) {
        // TODO to be implemented
        s[n++] = item;
        if (n == s.length)
            resize(s, n*2);
    }

    @Override
    public E pop() {
        // TODO to be implemented
        E item = s[--n];
        s[n] = null;
        if (n <= s.length / 4.0 && n >0)
            resize(s, s.length / 2);
        return null;
    }

    @Override
    public E peek() {
        // TODO to be implemented
        return s[n-1];
    }

    @Override
    public Iterator<E> iterator() {
        // TODO to be implemented
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<E>{
        private int current = n-1;
        public boolean hasNext() {
            return current != 0;
        }
        public void remove () {
               /* not supported */
        }
        public E next(){
            E item = s[current--];
            return item;
        }
    }

    @Override
    public boolean isEmpty() {
        // TODO to be implemented
        return n == 0;
    }

    private E[] resize(E[] s, int n){
        E[] copy = new E[n];
        for (int i = 0; i < s.length; i++ )
            copy[i] = s[i];
        return copy;
    }
}
