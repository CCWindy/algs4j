import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@code Queue}-like structure, implemented by using a resizing array.
 */
public class ArrayQueue<E> implements Queue<E> {
    private E[] s;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(){
        s = (E[]) new Object[1];
    }

    @Override
    public void enqueue(E item) {
        if ((tail + 1) % s.length == head)
            resize(s, s.length * 2);
        s[tail] = item;
        tail = (tail + 1) % s.length;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        E item = s[head];
        head = (head +1) % s.length;
        int total;
        if (tail >= head)
            total = tail - head;
        else
            total = tail + s.length - head;
        if (total < s.length / 4.0 && total > 0)
            resize(s, s.length / 2);
        return item;
    }

    @Override
    public E head() {
        if (isEmpty())
            throw new NoSuchElementException();
        return s[head];
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<E>{
        private int current = head;
        public boolean hasNext() {
            return current != tail;
        }
        public void remove () {
               /* not supported */
        }
        public E next(){
            E item = s[current];
            current = (current + 1) % s.length;
            return item;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == tail;

    }

    private void resize(E[] s, int n){
        E[] copy = (E[]) new Object[n];
        int t = head;
        head = 0;
        tail = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[t++ % s.length] == null)
                break;
            copy[i] = s[t++ % s.length];
            tail++;
        }
        s = copy;
    }
}
