import java.util.ArrayList;
import java.util.Map;

public class bubbleSort {
    myMap originalMap;
    myMap sortedMap;
    ArrayList<String> aux = new ArrayList<>();

    /**
     * gets original map and creates aux arraylist by its keys
     * 
     * @param originalMap
     */
    public bubbleSort(myMap originalMap) {
        this.originalMap = originalMap;
        this.sortedMap = new myMap();
        this.aux = new ArrayList<String>(this.originalMap.map.keySet());
    }

    /**
     * sorts the map with bubble sort algorithm. compares the element with nested
     * loops then replace them. And calculates the run time
     */
    public void bubbleSortByCount() {
        long start = System.nanoTime();
        for (int i = 0; i < aux.size() - 1; i++) {
            for (int j = 0; j < aux.size() - i - 1; j++) {
                if (originalMap.map.get(aux.get(j)).count > originalMap.map.get(aux.get(j + 1)).count) {
                    String tempString = aux.get(j);
                    aux.set(j, aux.get(j + 1));
                    aux.set(j + 1, tempString);
                }
            }
        }
        long end = System.nanoTime();
        long runTime = end - start;
        System.out.println("\nbubble sort run time: " + runTime + " ns");
        for (int i = 0; i < aux.size(); i++) {
            sortedMap.map.put(aux.get(i), originalMap.map.get(aux.get(i)));
        }
        sortedMap.str = originalMap.str;
        printMap();
    }

    /**
     * prints the sorted map
     */
    public void printMap() {
        if (this.sortedMap.str.trim().length() >= 1) {
            System.out.println("The sorted map:");
            for (Map.Entry<String, info> it : sortedMap.map.entrySet()) {
                System.out.print("Letter: " + it.getKey() + " - Count: " + it.getValue().count + " - Words: [");
                for (int i = 0; i < it.getValue().words.size(); i++) {
                    if (i == it.getValue().words.size() - 1) {
                        System.out.print(it.getValue().words.get(i) + "]");
                    } else {
                        System.out.print(it.getValue().words.get(i) + ", ");
                    }

                }
                System.out.println();
            }
        }
    }
}
