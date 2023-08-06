package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompareTest {
    @Test
    public void randomTest() {
        int times = 10000;
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < times; i++) {
            int operator = StdRandom.uniform(9);
            switch (operator) {
                case 0: // addFirst
                    lld.addFirst(i);
                    ad.addFirst(i);
                    System.out.println("addFirst()");
                    break;
                case 1: // addLast
                    lld.addLast(i);
                    ad.addLast(i);
                    System.out.println("addLast()");
                    break;
                case 3: // isEmpty
                    System.out.println("isEmpty()");
                    assertEquals(lld.isEmpty(), ad.isEmpty());
                    break;
                case 4: // size
                    System.out.println("size()");
                    assertEquals(lld.size(), ad.size());
                    break;
                case 5: // printDeque
                    System.out.println("printDeque() 1:");
                    lld.printDeque();
                    System.out.println("printDeque() 2:");
                    ad.printDeque();
                    break;
                case 6: // removeFirst
                    System.out.println("removeFirst()");
                    assertEquals(lld.removeFirst(), ad.removeFirst());
                    break;
                case 7: // removeLast
                    System.out.println("removeLast()");
                    assertEquals(lld.removeLast(), ad.removeLast());
                    break;
                case 8: // get
                    System.out.println("get(0)");
                    assertEquals(lld.get(0), ad.get(0));
                    break;
            }
        }
    }
}
