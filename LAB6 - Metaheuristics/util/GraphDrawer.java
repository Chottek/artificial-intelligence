package pl.fox.nailabs.util;

import javax.swing.*;
import java.awt.*;

public class GraphDrawer extends JPanel {

    private final double[] x;
    private final double[] y;

    public GraphDrawer(final double[] x, final double[] y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paintComponent(final Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        int xAxis = 0;
        int yAxis = 0;

        double xSum = 0;
        double ySum = 0;

        double MAXX = getHighest(x);
        double MAXY = getHighest(y);

        double greater = Math.max(MAXX, MAXY);

        for(int i = 1; i < x.length; i++) {

            xSum += x[i];

            g2d.setColor(Color.BLUE);
            g2d.fillRoundRect(xAxis,  (getHeight() - (int) (getHeight() / 80 * (xSum / i) * greater)) / 2, 5, 5 ,2 ,2);
            xAxis += 8;


            ySum += y[i];
            g2d.setColor(Color.YELLOW);
            g2d.fillRoundRect(yAxis, (getHeight() - (int) (getHeight() / 80 * (ySum / i) * greater)) / 2, 5, 5, 2, 2);
            yAxis += 8;

        }
    }

    private double getHighest(double[] arr){
        double max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }
}


