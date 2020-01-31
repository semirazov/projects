import percolation.Percolation;
import percolation.PercolationRunner;
import percolation.drawer.PercolationDrawer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Percolation percolation = new Percolation(10);
        PercolationRunner runner = new PercolationRunner(percolation);

        PercolationDrawer drawer = new PercolationDrawer(percolation);

    }
}
