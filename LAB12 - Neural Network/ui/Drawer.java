package pl.fox.crazyneurons.ui;

import pl.fox.crazyneurons.Layer;
import pl.fox.crazyneurons.Neuron;

import javax.swing.*;
import java.awt.*;


public class Drawer extends JPanel {

    private final java.util.List<Layer> layers;

    public Drawer(java.util.List<Layer> layers) {
        this.layers = layers;
    }

    @Override
    public void paintComponent(final Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setStroke(new BasicStroke(2));
        for(int i = 0; i < getInputNeurons().size(); i++){


           g.setColor(Color.RED);
            g2d.drawString(
                    String.valueOf(getInputNeurons().get(i)
                            .getWeight(0))
                            .substring(0, 8), (getWidth() / 2), (getHeight() / 2) - 50 + 60 * i);


            g.setColor(Color.GREEN);
            g2d.drawString(
                    String.valueOf(getInputNeurons().get(i)
                            .calculateOutput(getInputNeurons().get(i).getPreviousValues()))
                            .substring(0, 8), (getWidth() / 2) - 60, (getHeight() / 2) - 70 + 60 * i);


            g.setColor(Color.YELLOW);
            g2d.drawLine((getWidth() / 2) - 30, (getHeight() / 2) - 50 + 60 * i, (getWidth() / 2) + 120, (getHeight() /2) - 25);
            g.setColor(Color.BLUE);
            g2d.fillOval((getWidth() / 2) - 50, (getHeight() / 2) - 65 + 60 * i, 25, 25);
        }

        g.setColor(Color.GREEN);
        g2d.drawString(String.valueOf(
                getOutputNeuron()
                        .calculateOutput(
                                getOutputNeuron()
                                        .getPreviousValues()))
                .substring(0, 8), (getWidth() / 2) + 130, (getHeight() /2) - 20);
        g.setColor(Color.RED);
        g2d.fillOval((getWidth() / 2) + 100, (getHeight() /2) - 35, 25, 25);
    }

    private java.util.List<Neuron> getInputNeurons(){
        return layers.get(0).getNeurons();
    }

    private Neuron getOutputNeuron(){
        return layers.get(1).getNeurons().get(0);
    }
}
