
import java.util.List;
import java.util.Map;

public class TestCases implements Runnable {

    private  String FileName;
    private int X_SIZE;
    private int Y_SIZE;

    public TestCases(String FileName, int X_SIZE, int Y_SIZE) {
        this.FileName = FileName;
        this.X_SIZE = X_SIZE;
    	this.Y_SIZE = Y_SIZE;
    }

    
    public void test(){
    //****** MAIN FUNCTION ******	

    CSE222Map Map = new CSE222Map(this.FileName, this.X_SIZE, this.Y_SIZE);    
    CSE222Graph graph = new CSE222Graph(Map);
    Map<String, List<String>> graph2 = graph.getGraph();
    CSE222Dijkstra dij = new CSE222Dijkstra();
    List DijkstraPath = dij.findShortestPath(graph2, graph.startNode, graph.endNode);
    CSE222BFS BFS = new CSE222BFS(graph);
    List BFSPath = BFS.findShortestPath();
    Map.convertPNG();
    Map.drawLine(DijkstraPath,1); //second parameter is for algorithm number 1 means dijk 2 means bfs
    Map.drawLine(BFSPath,2);  //second parameter is for algorithm number 1 means dijk 2 means bfs
    Map.writePath(DijkstraPath, 1);  //second parameter is for algorithm number 1 means dijk 2 means bfs
    Map.writePath(BFSPath, 2);  //second parameter is for algorithm number 1 means dijk 2 means bfs
    
    
    System.out.println("\n\n*******************\nMap is " + this.FileName + " with X_SIZE " + this.X_SIZE + " and Y_SIZE " + this.Y_SIZE + "\n********************\n");
    System.out.println("Dijkstra Path: "+ DijkstraPath.size());
	System.out.println("BFS Path: "+ BFSPath.size());

    }

    @Override
    public void run() {
        test();
    }
}

