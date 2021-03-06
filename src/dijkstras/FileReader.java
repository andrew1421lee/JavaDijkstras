package dijkstras;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Used to pass data around internal functions
 * Contains a vertex and array of edge objects
 */
class ParseData{
    Vertex vertex;
    Edge[] edge;

    // constructor setting all values
    public ParseData(Vertex v, Edge[] e){
        this.vertex = v;
        this.edge = e;
    }
}

/**
 * FileReader Class
 * Holds methods for reading and parsing graph input files.
 * @author Anchu
 */
public class FileReader {
    /**
     * LoadGraphFile Method
     * Reads given file and parses strings into node objects.
     * @param filename file to read
     * @return Graph graph
     */
    static Graph LoadGraphFile(String filename, boolean mode){
        // create arraylist that will hold nodes
        ArrayList<ParseData> from_file = new ArrayList<>();
        // create a new graph to hold all this stuff
        Graph graph = new Graph();
        // try to open the file
        try(Stream<String> stream = Files.lines(Paths.get(filename))){
            // read the file line by line and pass them to the parser and then the arraylist
            stream.forEach(line -> from_file.add(StringParser(line, mode)));
            from_file.forEach(x -> { graph.addVertex(x.vertex); graph.addEdges(x.edge); });
            // return the completed array
            return graph;
        }
        // if failed to open file, return null
        catch(IOException e){
            return null;
        }
    }

    /**
     * StringParser
     * Parses given string into node objects
     * @param input string to parse
     * @return ParseData object
     */
    private static ParseData StringParser(String input, boolean mode){
        // ignore lines that start with #
        if(input.startsWith("#")) return null;
        try{
            // read the first character of a line as the node number
            int number = Integer.parseInt(input.substring(0, input.indexOf(":")));
            // split line by semicolons
            String[] adjs = input.substring(input.indexOf(":") + 1).split(";");
            // create array to hold adjacent edges
            ArrayList<Edge> adj = new ArrayList<>();
            // create array to hold adj numbers
            ArrayList<Integer> adj_nums = new ArrayList<>();
            // loop through all strings
            for(String s : adjs){
                // split strings by comma
                String[] vars = s.split(",");
                if(Objects.equals(vars[0], "")){
                    break;
                }
                // parse first int as number
                int adj_num = Integer.parseInt(vars[0].trim());
                // parse second int as cost
                int adj_cost = Integer.parseInt(vars[1].trim());
                // add as adjacent node
                // Create new edge based on data read and add to lists
                Edge ed = new Edge(number, adj_num, adj_cost, mode);
                adj.add(ed);
                adj_nums.add(ed.getId());
            }
            // return created node
            return new ParseData( new Vertex(number, adj_nums.stream().mapToInt(i -> i).toArray()),
                    adj.toArray(new Edge[adj.size()]) );
        }
        // if array access failed (incorrect file)
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error parsing string");
            return null;
        }
    }
}
