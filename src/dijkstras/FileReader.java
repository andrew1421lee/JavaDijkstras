package dijkstras;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileReader {
    static Node[] LoadFile(String filename){
        ArrayList<Node> nodes = new ArrayList<>();
        try(Stream<String> stream = Files.lines(Paths.get(filename))){
            stream.forEach(line->nodes.add(StringParser(line)));
            return (Node[])nodes.toArray();
        }
        catch(IOException e){
            return null;
        }
    }

    static Node StringParser(String input){
        if(input.startsWith("#")) return null;
        try{
            int number = input.charAt(0);
            String[] adjs = input.substring(input.indexOf(":")).split(";");
            Node n = new Node(number);
            for(String s : adjs){
                String[] vars = s.split(",");
                int adj_num = Integer.parseInt(vars[0]);
                int adj_cost = Integer.parseInt(vars[1]);
                n.addAdjNode(new AdjNode(adj_num, adj_cost));
            }
            return n;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error parsing string");
            return null;
        }
    }
}
