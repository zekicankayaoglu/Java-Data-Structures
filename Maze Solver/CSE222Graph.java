import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CSE222Graph {
    int size;
    private Map<String, List<String>> graph = new HashMap<>(); //keeps the graph
    String startNode,endNode;

    /**
     * Constructs a CSE222Graph object from a given CSE222Map object.
     *
     * @param map the CSE222Map object representing the map
     */
    public CSE222Graph(CSE222Map map) {

        int rows = map.matrix.length;
        int cols = map.matrix[0].length;
        
        if(map.matrix[map.startPointY][map.startPointX] != 0 || map.matrix[map.endPointY][map.endPointX] != 0){
            System.out.println("Invalid start or end coordinate!!!");
            return;
        }
        //gets start and end nodes
        startNode = getNodeKey(map.startPointY, map.startPointX);
        endNode = getNodeKey(map.endPointY, map.endPointX);

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (map.matrix[y][x] == 0) {
                    String currentNode = getNodeKey(y, x);
                    List<String> neighbors = new ArrayList<>();

                    // Checks neighbor coordinates
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (i == 0 && j == 0) continue; 
                            //skips the first coordinate
                            int newY = y + i;
                            int newX = x + j;

                            if (checkCoordinate(newY, newX, rows, cols) && map.matrix[newY][newX] == 0 ) {
                                String neighborNode = getNodeKey(newY, newX);
                                if (!neighbors.contains(neighborNode)) {
                                    neighbors.add(neighborNode);
                                }
                            }
                        }
                    }

                    graph.put(currentNode, neighbors);
                }
            }
        }
    }
    /**
     * Returns a key for the given coordinates.
     *
     * @param y the Y coordinate
     * @param x the X coordinate
     * @return the key as a string
     */
    private String getNodeKey(int y, int x) {
        return "(" + y + ", " + x + ")";
    }
    /**
     * Checks if the given coordinates are valid within the map boundaries.
     *
     * @param y the Y coordinate
     * @param x the X coordinate
     * @param rows the number of rows in the map
     * @param cols the number of columns in the map
     * @return true if the coordinates are valid, false otherwise
     */
    private boolean checkCoordinate(int y, int x, int rows, int cols) {

        return y >= 0 && y < rows && x >= 0 && x < cols;
    }
    /**
     * Returns the graph representation.
     *
     * @return the graph as a map of nodes and their neighbors
     */
    public Map<String, List<String>> getGraph() {
        return graph;
    }
}