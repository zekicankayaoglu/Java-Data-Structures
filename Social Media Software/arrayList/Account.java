package arrayList;
import java.util.ArrayList;

/**
 * <h1> This is super class 'Account' class </h1>
 * @author Zekican Kayaoğlu
 */

public class Account {

    public String username;
    public int accountId;
    private String birthdate;
    private String location;
    private boolean loggedIn;

    private ArrayList<Post> posts = new ArrayList<Post>();
    private ArrayList<Message> messages= new ArrayList<Message>();
    private ArrayList<Account> following= new ArrayList<Account>();
    private ArrayList<Account> followers= new ArrayList<Account>();
    private ArrayList<Account> blocked= new ArrayList<Account>();
    private ArrayList<String> history= new ArrayList<String>();

    /**
     * Constructor of Account with accountid,username,birthdate,location and signed info.
     * @param accountId is id of user's account.
     * @param username is name of user.
     * @param birthdate is birthdate of user.
     * @param location keeps location of user.
     * @param signed is boolean for checking user's register.
     */
    public Account(int accountId,String username,String birthdate,String location, boolean signed) {
        if(signed == false){System.out.println("An account with username " + username + " has been created.\n");}
        this.accountId = accountId;
        this.username = username;
        this.birthdate = birthdate;
        this.location = location;
        signed = true;
        
    }
    
    /**
     * login method logs into the user's aaccount
     * loggedIn variable becomes true if this method is called
     */
    public void login(){
        System.out.println("Logging into an account (username: '" + username + "' )...\n");
        loggedIn = true;
    }
    /**
     * logout method logs out from current user's account.
     * loggedIn variable becomes false for this user.
     */
    public void logout(){
        if(loggedIn == true){
            System.out.println("Logging out from account '" + username + "' ...\n");
            loggedIn = false;
        }
        else{
            System.out.println("Current account is not '" + username + "'s account!!!\n");
            loggedIn = false;
        }
    }
    /**
     * getter for accountId of users.
     * @return account id.
     */
    public int getId(){
        return this.accountId;
    }
    /**
     * viewInteractions method helps to show all interaction of user's posts
     * @param users brings all accounts from main class as array. 
     * @param username the name of the user whose posts we want to see interactions with.
     */
    public void viewInteractions(ArrayList<Account> users, String username){
        if(loggedIn == true){
            int searchUser = findUser(users,username);
            if(searchUser != -1){
                System.out.println("...Viewing " + users.get(searchUser).username + "'s posts interactions...");
                if(users.get(searchUser).posts.size() == 0){System.out.println("This user has no posts!!!");}
                for(int i=0;i<users.get(searchUser).posts.size();i++){                          
                    System.out.println("--------------------------------------------------------");
                    System.out.println("(PostID: " + i + "): "+ users.get(searchUser).posts.get(i).getContent());
                    users.get(searchUser).printLikes(users.get(searchUser).posts.get(i).postId);
                    users.get(searchUser).printComments(users.get(searchUser).posts.get(i).postId);
                    System.out.println("--------------------------------------------------------");
                }
                System.out.print("\n");
            }else{System.out.println("User not found!!!\n");}
        }else{ System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    /**
     * addComment method is used for adding comment to user's posts.
     * @param users comes from main class as array of accounts.
     * @param postId is id of user's posts.
     * @param interactionId is id of interactions.
     * @param comment is user's comment.
     */
    public void addComment(ArrayList<Account> users, int postId,int interactionId,String comment){
        if(loggedIn == true){
            int searchUser = findUserPost(users, postId);
            if(searchUser != -1){
                System.out.println("Adding a comment on a post of " + users.get(searchUser).username + "...\n");
                for(int i = 0; i<users.get(searchUser).posts.size();i++){
                    if(users.get(searchUser).posts.get(i).postId == postId) 
                        users.get(searchUser).posts.get(i).addComment(postId,username,interactionId,comment);
                        history.add("You commented on " + users.get(searchUser).username + "'s post id: " + postId);
                }
            }else{System.out.println("This postId does not exists!!!");}
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    public void unComment(ArrayList<Account> users, int postId,int interactionId){
        int flag = 0;
        if(loggedIn == true){
            int searchUser = findUserPost(users, postId);
            if(searchUser != -1){
                
                for(int i = 0; i<users.get(searchUser).posts.size();i++){
                    if(users.get(searchUser).posts.get(i).postId == postId){
                        for(int j = 0;j < users.get(searchUser).posts.get(i).comments.size(); j++){
                            if(users.get(searchUser).posts.get(i).comments.get(j).username == username){
                                flag = 1;
                                System.out.println("Uncomment on a post of " + users.get(searchUser).username + "...\n");
                                users.get(searchUser).posts.get(i).removeComment(postId,username,interactionId);
                                history.add("You uncommented " + users.get(searchUser).username + "'s post id: " + postId);
                            }
                        }
                    }
                }
                if(flag == 0) {System.out.println(username + " did not add comment on this post before...\n");}
            }else{System.out.println("This postId does not exists!!!");}
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    /**
     * printComments is helper method for printing the user's posts.
     * @param postId is the id of the post.
     */
    private void printComments(int postId){
        for(int i = 0; i<posts.size();i++){
            if(posts.get(i).postId == postId) posts.get(i).printComments(postId);                
        }
    }
    /**
     * addLike method is adding like to user's posts.
     * @param users comes from main class as array of accounts.
     * @param postId is the id of post.
     * @param interactionId is the id of interaction.
     */
    public void addLike(ArrayList<Account> users, int postId,int interactionId){
        int flag = 0;
        if(loggedIn == true){
            int searchUser = findUserPost(users, postId);
            if(searchUser != -1){
                System.out.println("Liking a post of " + users.get(searchUser).username + "...\n");
                for(int i = 0; i<users.get(searchUser).posts.size();i++){
                    if(users.get(searchUser).posts.get(i).postId == postId){
                        for(int x = 0;x < users.get(searchUser).posts.get(i).likes.size(); x++){
                            if(users.get(searchUser).posts.get(i).likes.get(x).username.equals(this.username)){
                                flag=1;
                                System.out.println(this.username + " liked this post before!!!\n");
                            }
                        }
                        if(flag == 0){
                            users.get(searchUser).posts.get(i).addLike(postId,username,interactionId);
                            history.add("You liked " + users.get(searchUser).username + "'s post id: " + postId);   
                        }
                    }
                }
            }else{System.out.println("This postId does not exists!!!");}
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    public void removeLike(ArrayList<Account> users, int postId,int interactionId){
        int flag = 0;
        if(loggedIn == true){
            int searchUser = findUserPost(users, postId);
            if(searchUser != -1){
                System.out.println("Unliking a post of " + users.get(searchUser).username + "...\n");
                for(int i = 0; i<users.get(searchUser).posts.size();i++){
                    if(users.get(searchUser).posts.get(i).postId == postId){
                        for(int x = 0;x < users.get(searchUser).posts.get(i).likes.size(); x++){
                            if(users.get(searchUser).posts.get(i).likes.get(x).username.equals(this.username)){
                                flag=1;
                                users.get(searchUser).posts.get(i).removeLike(postId,username,interactionId);
                                history.add("You unliked " + users.get(searchUser).username + "'s post id: " + postId);
                            }
                        }
                        if(flag == 0) System.out.println(this.username + " did not like this post before!!!\n");
                    }
                }
            }else{System.out.println("This postId does not exists!!!");}
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    /**
     * printlikes method is helper method for printing post's likes.
     * @param postId is the id of the post that want to print likes of it
     */
    private void printLikes(int postId){
        for(int i = 0; i<posts.size();i++){            
            if(posts.get(i).postId == postId){
                posts.get(i).printLikes(postId);    
            }            
        }
    }
    /**
     * sendMessage method sends the message from one user to another user.
     * @param users is array of accounts.
     * @param senderId is the accountId of the sender user.
     * @param receiverId is the accountId of the receiver user.
     * @param content is the message of user's.
     * @param messageId is the id of the message.
     */
    public void sendMessage(ArrayList<Account> users, int senderId, int receiverId, String content, int messageId){
        int checkFollow = 0;
        if(loggedIn == true){
            int searchUser = findUserAccountId(users, receiverId);
            for(int i = 0 ;i < following.size(); i++){
                if(users.get(searchUser).username == following.get(i).username) checkFollow = 1;
            }
            if(searchUser != -1){//checks account register
                if(checkBlock(users.get(searchUser).username) == 0){
                    if(checkFollow == 1){
                        if(senderId != this.accountId){System.out.println("Sender must be current user!!\n");}
                        else{
                            if(receiverId == senderId){System.out.println("You can not send message to yourself!!\n");}
                            else{
                                int senderUser = findUserAccountId(users, senderId);
                          
                                users.get(senderUser).messages.add(new Message(senderId,receiverId,content,messageId));
                                
                                int receiverUser = findUserAccountId(users, receiverId);
                                System.out.println("Sending a message to " + users.get(receiverUser).username + "...\n");
                            
                                users.get(receiverUser).messages.add(new Message(senderId,receiverId,content,messageId));
                            }
                        }
                    }else{System.out.println("You must follow this user to send him/her message...\n");}
                }else{System.out.println("This user blocked you :( \n");}
            }else{System.out.println("Receiver not found!!!\n");}
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    /**
     * checkInbox method gives the number of messages in inbox of users.
     * @param users is array of accounts.
     */
    public void checkInbox(ArrayList<Account> users){
        int messageNumber = 0;
        if(loggedIn == true){
            System.out.println("...Checking Inbox...");
                for (int i = 0; i < messages.size(); i++) {
                    if(messages.get(i).receiverId == this.accountId) messageNumber++;
                }
            System.out.println("There is/are " + messageNumber + " message(s) in the inbox.\n");
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    /**
     * viewInbox gives all messages in inbox of users.
     * @param users is array of accounts.
     */
    public void viewInbox(ArrayList<Account> users){
        if(loggedIn == true){
            System.out.println("...Viewing Inbox...");
                for (int i = 0; i < messages.size(); i++) {
                    if(messages.get(i).receiverId == this.accountId){int searchUser = findUserAccountId(users, messages.get(i).senderId);
                        messages.get(i).viewInbox(this.username,users.get(searchUser).username);}
                }  
        }else{System.out.println("Current account is not '" + username + "'s account!!!");}
    }
    /**
     * checkOutbox gives the number of messages in outbox of users.
     * @param users is the array of accounts.
     */
    public void checkOutbox(ArrayList<Account> users){
        int messageNumber = 0;
        if(loggedIn == true){
            System.out.println("...Checking Outbox...");
                for (int i = 0; i < messages.size(); i++) {
                    if(messages.get(i).senderId == this.accountId) messageNumber++;
                }
            System.out.println("There is/are " + messageNumber + " message(s) in the outbox.\n");
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    /**
     * viewOutbox prints all messages in the outbox of users.
     * @param users is array of accounts.
     */
    public void viewOutbox(ArrayList<Account> users){
        if(loggedIn == true){
            System.out.println("...Viewing Outbox...");
            for (int i = 0; i < messages.size(); i++) {
                if(messages.get(i).senderId == this.accountId){ int searchUser = findUserAccountId(users, messages.get(i).receiverId);
                    messages.get(i).viewOutbox(users.get(searchUser).username,this.username);
                }
            }
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    /**
     * getter for name of users.
     * @return name of user.
     */
    public String getName(){
        return this.username;
    }
    /**
     * addPost method is adding new post to an account.
     * @param post is the user's post.
     * @param postId is the id of the new post.
     */
    public void addPost(String post, int postId){    
        if(loggedIn == true){
            System.out.println("Sharing post...\n");
            posts.add(new Post(accountId, post, postId,this.username));
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    /**
     * follow method is adding new following to user's account.
     * At the same time calles getfollewer and adds new follower to following account.
     * @param users is the array of accounts.
     * @param username is the username that wants to follow.
     */
    public void follow(ArrayList<Account> users, String username){
        int checkFollow = 0;
        if(loggedIn == true){
            if(username == this.username){System.out.println("You can not follow yourself!\n");}
            else{
                int searchUser = findUser(users, username);
                for(int i = 0; i < following.size(); i++){
                    if(following.get(i).username == users.get(searchUser).username) checkFollow = 1;
                }
                if(searchUser != -1){
                    if(checkFollow == 1){System.out.println("You already followed " + username + "...\n");}
                    else{
                        users.get(searchUser).getFollower(users, this.username);
                        System.out.println("...Following " + username + "...\n");
                        history.add("You followed " + users.get(searchUser).username);
                        following.add(new Account(users.get(searchUser).accountId, users.get(searchUser).username,users.get(searchUser).birthdate,users.get(searchUser).location, true));
                    }
                }else{System.out.println("User not found!!!\n");}
            }
        }else{System.out.println("Current account is not '" + this.username + "'s account!!!\n");}
    }
    public void unfollow(ArrayList<Account> users, String username){
        int checkFollow = 0,index=0;
        if(loggedIn == true){
            if(username == this.username){System.out.println("You can not unfollow yourself!\n");}
            else{
                int searchUser = findUser(users, username);
                for(int i = 0; i < following.size(); i++){
                    if(following.get(i).username == users.get(searchUser).username) {checkFollow = 1; index = i;}
                }
                if(searchUser != -1){
                    if(checkFollow != 1){System.out.println("You are not following " + username + "...\n");}
                    else{
                        //users.get(searchUser).getFollower(users, this.username);
                        System.out.println("...Unfollowing " + username + "...\n");
                        users.get(searchUser).removeFollower(users,this.username);
                        following.remove(index);
                        history.add("You unfollowed " + users.get(searchUser).username);
                    }
                }else{System.out.println("User not found!!!\n");}
            }
        }else{System.out.println("Current account is not '" + this.username + "'s account!!!\n");}
    }
    /**
     * getFollower is helper method for following someone.
     * @param users is array of accounts.
     * @param username is the name of the following user.
     */
    private void getFollower(ArrayList<Account> users,String username){
        int searchUser = findUser(users, username);
        followers.add(new Account(users.get(searchUser).accountId, users.get(searchUser).username,users.get(searchUser).birthdate,users.get(searchUser).location, true));
    }
    private void removeFollower(ArrayList<Account> users,String username){
        for(int i = 0;i < followers.size(); i++){
            if(followers.get(i).username == username) followers.remove(i);
        }
    }
    /**
     * printProfile method prints all informations of an account.
     * @param users is array of accounts.
     * @param username is the name of user whose profile will be printed. 
     */
    public void printProfile(ArrayList<Account> users, String username)
    {   
        if(checkBlock(username) == 0){
            int searchUser = findUser(users, username);
            if(searchUser != -1){
                System.out.println("...Viewing " + users.get(searchUser).username + "'s profile...");
                System.out.println("--------------------------------------------------------");
                System.out.println("User ID: " + users.get(searchUser).accountId);
                System.out.println("Username: " + users.get(searchUser).username);
                System.out.println("Location: " + users.get(searchUser).location);
                System.out.println("Birthdate: " + users.get(searchUser).birthdate);

                if(users.get(searchUser).following.size() == 0 && users.get(searchUser).followers.size() == 0){ System.out.println(users.get(searchUser).username + " is following 0 account and has 0 follower(s).");}
                else{System.out.println(users.get(searchUser).username + " is following " + users.get(searchUser).following.size() + " account(s) and has " + users.get(searchUser).followers.size() + " follower.");}

                if(users.get(searchUser).followers.size() != 0){
                    System.out.print("The followers of " + users.get(searchUser).username + " are: ");
                    for(int i = 0; i<users.get(searchUser).followers.size();i++){
                        System.out.print(users.get(searchUser).followers.get(i).username + ", ");
                    }
                    System.out.print("\n");
                }
                if(users.get(searchUser).following.size() != 0){
                    System.out.print(users.get(searchUser).username + " is following: ");
                    for(int i = 0; i<users.get(searchUser).following.size();i++){
                        System.out.print(users.get(searchUser).following.get(i).username + ", ");
                    }
                    System.out.print("\n");
                }

                int postNumber = 0;
                for(int i=0;i<users.get(searchUser).posts.size();i++){

                    if(users.get(searchUser).posts.get(i).accountId == users.get(searchUser).accountId) postNumber++;       
                    
                }
                System.out.println(users.get(searchUser).username + " has " + postNumber + " posts.");
                System.out.println("--------------------------------------------------------\n");
            }else{System.out.println("User not found!!!\n");}
        }else{System.out.println("This user blocked you :( \n");}
    }
    /**
     * printPosts method prints all posts of users.
     * @param users is array of accounts.
     * @param username is the name of user whose posts will be printed.
     */
    public void printPosts(ArrayList<Account> users, String username){
        int searchUser = findUser(users, username);
        if(loggedIn == true){
            if(searchUser != -1){
                System.out.println("...Viewing " + users.get(searchUser).username + "'s posts...");
                if(users.get(searchUser).posts.size() == 0){System.out.println("This user has no posts!!!");}
                for(int i=0;i<users.get(searchUser).posts.size();i++){
                    if(users.get(searchUser).posts.get(i).accountId == users.get(searchUser).accountId) users.get(searchUser).posts.get(i).printPosts();
                    
                }
                System.out.print("\n");
            }else{System.out.println("User not found!!!\n");}
        }else{System.out.println("Current account is not '" + this.username + "'s account!!!\n");}
    }
    /**
     * blockUser method helps to add new account to blocked array.
     * @param users is array of accounts.
     * @param blockUser is the name of user who is blocked for current account.
     */
    public void blockUser(ArrayList<Account> users, String blockUser){
        if(loggedIn == true){
            int searchUser = findUser(users, this.username);
            
            int blocker = findUser(users, blockUser);
            if(searchUser != -1 && blocker != -1){
                
                System.out.println(blockUser + " is blocked...\n");
                users.get(blocker).blocked.add(users.get(searchUser));
                for(int i = 0;i<followers.size();i++){
                    if(followers.get(i).username == blockUser) removeFollower(users, blockUser);
                }
            }else{System.out.println("User not found!!!\n");}
        }else{System.out.println("Current account is not '" + this.username + "'s account!!!\n");}
    }
    public void unBlockUser(ArrayList<Account> users, String unBlock){
        if(loggedIn == true){
            int searchUser = findUser(users, this.username);
            
            int blocker = findUser(users, unBlock);
            if(searchUser != -1 ){
                if(blocker != 0){
                
                    System.out.println(unBlock + " is unblocked...\n");
                    users.get(blocker).blocked.remove(users.get(searchUser));
                }else{System.out.println(username + " didn't block " + unBlock);}
            }else{System.out.println("User not found!!!\n");}
        }else{System.out.println("Current account is not '" + this.username + "'s account!!!\n");}
    }
    
    public void showHistory(){
        if(loggedIn == true){
            System.out.println("----- Your History -----\n  ");
            for(int i = 0; i < history.size(); i++){
                System.out.println(history.get(i) + "\n");
            }
            System.out.println("--------------------\n");
        }else{System.out.println("Current account is not '" + this.username + "'s account!!!\n");}
    }
    /**
     * finds the index of user according to names.
     * @param username is the name of user whose index will be find.
     */
    private int findUser(ArrayList<Account> users, String username){
        int searchUser=-1;
        for(int i=0;i<users.size();i++){
            if(users.get(i).username == username) searchUser = i;
        }
        return searchUser;
    }
    /**
     * finds the index of user according to postid
     */
    private int findUserPost(ArrayList<Account> users, int postid){
        int searchUser=-1;
        for(int i=0;i<users.size();i++){
            for(int j=0;j<users.get(i).posts.size();j++){
                    if(users.get(i).posts.get(j).postId == postid) searchUser = i;
            }
        }
        return searchUser;
    }
    /**
     * finds the index of user according to accountid
     */
    private int findUserAccountId(ArrayList<Account> users, int accountid){
        int searchUser=-1;
        for(int i=0;i<users.size();i++){
            if(users.get(i).accountId == accountid) searchUser = i;
        }
        return searchUser;
    }
    /**
     * Checks all blocked users if this username is found it returns 1
     * @param username is the name of user who will be searched in blocked accounts.
     * @return 1 if the user found else 0.
     */
    private int checkBlock(String username){
        int x = 0;
        for(int i = 0; i < blocked.size(); i++){
            if(blocked.get(i).username == username){
                x = 1;
            }
        }
        return x;
    }
}
