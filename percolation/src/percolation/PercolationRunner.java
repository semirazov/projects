package percolation;

import java.util.concurrent.ThreadLocalRandom;

public class PercolationRunner {
    private Percolation percolation;

    public PercolationRunner(Percolation percolation) {
        this.percolation = percolation;
    }

    public void percolate() {
        while (!percolation.percolates()) {
            openRandomSite(percolation);
        }
    }

    public void openWithProbability(double p){
        int n = percolation.getN();
        double openSites = n * n * p;

        for (int i = 0; i < openSites; i++) {
            openRandomSite(percolation);
        }
    }

    public double percolatesRation() {
        int n = percolation.getN();
        Percolation percolation = new Percolation(n);

        while (!percolation.percolates()) {
            openRandomSite(percolation);
        }

        return (double) percolation.numberOfOpenSites() / (n * n);
    }

    private void openRandomSite(Percolation percolation) {
        int n = percolation.getN();
        int col, row;
        do {
            col = ThreadLocalRandom.current().nextInt(0, n);
            row = ThreadLocalRandom.current().nextInt(0, n);
        } while (percolation.isOpen(row, col));

        percolation.open(row, col);

    }
}
