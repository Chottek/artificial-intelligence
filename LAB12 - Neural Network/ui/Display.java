package pl.fox.crazyneurons.ui;

import pl.fox.crazyneurons.Layer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Display extends JFrame {

    private static final String TITLE = "NeuralGraph";

    private final java.util.List<Layer> layers;

    public Display(java.util.List<Layer> layers){
        this.layers = layers;
    }

    public void run() {
        setTitle(TITLE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new Drawer(layers), BorderLayout.CENTER);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);

        try{
            save();
        }catch(IOException ie){
            ie.printStackTrace();
        }

    }

    private void save() throws IOException {
        BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        paint(img.getGraphics());
        File outputFile = new File("saved.png");
        ImageIO.write(img, "png", outputFile);
    }




}
