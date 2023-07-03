import java.util.ArrayList;
import java.util.Map;

public class quickSort {
    myMap originalMap;
    myMap sortedMap;
    ArrayList<String> aux = new ArrayList<>();

    /**
     * gets original map and creates aux arraylist by its keys
     * 
     * @param originalMap
     */
    public quickSort(myMap originalMap) {
        this.originalMap = originalMap;
        this.sortedMap = new myMap();
        this.aux = new ArrayList<String>(this.originalMap.map.keySet());
    }

    /**
     * calls sort function then fills the sortedmap and calculates the run time
     */
    public void quickSortByCount() {
        long start = System.nanoTime();
        quickSortSplit(0, aux.size() - 1);
        long end = System.nanoTime();
        System.out.println("\nQuick sort run time: " + (end - start) + " ns");
        for (int i = 0; i < aux.size(); i++) {
            sortedMap.map.put(aux.get(i), originalMap.map.get(aux.get(i)));
        }
        sortedMap.str = originalMap.str;
        printMap();
    }

    /**
     * divides the map into two and calls itselfs recursively
     * 
     * @param first
     * @param last
     */
    public void quickSortSplit(int first, int last) {
        if (first < last) {
            int pivotElement = quickSortHelper(first, last);
            quickSortSplit(first, pivotElement - 1);
            quickSortSplit(pivotElement + 1, last);
        }

    }

    /**
     * compares the elements then replace them by using pivot that comes from helper
     * function
     * 
     * @param first
     * @param last
     * @return
     */
    public int quickSortHelper(int first, int last) {
        String pivot = aux.get(last);
        int i = first - 1;

        for (int j = first; j < last; j++) {
            if (originalMap.map.get(aux.get(j)).count < originalMap.map.get(pivot).count) {
                i++;
                String tempString = aux.get(i);
                aux.set(i, aux.get(j));
                aux.set(j, tempString);
            }
        }

        String tempString = aux.get(i + 1);
        aux.set(i + 1, aux.get(last));
        aux.set(last, tempString);
        return i + 1;
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
