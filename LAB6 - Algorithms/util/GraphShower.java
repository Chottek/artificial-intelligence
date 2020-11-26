package pl.fox.nailabs.util;

import javax.swing.*;
import java.awt.*;

public class GraphShower extends JFrame {

    public void run(String name, double[] x, double[] y) {
        setTitle(name);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new GraphDrawer(x, y), BorderLayout.CENTER);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

}
