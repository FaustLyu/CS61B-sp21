package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }
    // Returns the max element in the queue
    public T max() {
        return max(comparator);
    }
    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T tmpMax = this.get(0);
        for (int i = 0; i < size(); i++) {
            T temp = this.get(i);
            if (c.compare(tmpMax, temp) < 0) {
                tmpMax = temp;
            }
        }
        return tmpMax;
    }
}
