package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>{
    private static int RESIZE_FACTOR = 2;
    private static double USAGE_THRE = 0.25;
    private int size;
    private T items[];
    private int nextFirst, nextLast;
    public ArrayDeque() {
        items = (T []) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }
    // Adds an item to the first of the queue
    public void addFirst(T item) {
        checkResize(0);
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }
    // Adds an item to the last of the queue
    public void addLast(T item) {
        checkResize(0);
        items[nextLast] = item;
        nextLast = (nextLast + 1 + items.length) % items.length;
        size += 1;
    }
    // Resizes the memory space
    private void resize(int capacity) {
        T[] nextItems = (T[]) new Object[capacity];
        int ite = nextFirst;
        for (int i = 0; i < size; i++) {
            ite = (ite + 1 + items.length) % items.length;
            nextItems[i] = items[ite];
        }
        nextFirst = nextItems.length - 1;
        nextLast = size;
        items = nextItems;
    }
    // Returns whether the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }
    // Returns the size of the queue
    public int size() {
        return size;
    }
    // Prints the items in the deque from first to last
    public void printDeque() {
        int ite = (nextFirst + 1 + items.length) % items.length;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(items[ite] + " ");
            ite = (ite + 1 + items.length) % items.length;
        }
        System.out.println(items[ite]);
    }
    // Check and execute resize()
    private void checkResize(int type) {
        if (type == 0 && size == items.length) {
            resize(RESIZE_FACTOR * size);
        }
        if (type == 1 && size >= 16 && (int)(USAGE_THRE * items.length) >= size) {
            resize(items.length / RESIZE_FACTOR);
        }
    }
    // Removes the first item in the queue
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        checkResize(1);
        int first = (nextFirst + 1 + items.length) % items.length;
        T item = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;
        return item;
    }
    // Removes the last item in the queue
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        checkResize(1);
        int last = (nextLast - 1 + items.length) % items.length;
        T item = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;
        return item;
    }
    // Gets the ith item
    public T get(int index) {
        if (index > size) {
            return null;
        }
        int first = (nextFirst + 1 + items.length) % items.length;
        return items[(first + index) % items.length];
    }
    // Returns an Iterator
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(this);
    }

    private class ArrayIterator<T> implements Iterator<T> {
        private int remainingSize;
        private ArrayDeque<T> list;
        private int first;
        public ArrayIterator(ArrayDeque<T> list) {
            this.remainingSize = list.size;
            this.list = list;
            first = list.nextFirst;
            first = (first + 1) % list.items.length;
        }
        @Override
        public boolean hasNext() {
            return remainingSize > 0;
        }
        @Override
        public T next() {
            remainingSize -= 1;
            T item = list.items[first];
            first = (first + 1) % list.items.length;
            return item;
        }

    }
    // Check if the two lists ==
    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque && ((ArrayDeque<T>) o).size() == this.size))
            return false;
        Iterator<T> oIte = ((ArrayDeque<T>) o).iterator();
        Iterator<T> tIte = this.iterator();
        for (T a, b; oIte.hasNext() && tIte.hasNext();) {
            a = oIte.next();
            b = tIte.next();
            if (a != b) {
                return false;
            }
        }
        return true;
    }
}
