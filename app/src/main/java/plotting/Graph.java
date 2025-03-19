package plotting;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import java.awt.event.MouseEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;

public class Graph {
    private int width = 500;
    private int height = 500;

    public Graph(LinkedList<Point> coordinates, String functioString) {
        SwingUtilities.invokeLater(() -> createWindow("Plot of " + functioString, width, height, coordinates));
    }
    private void createWindow(String title, int width, int height, LinkedList<Point> coordinates) {

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        GraphPanel graphPanel = new GraphPanel(coordinates, width, height);
        graphPanel.setPreferredSize(new Dimension(width, height));

        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        frame.add(graphPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private class GraphPanel extends JPanel {

        private LinkedList<Point> coordinates;
        private double maxY;
        private double minY;
        private double maxX;
        private double minX;
        private double scaleY;
        private double scaleX;
        private int width, height;


        public GraphPanel(LinkedList<Point> coordinates, int width, int height) {
            this.coordinates = coordinates;
            this.width = width;
            this.height = height;

            this.minX = this.coordinates.peekLast().getX();
            this.maxX = this.coordinates.peekFirst().getX();

            this.minY = this.coordinates.peekLast().getY();
            this.maxY = this.coordinates.peekFirst().getY();

            for (Point p : coordinates) {
                double y = p.getY();
                minY = (y < minY) ? y : minY;
                maxY = (y > maxY) ? y : maxY;

                // double x = p.getX();
                // minX = (x < minX) ? x : minX;
                // maxX = (x > maxX) ? x : maxX;
            }
            // Adjust so that the highest and lowest points aren't on the edge of the window.
            // this.minY *= 1.05;
            // this.maxY *= 1.05;

            this.scaleY = (double) height / (maxY - minY);
            this.scaleX = (double) width / (maxX - minX);

            Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
            this.setBorder(blackBorder);

        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.black);

            Graphics2D g2 = (Graphics2D) g;

            drawXandYaxis(g);

            drawPoints(g2);

        }

        private void drawXandYaxis(Graphics g) {
            // Draw X-axis only if y=0 is within range
            g.setColor(Color.BLACK);
            if (minY <= 0 && maxY >= 0) {
                int xAxisY = (int) ((maxY - 0) * scaleY);
                g.drawLine(0, xAxisY, width, xAxisY);
            }

            // Draw Y-axis only if x=0 is within range
            if (minX <= 0 && maxX >= 0) {
                int yAxisX = (int) ((0 - minX) * scaleX);
                g.drawLine(yAxisX, 0, yAxisX, height);
            }

        }

        private void drawPoints(Graphics2D g2) {
            g2.setColor(Color.RED);
            Iterator<Point> itr = coordinates.iterator();
            Point previous = itr.next();
            while (itr.hasNext()) {
                Point curr = itr.next();
                // get point to scale for the window
                int x = curr.getScaledX(minX, scaleX);
                int y = height - curr.getScaledY(minY, scaleY);

                // draw current point.
                g2.fill(new Ellipse2D.Double(x, y, 5, 5));
                // On hover, display getX() and getY()

                // draw a line between previous and curr
                int previousX = previous.getScaledX(minX, scaleX);
                int previousY = height - previous.getScaledY(minY, scaleY);
                g2.drawLine(previousX, previousY, x, y);

                previous = curr;
            }
        }
    }

}
