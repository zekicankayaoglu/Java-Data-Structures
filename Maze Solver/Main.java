

public class Main {
    
    public static void main(String[] args){
        //Please check the first letters of txt files!
        new Thread(new TestCases("map01.txt", 500, 500)).start();
        new Thread(new TestCases("map02.txt", 500, 500)).start();
        new Thread(new TestCases("map03.txt", 500, 500)).start();
        new Thread(new TestCases("map04.txt", 500, 500)).start();
        new Thread(new TestCases("map05.txt", 500, 500)).start();
        new Thread(new TestCases("map06.txt", 500, 500)).start();
        new Thread(new TestCases("map07.txt", 500, 500)).start();
        new Thread(new TestCases("map08.txt", 500, 500)).start();
        new Thread(new TestCases("map09.txt", 500, 500)).start();
        new Thread(new TestCases("map10.txt", 500, 500)).start();
    }
}
