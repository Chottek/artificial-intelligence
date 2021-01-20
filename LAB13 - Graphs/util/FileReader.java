package pl.fox.grapher.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.fox.grapher.Node;

public class FileReader {

    private static final Logger LOG = LoggerFactory.getLogger(FileReader.class);

    private final java.io.File file;
    private final String EXTENSION = ".graph";

    public FileReader(String pathToFile){
        file = new java.io.File(pathToFile);
    }

    public java.util.List<Node> readFile(){
        if(!file.getName().endsWith(EXTENSION)){
            LOG.error("Wrong extension of input file!");
            return new java.util.ArrayList<>();
        }

        try{
            java.util.Scanner sc = new java.util.Scanner(file);
            LOG.info("Reading Graph file:: {}", file.getName());
            String s;

            java.util.List<Node> nodes = new java.util.ArrayList<>();

            while (sc.hasNextLine()) {
                s = sc.nextLine();

                if(s.startsWith("#")){
                    continue;
                }

                if(s.startsWith("!")){
                    String[] names = s.split("to");
                    Node n = new Node(names[0].replaceAll("!", ""));
                    Node n1 = new Node(names[1]);

                    nodes.add(n);
                    nodes.add(n1);
                }
            }

            return nodes;
        }catch(java.io.FileNotFoundException f){
            LOG.error("There was an error reading file!");
        }

        return new java.util.ArrayList<>();
    }

    private void checkFile(){

    }

}
