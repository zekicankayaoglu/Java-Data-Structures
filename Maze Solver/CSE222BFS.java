import java.util.*;

public class CSE222BFS {
    private Map<String, List<String>> graph;
    private String startNode;
    private String endNode;

    /**
     * gets graph, start and end nodes from CSE222Graph object
     * @param cse222Graph
     */
    public CSE222BFS(CSE222Graph cse222Graph) {
        this.graph = cse222Graph.getGraph();
        this.startNode = cse222Graph.startNode;
        this.endNode = cse222Graph.endNode;
    }

    /**
     * finds the path with BFS algorithm
     * @return the shortest path as a list of nodes
     */
    public List<String> findShortestPath() {
        long startTime = System.currentTimeMillis();
        Map<String, String> graphMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        graphMap.put(startNode, null);
        queue.offer(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if(current == null){
                break;
            }
            if (current.equals(endNode)) {
                break; //if finds the end node finishes the loop
            }

            List<String> neighbors = graph.get(current);
            //checks the neighbors of node
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    graphMap.put(neighbor, current);
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        double time = (endTime - startTime)/1000F;
        System.out.println("-->Run time BFS: " + time);
        return buildPath(graphMap);
    }

    /**
     * Builds the path using the graphMap map.
     *
     * @param graphMap the map containing the graphMap-child relationships
     * @return the path as a list of nodes
     */
    private List<String> buildPath(Map<String, String> graphMap) {
        List<String> path = new ArrayList<>();
        String current = endNode;

        while (current != null) {
            path.add(0, current);
            current = graphMap.get(current);
        }
        if(path.size() == 0){
            System.out.println("Path could not find!!!");
            return null;
        }
        return path;
    }
}
