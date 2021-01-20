package pl.fox.grapher;

import pl.fox.grapher.util.FileReader;

public class Controller {

    private static final int NODE_COUNT = 10;
    private static final double[] DISTANCES = {5, 7, 3, 2, 10, 9, 8, 13, 8, 5};

    private FileReader fr;

    private final boolean doIHaveToReadFile;

    private static final String PATH = ".\\test.graph";

    public Controller(boolean doIHaveToReadFile){
        this.doIHaveToReadFile = doIHaveToReadFile;

        if(doIHaveToReadFile){
            fr = new FileReader(PATH);
        }
    }

    public void control(){
        Graph g = new Graph();

        if(doIHaveToReadFile){
            g.setNodeList(fr.readFile());

        }else{
            g.initNodes(NODE_COUNT, null);
        }
        g.toString();
    }

}
