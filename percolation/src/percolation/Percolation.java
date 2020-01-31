package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;
import java.util.List;

public class Percolation {
    /**
     * Grid size nxn
     */
    private int n;
    private int[][] map;
    private WeightedQuickUnionUF uf;
    private int ufTopId;
    private int ufBottomId;

    public Percolation(int n) {
        this.n = n;
        this.map = new int[n][n];
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.ufTopId = n * n;
        this.ufBottomId = n * n + 1;
    }

    public boolean isOpen(int row, int col) {
        return map[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        return map[row][col] == 0;
    }

    public boolean isPercolated(int row, int col) {
        int p = getCellId(row, col);

        return uf.connected(p, ufTopId);
    }

    public void open(int row, int col) {
        map[row][col] = 1;
        unionWithNeighbor(row, col);
    }

    public boolean percolates() {
        unionLastRow();
        return uf.connected(this.ufTopId, this.ufBottomId);
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (isOpen(row, col)) {
                    count++;
                }
            }
        }
        return count;
    }

    private void unionLastRow() {
        int row = n - 1;
        for (int col = 0; col < n; col++) {
            if (isOpen(row, col)) {
                unionForLastRow(row, col);
            }
        }
    }

    private void unionWithNeighbor(int row, int col) {
        int p = getCellId(row, col);

        if (isFirstRow(row)) {
            unionIfNotConnected(p, ufTopId);
        }
        if (isLastRow(row)) {
            unionForLastRow(row, col);
        }
        if (isRightOpen(row, col)) {
            unionIfNotConnected(p, getRightId(row, col));
        }
        if (isLeftOpen(row, col)) {
            unionIfNotConnected(p, getLeftId(row, col));
        }
        if (isTopOpen(row, col)) {
            unionIfNotConnected(p, getTopId(row, col));
        }
        if (isBottomOpen(row, col)) {
            unionIfNotConnected(p, getBottomId(row, col));
        }
    }

    private void unionIfNotConnected(int p, int q) {
        if (!uf.connected(p, q)) {
            uf.union(p, q);
        }
    }

    private boolean isFirstRow(int row) {
        return row == 0;
    }

    private boolean isLastRow(int row) {
        return row == n - 1;
    }

    private boolean isRightOpen(int row, int col) {
        return col < n - 1 && isOpen(row, col + 1);
    }

    private int getRightId(int row, int col) {
        return getCellId(row, col + 1);
    }

    private boolean isLeftOpen(int row, int col) {
        return col > 0 && isOpen(row, col - 1);
    }

    private int getLeftId(int row, int col) {
        return getCellId(row, col - 1);
    }

    private boolean isTopOpen(int row, int col) {
        return row > 0 && isOpen(row - 1, col);
    }

    private int getTopId(int row, int col) {
        return getCellId(row - 1, col);
    }

    private boolean isBottomOpen(int row, int col) {
        return row < n - 1 && isOpen(row + 1, col);
    }

    private int getBottomId(int row, int col) {
        return getCellId(row + 1, col);
    }

    private void unionForLastRow(int row, int col) {
        int p = getCellId(row, col);
        if (uf.connected(p, this.ufTopId)) {
            unionIfNotConnected(p, this.ufBottomId);
        }
    }

    private int getCellId(int row, int col) {
        return row * n + col;
    }

    public int getN() {
        return n;
    }
}
