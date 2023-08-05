package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        BuggyAList<Integer> bAList = new BuggyAList<>();
        AListNoResizing<Integer> AList = new AListNoResizing<>();
        for (int i = 0; i < 4; i++) {
            bAList.addLast(i);
            AList.addLast(i);
        }
        for (int i = 0; i < 4; i++) {
            assertEquals(AList.get(i), bAList.get(i));
        }
    }
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> list = new AListNoResizing<>();
        int N = 500;
        for (int i = 0; i < N; i++) {
            int id = StdRandom.uniform(0, 4);
            if (id == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                list.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (id == 1) {
                // size
                int size = list.size();
                System.out.println("size: " + size);
            } else if (id == 2) {
                // getLast
                if (list.size() != 0) {
                    System.out.println("getLast()=" + list.getLast());
                } else
                    System.out.println("getLast() failed");
            } else if (id == 3) {
                // removeLast
                if (list.size() != 0) {
                    System.out.println("removeLast()=" + list.removeLast());
                } else
                    System.out.println("removeLast() failed");
            }
        }
    }
    @Test
    public void testBuggyList() {
        BuggyAList<Integer> bAList = new BuggyAList<>();
        AListNoResizing<Integer> AList = new AListNoResizing<>();
        int N = 5000;
        for (int i = 0; i < N; i++) {
            int id = StdRandom.uniform(0, 4);
            if (id == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                AList.addLast(randVal);
                bAList.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (id == 1) {
                // size
                System.out.println("size1: " + AList.size() + ", size2:" + bAList.size());
                assertEquals(AList.size(), bAList.size());
            } else if (id == 2) {
                // getLast
                if (AList.size() != 0 && bAList.size() != 0) {
                    Integer ret1 = AList.getLast();
                    Integer ret2 = bAList.getLast();
                    System.out.println("getLast()=" + ret1 + ", " + ret2);
                    assertEquals(ret1, ret2);
                } else
                    System.out.println("getLast() failed");
            } else if (id == 3) {
                // removeLast
                if (AList.size() != 0 && bAList.size() != 0) {
                    Integer ret1 = AList.removeLast();
                    Integer ret2 = bAList.removeLast();
                    System.out.println("removeLast()=" + ret1 + ", " + ret2);
                    assertEquals(ret1, ret2);
                } else
                    System.out.println("removeLast() failed");
            }
        }
    }
}
