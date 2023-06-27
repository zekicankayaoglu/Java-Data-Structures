package homework1;
public class TestClass1 {
        
    //SCENARIA 1
    public static void main(String[] args){
        
        Account users[] = new Account[100];
        
        users[1] = new Account(1, "sibel", "10.03.1995","Istanbul", false);
        users[2] = new Account(2, "gokhan","21.11.1990", "Ankara", false);
        users[3] = new Account(3, "gizem","10.10.1995", "Izmir", false);

        users[1].login();
        users[1].addPost("I like java.", 1);
        users[1].addPost("Java the coffee", 2);
        users[1].follow(users,"gokhan");
        users[1].follow(users,"gizem");
        users[1].logout();
        
        users[2].logout(); //???? This should give error because user 1 has not logout yet ???????
        
        users[2].login();
        users[2].printProfile(users, "sibel");
        users[2].printPosts(users, "sibel");
        users[2].addLike(users,1, 0);

        //This won't increase the like number because gokhan liked this post before
        users[2].addLike(users,1, 10);

        users[2].addComment(users, 1, 0, "me too");
        users[2].follow(users,"sibel");
        users[2].follow(users,"gizem");
        users[2].sendMessage(users,2, 3, "This homework is too easy!",1);
        users[2].logout();

        users[3].login();
        users[3].checkOutbox(users);
        users[3].checkInbox(users);
        users[3].viewInbox(users);
        users[3].printProfile(users, "gizem");
        users[3].printProfile(users, "sibel");
        users[3].printPosts(users, "sibel");

        users[3].viewInteractions(users, "sibel");
        users[3].addLike(users, 1, 1);
        users[3].addLike(users, 2, 2);
        users[3].viewInteractions(users, "sibel");
        
        users[3].logout(); 
    }    
}