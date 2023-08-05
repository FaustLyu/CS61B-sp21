package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    // ops : calls to addLast()
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        int MAX_SIZE = 128000;
//        MAX_SIZE = 128000 * 1024;
        AList<Integer> L = new AList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> ops = new AList<>();
        // Construct the Ns List
        int tmp = 1000;
        while (tmp <= MAX_SIZE) {
            Ns.addLast(tmp);
            tmp *= 2;
        }
        int turn = 0;
        int op = 0;
        // Timer
        Stopwatch sw = new Stopwatch();
        for (int i = 1; i <= MAX_SIZE; i++) {
            // Add operation
            L.addLast(i);
            op += 1;
            // Check if we need save
            if (i == Ns.get(turn)) {
                ops.addLast(op);
                times.addLast(sw.elapsedTime());
                turn += 1;
            }
        }
        printTimingTable(Ns, times, ops);
    }
}
