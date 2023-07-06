import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.*;

import java.awt.Color;
import java.util.List;
import java.awt.image.BufferedImage;

public class CSE222Map{
    int startPointY;
    int startPointX;
    int endPointY;
    int endPointX;
    int[][] matrix;
    String fileName;

    /**
     * Constructs a CSE222Map object by reading map data from an input file.
     *
     * @param inputFile the name of the input file containing map data
     * @param X_SIZE the X size of the map
     * @param Y_SIZE the Y size of the map
     */
    public CSE222Map(String inputFile, int X_SIZE, int Y_SIZE){
        fileName = inputFile;

        try{
            File file = new File(inputFile);
            Scanner scanner = new Scanner(file);
            String startCoord = scanner.nextLine();
            String[] startCoords = startCoord.split(",");

            this.startPointY = Integer.parseInt(startCoords[0]);
            this.startPointX = Integer.parseInt(startCoords[1]);
            
            String endCoord = scanner.nextLine();
            String[] endCoords = endCoord.split(",");

            this.endPointY = Integer.parseInt(endCoords[0]);
            this.endPointX = Integer.parseInt(endCoords[1]);

            int row = 0;
            int column = 0;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] values = line.split(",");
                column = values.length;
                row++;
            }
            scanner.close();
            matrix = new int[row][column];
            File file1 = new File(inputFile);
            Scanner scanner2 = new Scanner(file1);            

            int rowCount = -2;
            while(scanner2.hasNextLine()){
                String line = scanner2.nextLine();
                if(rowCount >= 0){

                    String[] values = line.split(",");
                    for(int i = 0; i < values.length; i++){
                        matrix[rowCount][i] = Integer.parseInt(values[i]);
                    }      
                }          
                rowCount++;
            }
            scanner2.close();
            
        }catch(FileNotFoundException e){
            System.out.println("File can not found!! Check file name...\n");
        }
    }
    /**
     * Converts the map to a PNG image.
     */
    public void convertPNG(){
        BufferedImage image = new BufferedImage(matrix.length,matrix[0].length,BufferedImage.TYPE_INT_RGB);
            for(int j = 0; j < matrix.length; j++){

                for(int i = 0; i < matrix[0].length; i++){
                   int value = matrix[i][j];
                   Color color;
                   if(value == 1){
                        color = Color.DARK_GRAY;
                   }else color = Color.WHITE;
                   image.setRGB(i,j,color.getRGB());
                }
            }

            String output = fileName.replace(".txt",".png");
            try{
                File outputFile = new File(output);
                ImageIO.write(image,"png",outputFile);
                
            }catch(IOException e){
                System.out.println("PNG could not create!!!");
            }
    }

    /**
     * Returns the key node for the given coordinates.
     *
     * @param y the Y coordinate
     * @param x the X coordinate
     * @return the key as a string
     */
    private String getNodeKey(int y, int x) {
        return "(" + y + ", " + x + ")";
    }
    /**
     * Checks if the given coordinates are part of the path.
     *
     * @param path the list representing the path
     * @param y the Y coordinate
     * @param x the X coordinate
     * @return 1 if the coordinates are part of the path, 0 otherwise
     */
    private int checkPath(List path,int y, int x){
        int flag = 0;
        if(path.size() == 0){
            return 0;
        }
        for(int i = 0; i < path.size(); i++){
            if(getNodeKey(y, x).equals(path.get(i))){
                
                flag = 1;
                break;
            }
        }
        return flag;
    }
    /**
     * Draws the path line on the map image.
     *
     * @param path the list representing the path
     * @param algorithm the algorithm used to find the path (1 for Dijkstra's algorithm, 2 for BFS algorithm)
     */
    public void drawLine(List path, int algorithm){
        
        BufferedImage image = new BufferedImage(matrix.length,matrix[0].length,BufferedImage.TYPE_INT_RGB);
            for(int j = 0; j < matrix.length; j++){

                for(int i = 0; i < matrix[0].length; i++){
                   int value = matrix[i][j];
                   if(checkPath(path, i, j) == 1){
                    value = 2;
                   }
                   Color color = Color.WHITE;
                   if(value == 1){
                        color = Color.DARK_GRAY;
                   }else if(value == 2){
                    if(algorithm == 1) color = Color.RED;
                    if(algorithm == 2) color = Color.BLUE;
                    }
                   else color = Color.WHITE;
                   image.setRGB(i,j,color.getRGB());
                }
            }
            String output = "output.png";
            if(algorithm == 1) output = fileName.replace(".txt","_dijkstra.png");
            if(algorithm == 2) output = fileName.replace(".txt","_BFS.png");

            try{
                File outputFile = new File(output);
                ImageIO.write(image,"png",outputFile);
               
            }catch(IOException e){
                System.out.println("PNG could not create!!!");
            }

    }
    /**
     * Writes the path to a text file.
     *
     * @param path the list representing the path
     * @param algorithm the algorithm used to find the path (1 for Dijkstra's algorithm, 2 for BFS algorithm)
     */
    public void writePath(List path, int algorithm) {
        String filename = fileName;
        if(algorithm == 1) filename = fileName.replace(".txt", "_dijkstra_path.txt");
        if(algorithm == 2) filename = fileName.replace(".txt", "_BFS.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for(int i = 0; i < path.size(); i++){
                writer.write(path.get(i).toString());
                writer.newLine();
            }
           
        } catch (IOException e) {
            System.out.println("Error: txt file could not created!!!");
        }
    }  
}