package dijkstras;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * FileReader Class
 * Holds methods for reading and parsing graph input files.
 * @author Anchu
 */
public class FileReader {
    /**
     * LoadGraphFile Method
     * Reads given file and parses strings into node objects.
     * @param filename
     * @return Node array
     */
    static Node[] LoadGraphFile(String filename){
        // create arraylist that will hold nodes
        ArrayList<Node> nodes = new ArrayList<>();
        // try to open the file
        try(Stream<String> stream = Files.lines(Paths.get(filename))){
            // read the file line by line and pass them to the parser and then the arraylist
            stream.forEach(line-> nodes.add(StringParser(line)));
            // return the completed array
            return nodes.toArray(new Node[nodes.size()]);
        }
        // if failed to open file, return null
        catch(IOException e){
            return null;
        }
    }

    /**
     * StringParser
     * Parses given string into node objects
     * @param input
     * @return Node object
     */
    private static Node StringParser(String input){
        // ignore lines that start with #
        if(input.startsWith("#")) return null;
        try{
            // read the first character of a line as the node number
            int number = Integer.parseInt(input.charAt(0) + "");
            // split line by semicolons
            String[] adjs = input.substring(input.indexOf(":") + 1).split(";");
            // create new node with number
            Node n = new Node(number);
            // loop through all strings
            for(String s : adjs){
                // split strings by comma
                String[] vars = s.split(",");
                // parse first int as number
                int adj_num = Integer.parseInt(vars[0].trim());
                // parse second int as cost
                int adj_cost = Integer.parseInt(vars[1].trim());
                // add as adjacent node
                n.addAdjNode(new AdjNode(adj_num, adj_cost));
            }
            // return created node
            return n;
        }
        // if array access failed (incorrect file)
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error parsing string");
            return null;
        }
    }
}
