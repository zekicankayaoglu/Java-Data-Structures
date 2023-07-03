package arrayList;
import java.util.ArrayList;

public class TestClass3 {

    // SCENARIO 3
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ArrayList<Account> users = new ArrayList<Account>();

        users.add(new Account(1, "sibel", "10.03.1995", "Istanbul", false));
        users.add(new Account(2, "gokhan", "21.11.1990", "Ankara", false));
        users.add(new Account(3, "gizem", "10.10.1995", "Izmir", false));

        users.get(0).login();
        users.get(0).addPost("I like java.", 0);
        users.get(0).addPost("Java the coffee", 1);
        users.get(0).follow(users, "gokhan");
        users.get(0).follow(users, "gizem");
        users.get(0).logout();

        users.get(1).login();
        users.get(1).printProfile(users, "sibel");
        users.get(1).printPosts(users, "sibel");
        users.get(1).addLike(users, 0, 0);

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

        users.get(2).login();
        users.get(2).addPost("post1", 2);
        users.get(2).addPost("post2", 3);
        users.get(2).logout();

        users.get(0).login();
        users.get(0).printProfile(users, "gizem");
        users.get(0).addLike(users, 2, 5);
        users.get(0).logout();

        users.get(1).login();
        users.get(1).printProfile(users, "gizem");
        users.get(1).addComment(users, 3, 6, "Nice!");
        users.get(1).sendMessage(users, 2, 3, "Hello!", 2);
        users.get(1).logout();

        users.get(2).login();
        users.get(2).printProfile(users, "gizem");
        users.get(2).viewInteractions(users, "gizem");
        users.get(2).viewInbox(users);


        users.get(2).blockUser(users, "sibel");
        users.get(2).logout();

        users.get(0).login();
        users.get(0).printProfile(users, "gizem");
        users.get(0).sendMessage(users, 1, 3, null, 0);

        // !!! These are error cases !!!
        /* 
        users.get(0).logout();
        users.get(1).login();
        users.get(1).printProfile(users, "zeki"); //user not found error
        users.get(1).viewInteractions(users, "ali"); //user not found error
        users.get(1).printPosts(users, "ali"); //user not found error
        users.get(1).blockUser(users, "ahmet"); //user not found error
        users.get(1).sendMessage(users, 2, 2, null, 0); //cannot message yourself error
        users.get(1).sendMessage(users, 1, 2, null, 0); //sender must be current user
        users.get(1).follow(users, "gokhan"); //can not follow yourself error
        */

        long endTime = System.currentTimeMillis();
        float total = (endTime - startTime) / 1000F;
        System.out.println("Runtime: " + total);
    }

}