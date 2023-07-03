public class Main {

    public static void main(String[] args) {
        System.out.print("\nTest1 \n");

        myMap test = new myMap();// first creats the myMap object
        test.preprocessOperation("    ");// preprocess for input string for part 1
        test.buildMap();// builds the map for part 2
        test.printMap(); // prints the map for part 2
        System.out.println();
        mergeSort merge = new mergeSort(test);
        merge.sortMapByCount();// sorts the map then prints it

        System.out.print("\nTest2 \n");

        myMap test2 = new myMap();
        test2.preprocessOperation("Buzzing bees buzz.");
        test2.buildMap();
        test2.printMap();
        System.out.println();
        mergeSort merge2 = new mergeSort(test2);
        merge2.sortMapByCount();

        System.out.print("\nTest 3\n");

        myMap test3 = new myMap();
        test3.preprocessOperation("'Hush, hush!' whispered the rushing wind.");
        test3.buildMap();
        test3.printMap();
        System.out.println();
        mergeSort merge3 = new mergeSort(test3);
        merge3.sortMapByCount();
    }
}
