package percolation.drawer;

import percolation.Percolation;

import javax.swing.*;
import java.awt.*;

public class PercolationDrawer {

    private Percolation percolation;

    public PercolationDrawer(Percolation percolation) {
        this.percolation = percolation;

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new PercolationGrid());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class PercolationGrid extends JPanel {

        public PercolationGrid() {
            int index = 0;
            int n = percolation.getN();
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    Color color = getColor(row, col);
                    gbc.gridx = col;
                    gbc.gridy = row;
                    add(new Cell(color), gbc);
                    index++;
                }
                index++;
            }
        }

        private Color getColor(int row, int col) {
            if (percolation.isPercolated(row, col)) {
//                return new Color(51,107,135);
                return Color.WHITE;
            } else if (percolation.isFull(row, col)) {
                return new Color(42,49,50);
            } else {
                return Color.WHITE;
            }
        }

    }

    public class Cell extends JButton {

        public Cell(Color background) {

            setBackground(background);
            setBorder(BorderFactory.createLineBorder(new Color(42,49,50), 1));
            setOpaque(true);

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(35, 35);
        }

    }

}