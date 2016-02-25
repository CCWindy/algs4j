import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * <p>
 * A percolation system can be represented as an N-by-N grid of <i>sites</i>.
 * </p>
 * <p>
 * Each site is either <i>open</i> or <i>blocked</i>. <i>A full site</i> is an
 * open site that can be connected to an open site in the top row via a chain
 * of neighboring (left, right, up, down) open sites.
 * </p>
 * <p>
 * We say the system <i>percolates</i> if there is a full site in the bottom row.
 * In other words, a system percolates if we fill all open sites connected to the
 * top row and that process fills some open site on the bottom row.
 * </p>
 *
 * @author Mr.Dai
 */
public class Percolation {
    private int N;
    private WeightedQuickUnionUF weightedUF;
    private boolean[] sites;

    /**
     * Returns a {@code Percolation} with an N-by-N grid of blocked sites.
     *
     * @param N the side length of the grid
     * @throws IllegalArgumentException if {@code N <= 0}.
     */
    public Percolation(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("The given `N` should be greater than 0.");

        this.N = N;
        sites = new boolean[N * N];

        weightedUF = new WeightedQuickUnionUF(N * N + 2);
    }

    /**
     * Opens site (row i, column j) if it is not open already.
     *
     * @param i the base-1 row index of the site to be opened
     * @param j the base-1 column index of the site to be opened
     * @throws IndexOutOfBoundsException if the given index is outside the prescribed range
     */
    public void open(int i, int j) {
        checkIndex(i, j);

        sites[normalize(i, j)] = true;
        int idx = normalize(i, j) + 1;

        if (i == 1) {
            // Open site on the first row
            weightedUF.union(idx, 0);
        }

        if (i == N) {
            // Open site on the last row
            weightedUF.union(idx, N * N + 1);
        }

        // Union the neighboring open sites
        if (i > 1 && isOpen(i - 1, j)) // Up
            weightedUF.union(idx, idx - N);
        if (i < N && isOpen(i + 1, j)) // Down
            weightedUF.union(idx, idx + N);
        if (j > 1 && isOpen(i, j - 1)) // Left
            weightedUF.union(idx, idx - 1);
        if (j < N && isOpen(i, j + 1)) // Right
            weightedUF.union(idx, idx + 1);
    }

    /**
     * Returns if site (row i, column j) is open.
     *
     * @param i the base-1 row index of the site to be examined
     * @param j the base-1 column index of the site to be examined
     * @return {@code true} if site (i, j) is open; {@code false}, otherwise.
     * @throws IndexOutOfBoundsException if the given index is outside the prescribed range
     */
    public boolean isOpen(int i, int j) {
        checkIndex(i, j);

        return sites[normalize(i, j)];
    }

    /**
     * Returns if site (row i, column j) is full.
     *
     * @param i the base-1 row index of the site to be examined
     * @param j the base-1 column index of the site to be examined
     * @return {@code true} if site (i, j) is full; {@code false}, otherwise.
     * @throws IndexOutOfBoundsException if the given index is outside the prescribed range
     */
    public boolean isFull(int i, int j) {
        checkIndex(i, j);

        return weightedUF.connected(0, normalize(i, j) + 1);
    }

    /**
     * Checks if the given index is valid. Throws {@link IndexOutOfBoundsException}
     * if the given index is outside the prescribed range.
     *
     * @param i the base-1 row index to be examined
     * @param j the base-1 column index to be examined
     * @throws IndexOutOfBoundsException if the given index is outside the prescribed range
     */
    private void checkIndex(int i, int j) {
        if (i < 1 || i > N)
            throw new IndexOutOfBoundsException(String.format(
                "The given row index `i = %d` is outside the valid range `[1, %d]`", i, N));
        if (j < 1 || j > N)
            throw new IndexOutOfBoundsException(String.format(
                "The given row index `j = %d` is outside the valid range `[1, %d]`", j, N));
    }

    /**
     * Normalizes the given 2-d base-1 index to an 1-d base-0 index.
     *
     * @param i the base-1 row index to be converted
     * @param j the base-1 column index to be converted
     * @return the normalized 1-d base-0 index
     */
    private int normalize(int i, int j) {
        return (i - 1) * N + j - 1;
    }

    /**
     * Returns if the system percolates.
     *
     * @return {@code true} if the system percolates; {@code false}, otherwise.
     */
    public boolean percolates() {
        return weightedUF.connected(0, N * N + 1);
    }

}
