public class Main {

    public static void main(String[] args) {

        // three inputs are given, you can uncomment them to test

        // String inputString = "a bb ccc dddd eeeee ffffff"; //best case
        // String inputString = "xyz xyzz xxyyzz xxxyyyzzz"; // average case
        String inputString = "h he hel helo helow helowo helowor heloworl heloworld"; // worst-case

        System.out.print("\n\n---Merge Sort Test--- \n");

        myMap test1 = new myMap();// first creates the myMap object
        test1.preprocessOperation(inputString);// preprocess

        test1.buildMap();// builds the map
        test1.printMap(); // prints the map
        System.out.println();
        mergeSort select1 = new mergeSort(test1);
        select1.sortMapByCount();// sorts the map then prints it

        System.out.print("\n\n---Selection Sort Test--- \n");

        myMap test2 = new myMap();// first creates the myMap object
        test2.preprocessOperation(inputString);// preprocess

        test2.buildMap();// builds the map
        test2.printMap(); // prints the map
        System.out.println();
        selectionSort select2 = new selectionSort(test2);
        select2.selectionSortByCount();// sorts the map then prints it

        System.out.print("\n\n---Insertion Sort Test--- \n");

        myMap test3 = new myMap();// first creats the myMap object
        test3.preprocessOperation(inputString);// preprocess for input
        // part 1
        test3.buildMap();// builds the map
        test3.printMap(); // prints the map
        System.out.println();
        insertionSort select3 = new insertionSort(test3);
        select3.insertionSortByCount();// sorts the map then prints it

        System.out.print("\n\n---Bubble Sort Test--- \n");
        myMap test4 = new myMap();// first creats the myMap object
        test4.preprocessOperation(inputString);// preprocess for input
        test4.buildMap();// builds the map
        test4.printMap(); // prints the map
        System.out.println();
        bubbleSort select4 = new bubbleSort(test4);
        select4.bubbleSortByCount();// sorts the map then prints it

        System.out.print("\n\n---Quick Sort Test--- \n");
        myMap test5 = new myMap();// first creats the myMap object
        test5.preprocessOperation(inputString);// preprocess for input
        test5.buildMap();// builds the map
        test5.printMap(); // prints the map
        System.out.println();
        quickSort select5 = new quickSort(test5);
        select5.quickSortByCount();// sorts the map then prints it

    }
}
