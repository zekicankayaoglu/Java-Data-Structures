import java.util.ArrayList;
import java.util.Map;

public class insertionSort {
    myMap originalMap;
    myMap sortedMap;
    ArrayList<String> aux = new ArrayList<>();

    /**
     * gets original map and creates aux arraylist by its keys
     * 
     * @param originalMap
     */
    public insertionSort(myMap originalMap) {
        this.originalMap = originalMap;
        this.sortedMap = new myMap();
        this.aux = new ArrayList<String>(this.originalMap.map.keySet());
    }

    /**
     * starts from the second element and compares all elements with before it then
     * makes replacement and calculates the run time
     */
    public void insertionSortByCount() {
        long start = System.nanoTime();
        for (int i = 1; i < aux.size(); i++) {
            String keyString = aux.get(i);
            int j = i - 1;

            while (j >= 0 && originalMap.map.get(aux.get(j)).count > originalMap.map.get(keyString).count) {
                aux.set(j + 1, aux.get(j));
                j--;
            }

            aux.set(j + 1, keyString);
        }
        long end = System.nanoTime();
        System.out.println("\ninsertion sort run time: " + (end - start) + " ns");
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
