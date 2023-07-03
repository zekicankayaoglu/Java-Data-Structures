import java.util.ArrayList;
import java.util.Map;

public class selectionSort {
    myMap originalMap;
    myMap sortedMap;
    ArrayList<String> aux = new ArrayList<>();

    /**
     * gets original map and creates aux arraylist by its keys
     * 
     * @param originalMap
     */
    public selectionSort(myMap originalMap) {
        this.originalMap = originalMap;
        this.sortedMap = new myMap();
        this.aux = new ArrayList<String>(this.originalMap.map.keySet());
    }

    /**
     * starts with the first element and compare all elements with next elements and
     * when find the last less one makes replacement with it
     */
    public void selectionSortByCount() {
        long start = System.nanoTime();
        for (int i = 0; i < aux.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < aux.size(); j++) {
                if (originalMap.map.get(aux.get(j)).count < originalMap.map.get(aux.get(index)).count) {

                    index = j;
                }
            }
            String minCountString = aux.get(index);
            aux.set(index, aux.get(i));
            aux.set(i, minCountString);

        }
        long end = System.nanoTime();
        System.out.println("\nSelection sort run time: " + (end - start) + " ns");
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
