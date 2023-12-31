package deque;

import java.util.Iterator;

public interface Deque<T> {
    default boolean isEmpty() {
        return size() == 0;
    }
    int size();
    void addFirst(T item);
    void addLast(T item);
    T removeFirst();
    T removeLast();
    T get(int index);
    void printDeque();
    Iterator<T> iterator();
    boolean equals(Object o);
}
