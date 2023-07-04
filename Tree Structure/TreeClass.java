import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class TreeClass {
    public DefaultMutableTreeNode root;

    public TreeClass() {
        String[][] data = null;
        int rowCount = 0;
        try {
            File file = new File("tree.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                rowCount++;
            }
            data = new String[rowCount][];
            scanner.close();
            File file2 = new File("tree.txt");
            Scanner scanner2 = new Scanner(file2);
            int i = 0;
            while (scanner2.hasNextLine()) {
                String line2 = scanner2.nextLine();
                String[] tokens = line2.split(";");
                data[i] = tokens;
                i++;
            }
            scanner2.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not open! \n" + e);
        }
        this.root = new DefaultMutableTreeNode("Root");
        buildTree(root, data);
    }

    /**
     * Finds the node according to parent
     * 
     * @param root parent node
     * @param data String that will be checked in tree
     * @return if it finds returns the node, if cant't returns null
     */
    public DefaultMutableTreeNode findNode(DefaultMutableTreeNode root, String data) {

        for (int i = 0; i < root.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) root.getChildAt(i);
            if (child.getUserObject().equals(data)) {
                return child;
            }
        }
        return null;
    }

    /**
     * Helper for adding new node adds new child to parent
     * 
     * @param parent parent node
     * @param data   the string that will be added to parent
     */
    public void addNode(DefaultMutableTreeNode parent, String data) {
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(data);
        parent.add(newNode);
    }

    /**
     * Builds the tree according to data
     * 
     * @param root The root of the tree
     * @param data the data in the file
     */
    public void buildTree(DefaultMutableTreeNode root, String[][] data) {
        for (String[] row : data) {
            DefaultMutableTreeNode parent = root;
            for (String node : row) {
                DefaultMutableTreeNode child = findNode(parent, node);
                if (child == null) {
                    addNode(parent, node);
                    child = (DefaultMutableTreeNode) parent.getLastChild();
                }
                parent = child;
            }
        }
    }

    /**
     * search the given input by BFS search algorithm
     * 
     * @param root  root of the tree
     * @param input input of user
     */
    public void bfsSearch(DefaultMutableTreeNode root, String input) {
        System.out.println("Using BFS to find " + input + " in the tree.");
        int step = 1, flag = 0;

        Queue<DefaultMutableTreeNode> queueOfTree = new LinkedList<>();
        queueOfTree.offer(root);
        // it continues until the tree is finished
        while (!queueOfTree.isEmpty()) {
            DefaultMutableTreeNode node = queueOfTree.poll();
            String data = (String) node.getUserObject();
            System.out.print("Step " + step + " -> " + data);
            step++;// counts the steps
            if (data.equals(input)) {// input is found
                System.out.println("(Found!)");
                flag = 1;
                break;
            }
            System.out.println();
            for (int i = 0; i < node.getChildCount(); i++) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
                queueOfTree.offer(child);// inserts childs to queue
            }
        }
        if (flag == 0) // if tree does not contain the input string
            System.out.println("Not found.");

    }

    /**
     * searchs the given input by DFS algorithm
     * 
     * @param root
     * @param input
     */
    public void dfsSearch(DefaultMutableTreeNode root, String input) {
        System.out.println("Using DFS to find " + input + " in the tree.");
        int step = 1, flag = 0;

        Stack<DefaultMutableTreeNode> stackOfTree = new Stack<>();
        stackOfTree.push(root);
        // it continues until the tree is finished
        while (!stackOfTree.isEmpty()) {
            DefaultMutableTreeNode node = stackOfTree.pop();
            String data = (String) node.getUserObject();
            System.out.print("Step " + step + " -> " + data);
            step++;// counts the steps
            if (data.equals(input)) {// input is found
                System.out.println("(Found!)");
                flag = 1;
                break;
            }
            System.out.println();
            for (int i = 0; i < node.getChildCount(); i++) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
                stackOfTree.push(child);// pushes childs to stack
            }
        }
        if (flag == 0) // if tree does not contain the input string
            System.out.println("Not found.");

    }

    /**
     * calls the postOrderTraversal function and checks it
     * 
     * @param root
     * @param input
     */
    public void post(DefaultMutableTreeNode root, String input) {
        System.out.println("Using Post-Order traversal to find " + input + " in the tree.");

        if (!postOrderTraversal(root, input))
            System.out.println("Not Found.");
    }

    static int count = 1; // step number for post order traversal function

    /**
     * searchs the given input in tree bu post-order traversal algorithm
     * 
     * @param tree
     * @param input
     * @return if it is found returns true if not returns false
     */
    public boolean postOrderTraversal(DefaultMutableTreeNode tree, String input) {

        for (int i = 0; i < tree.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) tree.getChildAt(i);

            if (postOrderTraversal(child, input))
                return true;
        }

        String data = (String) tree.getUserObject();

        if (data.equals(input)) {
            System.out.println("Step " + count + " -> " + data + "(Found!)");
            return true;
        } else {
            if (tree.getParent() != null) {
                System.out.println("Step " + count + " -> " + data);
                count++;
            }

        }
        return false;
    }

    /**
     * helper for move function searchs the tree according to given inputs
     * 
     * @param tree
     * @param input
     * @param index
     * @param flag  return value
     * @return returns flag value according to contain given input or not
     */
    public int searchForNode(DefaultMutableTreeNode tree, String[] input, int index, int flag) {
        DefaultMutableTreeNode temp = findNode(tree, input[0]);
        if (temp == null) {
            flag = 0;
        } else {
            index++;
            while (index < input.length && findNode(temp, input[index]) != null) {

                temp = findNode(temp, input[index]);
                if (temp == null) {
                    flag = 0;
                    break;
                }
                index++;
            }
            if (index < input.length)
                flag = 0;
        }
        return flag;
    }

    /**
     * this is an also search function to help move function. It is similar to bfs,
     * uses queue
     * 
     * @param root
     * @param input
     * @return the node or null
     */
    public DefaultMutableTreeNode Search(DefaultMutableTreeNode root, String input) {
        // finds the given input in tree by queue
        int flag = 0;
        DefaultMutableTreeNode temp = null;
        Queue<DefaultMutableTreeNode> queueOfTree = new LinkedList<>();
        queueOfTree.offer(root);
        // it continues until the tree is finished
        while (!queueOfTree.isEmpty()) {
            DefaultMutableTreeNode node = queueOfTree.poll();
            String data = (String) node.getUserObject();
            if (data.equals(input)) {// input is found
                flag = 1;
                temp = node;

            }
            for (int i = 0; i < node.getChildCount(); i++) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
                queueOfTree.offer(child);
            }
        }
        if (flag == 0) // if tree does not contain the input string
            return null;
        else
            return temp;
    }

    /**
     * moves the node in given path to given address then prints tree again after
     * updates
     */
    public void move(DefaultMutableTreeNode tree, String[] input, String output) {
        String firstElement = input[0];
        if (searchForNode(tree, input, 0, 1) == 1) {
            if (!output.equals(input[0])) {
                DefaultMutableTreeNode node = Search(tree, input[0]);
                DefaultMutableTreeNode year = Search(tree, input[0]);
                DefaultMutableTreeNode moved = null;
                DefaultMutableTreeNode course = null;

                for (int i = 1; i < input.length; i++) {
                    node = Search(node, input[i]);
                    if (i == input.length - 1)
                        moved = node;
                    if (i == 1)
                        course = node;
                }
                input[0] = output;
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
                int childNumber = node.getChildCount();

                parent.remove(node);

                if (course.getChildCount() == 0) {
                    DefaultMutableTreeNode bigParent1 = (DefaultMutableTreeNode) course.getParent();
                    bigParent1.remove(course);
                }

                if (year.getChildCount() == 0) {
                    DefaultMutableTreeNode bigParent = (DefaultMutableTreeNode) year.getParent();
                    bigParent.remove(year);
                }

                if (searchForNode(tree, input, 0, 1) == 1) {
                    DefaultMutableTreeNode node2 = Search(tree, input[0]);
                    for (int i = 1; i < input.length; i++) {
                        node2 = Search(node2, input[i]);

                    }
                    DefaultMutableTreeNode movedParent = (DefaultMutableTreeNode) node2.getParent();

                    String first = (String) moved.getUserObject();
                    String second = (String) node2.getUserObject();

                    if (!first.equals(second) || moved.getChildCount() != 0) {
                        movedParent.add(moved);
                        movedParent.remove(node2);
                    }
                    System.out.print("Moved " + firstElement + "->");
                    for (int i = 1; i < input.length - 1; i++) {
                        System.out.print(input[i] + "->");
                    }
                    System.out.println(input[input.length - 1] + " to " + output + ".");
                    for (int i = 0; i < input.length - 1; i++) {
                        System.out.print(input[i] + "->");
                    }
                    System.out.println(input[input.length - 1] + " has been overwritten.");
                } else {
                    DefaultMutableTreeNode newParent = Search(tree, output);
                    if (newParent == null) {
                        newParent = new DefaultMutableTreeNode(output);
                        tree.add(newParent);
                    }
                    if (childNumber == 0) {

                        for (int i = 1; i < input.length; i++) {
                            DefaultMutableTreeNode checkParent = Search(newParent, input[i]);
                            if (checkParent != null) {
                                newParent = checkParent;
                            } else {
                                DefaultMutableTreeNode tempParent = new DefaultMutableTreeNode(input[i]);
                                newParent.add(tempParent);
                                newParent = tempParent;
                            }
                        }
                    }
                    if (childNumber > 0) {
                        for (int i = 1; i < input.length - 1; i++) {
                            DefaultMutableTreeNode tempParent = new DefaultMutableTreeNode(input[i]);
                            newParent.add(tempParent);
                            newParent = tempParent;
                        }
                        newParent.add(node);
                    }
                }

            } else {
                System.out.println("Destination years are same. So");
                System.out.print("Moved " + firstElement + "->");
                for (int i = 1; i < input.length - 1; i++) {
                    System.out.print(input[i] + "->");
                }
                System.out.println(input[input.length - 1] + " to " + output + ".");
                for (int i = 0; i < input.length - 1; i++) {
                    System.out.print(input[i] + "->");
                }
                System.out.println(input[input.length - 1] + " has been overwritten.");

            }
        } else {
            System.out.print("Cannot move ");
            for (int i = 0; i < input.length - 1; i++) {
                System.out.print(input[i] + "->");
            }

            System.out.println(input[input.length - 1] + " because it doesn't exist in the tree.");
        }
        // prints the tree again after changes in move function
        DefaultTreeModel treeModel = new DefaultTreeModel(tree);
        JTree tree2 = new JTree(treeModel);
        JFrame frame = new JFrame("Tree");
        JPanel panel = new JPanel();
        panel.add(tree2);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
