package LDLinkedList;


public class TestClass4 {

    // SCENARIO 4
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        LDLinkedList<Account> users = new LDLinkedList<Account>();
        users.add(new Account(1, "sibel", "10.03.1995", "Istanbul", false));
        users.add(new Account(2, "gokhan", "21.11.1990", "Ankara", false));
        users.add(new Account(3, "gizem", "10.10.1995", "Izmir", false));
        users.add(new Account(4, "zeki", "09.04.2001", "Erzincan", false));
        users.add(new Account(5, "ali", "11.11.1990", "Ankara", false));
        users.add(new Account(6, "ahmet", "11.10.1995", "Kayseri", false));
        users.add(new Account(7, "mine", "26.04.2001", "Istanbul", false));
        users.add(new Account(8, "yusuf", "01.11.1990", "Ankara", false));
        users.add(new Account(9, "alp", "30.10.1995", "Izmir", false));
        users.add(new Account(10, "eren", "22.10.1995", "Izmir", false));
        
        users.get(0).login();
        users.get(0).addPost("hello everyone",0);
        users.get(0).logout();

        users.get(1).login();
        users.get(1).addLike(users,0,0);
        users.get(1).addComment(users,0,0, "hello I am gokhan");
        users.get(1).logout();

        users.get(2).login();
        users.get(2).addLike(users,0,0);
        users.get(2).addComment(users,0,0, "hello I am gizem");
        users.get(2).showHistory();
        users.get(2).logout();

        users.get(3).login();
        users.get(3).addLike(users,0,0);
        users.get(3).addComment(users,0,0, "hello I am zeki");
        users.get(3).logout();

        users.get(4).login();
        users.get(4).addLike(users,0,0);
        users.get(4).addComment(users,0,0, "hello I am ali");
        users.get(4).logout();
        
        users.get(5).login();
        users.get(5).addLike(users,0,0);
        users.get(5).addComment(users,0,0, "hello I am ahmet");
        users.get(5).viewInteractions(users,"sibel");
        users.get(5).logout();

        users.get(1).login();
        users.get(1).removeLike(users,0,0);
        users.get(1).unComment(users,0,0);
        users.get(1).logout();

        users.get(2).login();
        users.get(2).removeLike(users,0,0);
        users.get(2).logout();
        
        users.get(3).login();
        users.get(3).removeLike(users,0,0);
        users.get(3).logout();

        users.get(4).login();
        users.get(4).removeLike(users,0,0);
        users.get(4).logout();

        users.get(5).login();
        users.get(5).removeLike(users,0,0);
        users.get(5).viewInteractions(users,"sibel");
        users.get(5).logout();

        users.get(6).login();
        users.get(6).follow(users,"sibel");
        users.get(6).printProfile(users,"sibel");
        users.get(6).unfollow(users,"sibel");
        users.get(6).printProfile(users,"sibel");
        users.get(6).logout();

        users.get(7).login();
        users.get(7).follow(users,"sibel");
        users.get(7).printProfile(users,"sibel");
        users.get(7).unfollow(users,"sibel");
        users.get(7).printProfile(users,"sibel");
        users.get(7).logout();

        users.get(8).login();
        users.get(8).follow(users,"sibel");
        users.get(8).printProfile(users,"sibel");
        users.get(8).unfollow(users,"sibel");
        users.get(8).printProfile(users,"sibel");
        users.get(8).follow(users, "zeki");
        users.get(8).showHistory();
        users.get(8).logout();

        users.get(9).login();
        users.get(9).follow(users,"sibel");
        users.get(9).printProfile(users,"sibel");
        users.get(9).logout();

        users.get(0).login();
        users.get(0).blockUser(users,"eren");
        users.get(0).logout();

        users.get(9).login();
        users.get(9).printProfile(users,"sibel");
        users.get(9).logout();

        users.get(0).login();
        users.get(0).unBlockUser(users,"eren");
        users.get(0).logout();

        users.get(9).login();
        users.get(9).printProfile(users,"sibel");
        long endTime = System.currentTimeMillis();
        float total = (endTime - startTime) / 1000F;
        System.out.println("Runtime: " + total);

    }

}