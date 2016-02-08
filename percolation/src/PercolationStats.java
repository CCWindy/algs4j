import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Class for performing series of percolation experiments
 */
public class PercolationStats {
    private int T;
    private double[] openNums;

    /**
     * Performs {@code T} independent experiments on an N-by-N grid
     *
     * @param N the side length of the grid
     * @param T the number of experiments to be performed
     * @throws IllegalArgumentException if either {@code N <= 0} or {@code T <= 0}.
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("`N` and `T` cannot be less than 1");
        openNums = new double[T];
        this.T = T;

        for (int t = 0; t < T; t++) {
            Percolation percolation = new Percolation(N);

            int count = 0;
            while (!percolation.percolates()) {
                while (true) {
                    int i = StdRandom.uniform(N) + 1;
                    int j = StdRandom.uniform(N) + 1;
                    if (!percolation.isOpen(i, j)) {
                        percolation.open(i, j);
                        break;
                    }
                }
                count++;
            }

            openNums[t] = 1.0 * count / N / N;
        }
    }

    /**
     * Returns the sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(openNums);
    }

    /**
     * Returns the sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(openNums);
    }

    /**
     * Returns the low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    /**
     * Returns the high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            StdOut.println("Usage: java PercolationStats <N> <T>");
            return;
        }

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        Stopwatch watch = new Stopwatch();
        PercolationStats percolationStats = new PercolationStats(N, T);

        StdOut.printf("%d experiments on %dx%d grid finished in %fs%n", T, N, N, watch.elapsedTime());
        StdOut.printf("mean                    = %.17f%n", percolationStats.mean());
        StdOut.printf("stddev                  = %.17f%n", percolationStats.stddev());
        StdOut.printf("95%% confidence interval = %.17f, %.17f%n",
                      percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}
