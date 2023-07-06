import java.util.*;

public class CSE222Dijkstra {
    /**
     * Finds the shortest path between two nodes in a graph using Dijkstra's algorithm.
     *
     * @param graph The graph represented as a map of nodes and their neighbors.
     * @param startNode The starting node for the shortest path.
     * @param endNode The destination node for the shortest path.
     * @return The list of nodes representing the shortest path from the start node to the end node.
     */
    public List<String> findShortestPath(Map<String, List<String>> graph, String startNode, String endNode) {
        long startTime = System.currentTimeMillis();
        // Creates a priority queue to store nodes with their distances
        PriorityQueue<NodeDistancePair> queue = new PriorityQueue<>();
        // Creates a map to store the shortest distances from the start node to all other nodes
        Map<String, Integer> dist = new HashMap<>();
        // Creates a map to store the previous node in the shortest path from the start node
        Map<String, String> prev = new HashMap<>();
        if(graph == null){
            System.out.println("graph could not created!!!");
            return null;
        }
        // Initialize the shortest distances of all nodes to infinity except the start node
        for (String node : graph.keySet()) {
            if (node.equals(startNode)) {
                dist.put(node, 0);
            } else {
                dist.put(node, Integer.MAX_VALUE);
            }
        }
        
        // Adds start node to the priority queue
        queue.offer(new NodeDistancePair(startNode, 0));
        
        while (!queue.isEmpty()) {
            NodeDistancePair currentPair = queue.poll();
            String currentNode = currentPair.getNode();
            if(currentNode == null){
                break;
            }
            if (currentNode.equals(endNode)) {
                break; // If finds the end node, finishes the searching
            }
            
            int curDis = currentPair.getDistance();
            
            // Checks neighbors of the current node
            List<String> neighbors = graph.get(currentNode);
            for (String neighbor : neighbors) {
                int distance = curDis + 1; // Assumes all edge weights are 1
                
                // shortest distance and previous node change if a shorter path is found
                if (distance < dist.get(neighbor)) {
                    dist.put(neighbor, distance);
                    prev.put(neighbor, currentNode);
                    
                    // Add the neighbor to the priority queue with the new distance
                    queue.offer(new NodeDistancePair(neighbor, distance));
                }
            }
        }
        
        // Build the shortest path by backtracking from the end node to the start node
        List<String> shortestPath = new ArrayList<>();
        String currentNode = endNode;
        
        while (currentNode != null) {
            shortestPath.add(0, currentNode);
            currentNode = prev.get(currentNode);
        }
        if(shortestPath.size() == 0){
            System.out.println("Path could not find!!!");
            return null;
        }
        long endTime = System.currentTimeMillis();
        double time = (endTime - startTime)/1000F;
        System.out.println("-->Run time Dijkstra: " + time);
        return shortestPath;
        
    }
    /**
     * Class representing a pair of a node and its distance.
     */
    private class NodeDistancePair implements Comparable<NodeDistancePair>{
        private String node;
        private int distance;
        /**
         * Constructs a new NodeDistancePair with the given node and distance
         *
         * @param node The node.
         * @param distance The distance.
         */
        public NodeDistancePair(String node, int distance) {
            this.node = node;
            this.distance = distance;
        }
        
        public String getNode() {
            return node;
        }
        
        public int getDistance() {
            return distance;
        }
        @Override
        public int compareTo(NodeDistancePair other) {
            return Integer.compare(distance, other.distance);
        }
    }
}
