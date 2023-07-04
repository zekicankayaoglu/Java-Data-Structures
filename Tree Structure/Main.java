import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

public class Main {
    /**
     * calls buildTree with data and creates the tree then gets inputs from user and
     * calls the functions by these parameters
     * 
     * @param args
     */
    public static void main(String[] args) {
        TreeClass tree = new TreeClass();

        // prints the tree
        DefaultTreeModel treeModel = new DefaultTreeModel(tree.root);
        JTree tree2 = new JTree(treeModel);
        JFrame frame = new JFrame("Tree");
        JPanel panel = new JPanel();
        panel.add(tree2);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter the input for BFS algorithm:");
        String inputBFS = input.next();
        tree.bfsSearch(tree.root, inputBFS);
        System.out.println("-------------------------------------------\n");

        System.out.println("\nEnter the input for DFS algorithm:");
        String inputDFS = input.next();
        tree.dfsSearch(tree.root, inputDFS);
        System.out.println("-------------------------------------------\n");

        System.out.println("Enter the input for Post-Order traversal algorithm:");
        String inputPost = input.next();
        tree.post(tree.root, inputPost);
        System.out.println("-------------------------------------------\n");
        frame.setVisible(false);

        System.out.println("Enter the path of node that you want to move:");
        System.out.println("Format:2022->CSE222->LECTURE1\n");
        String inputPath = input.next();
        String[] path = inputPath.split("->");
        System.out.println("Enter the destination year that you want to move to it:");
        String outputPath = input.next();
        tree.move(tree.root, path, outputPath);

        input.close();
    }
}
