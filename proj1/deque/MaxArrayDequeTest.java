package deque;

import org.junit.Test;

import java.util.Comparator;

public class MaxArrayDequeTest {
    private class comparator1<T> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.hashCode() - o2.hashCode();
        }
        // Never mind this method
        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
    @Test
    public void comparatorTest() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new comparator1<>());
        mad.addLast("asd");
        mad.addLast("eeee");
        for (int i = 0; i < 2; i++) {
            System.out.println(mad.get(i).hashCode());
        }
        System.out.println(mad.max());
    }
}
