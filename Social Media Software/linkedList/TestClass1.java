package linkedList;
import java.util.LinkedList;

public class TestClass1 {

    // SCENARIA 1
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        LinkedList<Account> users = new LinkedList<Account>();

        users.add(new Account(1, "sibel", "10.03.1995", "Istanbul", false));
        users.add(new Account(2, "gokhan", "21.11.1990", "Ankara", false));
        users.add(new Account(3, "gizem", "10.10.1995", "Izmir", false));

        users.get(0).login();
        users.get(0).addPost("I like java.", 0);
        users.get(0).addPost("Java the coffee", 1);
        users.get(0).follow(users, "gokhan");
        users.get(0).follow(users, "gizem");
        users.get(0).logout();
        users.get(1).logout(); // ???? This should give error because user 1 has not logout yet ???????
        users.get(1).login();
        users.get(1).printProfile(users, "sibel");
        users.get(1).printPosts(users, "sibel");
        users.get(1).addLike(users, 0, 0);

        users.get(1).addLike(users, 0, 10);

        users.get(1).addComment(users, 0, 0, "me too");
        users.get(1).follow(users, "sibel");
        users.get(1).follow(users, "gizem");
        users.get(1).sendMessage(users, 2, 3, "This homework is too easy!", 1);
        users.get(1).logout();

        users.get(2).login();
        users.get(2).checkOutbox(users);
        users.get(2).checkInbox(users);
        users.get(2).viewInbox(users);
        users.get(2).printProfile(users, "gizem");
        users.get(2).printProfile(users, "sibel");
        users.get(2).printPosts(users, "sibel");

        users.get(2).viewInteractions(users, "sibel");
        users.get(2).addLike(users, 0, 1);
        users.get(2).addLike(users, 1, 2);
        users.get(2).viewInteractions(users, "sibel");

        users.get(2).logout();
        

        long endTime = System.currentTimeMillis();
        float total = (endTime - startTime) / 1000F;
        System.out.println("Runtime: " + total);


    
    }
}