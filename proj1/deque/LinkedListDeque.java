package deque;

import java.util.Deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T> {
    private static class DoubleNode<T> {
        private T item;
        private DoubleNode<T> prev;
        private DoubleNode<T> next;
        public DoubleNode(T item) {
            this.item = item;
            next = null;
            prev = null;
        }
        public DoubleNode(DoubleNode prev, T item, DoubleNode next) {
            this.item = item;
            this.prev = prev;
            this. next = next;
        }
    }
    DoubleNode<T> sentinel;
    private int size;
    public LinkedListDeque() {
        this.sentinel = new DoubleNode<>(null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        size = 0;
    }
    // Adds a new item to the first of the queue
    public void addFirst(T item) {
        DoubleNode<T> first = new DoubleNode<>(sentinel, item, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }
    // Adds a new item to the last of the queue
    public void addLast(T item) {
        DoubleNode<T> last = new DoubleNode<>(sentinel.prev, item, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
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
        DoubleNode<T> p = sentinel;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println(p.next.item);
    }
    // Removes the first item in the queue
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        DoubleNode<T> first = sentinel.next;
        first.next.prev = sentinel;
        sentinel.next = first.next;
        size -= 1;
        return first.item;
    }
    // Removes the last item in the queue
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        DoubleNode<T> last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        size -= 1;
        return last.item;
    }
    // Gets the ith item
    public T get(int index) {
        if (index > size) {
            return null;
        }
        DoubleNode<T> p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }
    // Gets the ith item by recursive method
    public T getRecursive(int index) {
        if (index > size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel);
    }
    private T getRecursiveHelper(int index, DoubleNode<T> p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }
    // Returns an Iterator
    public Iterator<T> iterator() {
        return new ListIterator<T>(this);
    }

    private class ListIterator<T> implements Iterator<T> {
        private int remainingSize;
        private DoubleNode<T> p;
        public ListIterator(LinkedListDeque<T> list) {
            this.remainingSize = list.size;
            p = list.sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return remainingSize > 0;
        }
        @Override
        public T next() {
            remainingSize -= 1;
            T item =  p.item;
            p = p.next;
            return item;
        }

    }
    // Check if the two lists ==
    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque && ((LinkedListDeque<T>) o).size() == this.size))
            return false;
        Iterator<T> oIte = ((LinkedListDeque<T>) o).iterator();
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
