import java.util.ArrayList;
import java.util.List;
class Node {
    int value;
    int x;
    int y;
    List<Node> neighbors = new ArrayList<>();

    public Node(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

