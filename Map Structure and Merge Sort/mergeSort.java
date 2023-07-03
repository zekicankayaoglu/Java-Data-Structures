import java.util.ArrayList;
import java.util.LinkedHashMap;

public class mergeSort {
    myMap originalMap;
    myMap sortedMap;
    ArrayList<String> aux = new ArrayList<>();

    /**
     * the map that builded in second part comes here as originalMap
     * 
     * @param originalMap the first map
     */
    public mergeSort(myMap originalMap) {
        this.originalMap = originalMap;
        this.sortedMap = new myMap();

    }

    /**
     * calls main sort function and fills the sorted map then prints the sorted map
     */
    public void sortMapByCount() {
        sortedMap.map = mergeSortMapByCount(originalMap.map);
        for (String key : sortedMap.map.keySet()) {
            aux.add(key);
        }
        if (this.originalMap.str.trim().length() >= 1) {
            System.out.println("The sorted map:");
            for (int j = 0; j < aux.size(); j++) {
                System.out.print(
                        "Letter: " + aux.get(j) + " - Count: " + sortedMap.map.get(aux.get(j)).count + " - Words: [");
                for (int i = 0; i < sortedMap.map.get(aux.get(j)).words.size(); i++) {
                    if (i == sortedMap.map.get(aux.get(j)).words.size() - 1) {
                        System.out.print(sortedMap.map.get(aux.get(j)).words.get(i) + "]");
                    } else {
                        System.out.print(sortedMap.map.get(aux.get(j)).words.get(i) + ", ");
                    }
                }
                System.out.println();
            }
        }
    }

    /**
     * merge sort algorithm in this function
     * 
     * @param map
     * @return the sorted map
     */
    private LinkedHashMap<String, info> mergeSortMapByCount(LinkedHashMap<String, info> map) {
        if (map.size() <= 1) { // if the size is less or equal 1 it does not continue to algorithm
            return map;
        }
        // first we divide the map into 2 as leftMap and rightMap
        int middleIndex = map.size() / 2;
        LinkedHashMap<String, info> leftMap = new LinkedHashMap<>();
        LinkedHashMap<String, info> rightMap = new LinkedHashMap<>();
        int i = 0;
        // fills these two half map
        for (String key : map.keySet()) {
            if (i < middleIndex) {
                leftMap.put(key, map.get(key));
            } else {
                rightMap.put(key, map.get(key));
            }
            i++;
        }
        // creates two new half map for returns of first maps
        LinkedHashMap<String, info> sortedLeftMap = mergeSortMapByCount(leftMap);
        LinkedHashMap<String, info> sortedRightMap = mergeSortMapByCount(rightMap);
        // creates the real main sorted map
        LinkedHashMap<String, info> sortedMap = new LinkedHashMap<>();
        while (!sortedLeftMap.isEmpty() && !sortedRightMap.isEmpty()) {
            String leftKey = sortedLeftMap.keySet().iterator().next();
            String rightKey = sortedRightMap.keySet().iterator().next();
            // compares the counts of first keys of sortedleft and right maps
            if (sortedLeftMap.get(leftKey).count <= sortedRightMap.get(rightKey).count) {
                // then according to compare adds the less one into main sortedmap
                sortedMap.put(leftKey, sortedLeftMap.get(leftKey));
                sortedLeftMap.remove(leftKey);
            } else {
                sortedMap.put(rightKey, sortedRightMap.get(rightKey));
                sortedRightMap.remove(rightKey);
            }
        }
        // after all operations it fills the sortedmap with all left and right map keys
        sortedMap.putAll(sortedLeftMap);
        sortedMap.putAll(sortedRightMap);

        return sortedMap;// returns the sorted map
    }

}
