package dijkstras;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileReader {
    static Node[] LoadGraphFile(String filename){
        ArrayList<Node> nodes = new ArrayList<>();
        try(Stream<String> stream = Files.lines(Paths.get(filename))){
            stream.forEach(line-> nodes.add(StringParser(line)));
            return nodes.toArray(new Node[nodes.size()]);
        }
        catch(IOException e){
            return null;
        }
    }

    private static Node StringParser(String input){
        if(input.startsWith("#")) return null;
        try{
            int number = Integer.parseInt(input.charAt(0) + "");
            String[] adjs = input.substring(input.indexOf(":") + 1).split(";");
            Node n = new Node(number);
            for(String s : adjs){
                String[] vars = s.split(",");
                int adj_num = Integer.parseInt(vars[0].trim());
                int adj_cost = Integer.parseInt(vars[1].trim());
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
