package homework1;
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

    private Post posts[] = new Post[100];
    private Message messages[] = new Message[100];
    private Account following[] = new Account[100];
    private Account followers[] = new Account[100];
    private Account blocked[] = new Account[100];

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
    public void viewInteractions(Account users[], String username){
        if(loggedIn == true){
            int searchUser = findUser(users,username);
            if(searchUser != -1){
                System.out.println("...Viewing " + users[searchUser].username + "'s posts interactions...");
                if(users[searchUser].getPostNumber() == -1){System.out.println("This user has no posts!!!");}
                for(int i=1;i<20;i++){                          
                    if(users[searchUser].posts[i] != null && users[searchUser].posts[i].postId == i){
                        System.out.println("--------------------------------------------------------");
                        System.out.println("(PostID: " + i + "): "+ users[searchUser].posts[i].getContent());
                        users[searchUser].printLikes(i);
                        users[searchUser].printComments(i);
                        System.out.println("--------------------------------------------------------");
                    }
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
    public void addComment(Account users[], int postId,int interactionId,String comment){
        if(loggedIn == true){
            int searchUser = findUserPost(users, postId);
            if(searchUser != -1){
                System.out.println("Adding a comment on a post of " + users[searchUser].username + "...\n");
                for(int i = 1; i<=users[searchUser].getPostNumber();i++){
                    if(users[searchUser].posts[i] != null && users[searchUser].posts[i].postId == postId) 
                        users[searchUser].posts[i].addComment(postId,username,interactionId,comment);
                }
            }else{System.out.println("This postId does not exists!!!");}
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    /**
     * printComments is helper method for printing the user's posts.
     * @param postId is the id of the post.
     */
    private void printComments(int postId){
        for(int i = 1; i<getPostNumber();i++){
            if(posts[i] != null && posts[i].postId == postId) posts[i].printComments(postId);                
        }
    }
    /**
     * addLike method is adding like to user's posts.
     * @param users comes from main class as array of accounts.
     * @param postId is the id of post.
     * @param interactionId is the id of interaction.
     */
    public void addLike(Account users[], int postId,int interactionId){
        int flag = 0;
        if(loggedIn == true){
            int searchUser = findUserPost(users, postId);
            if(searchUser != -1){
                System.out.println("Liking a post of " + users[searchUser].username + "...\n");
                for(int i = 1; i<=users[searchUser].getPostNumber();i++){
                    if(users[searchUser].posts[i] != null && users[searchUser].posts[i].postId == postId){
                        for(int x = 0;x < 20; x++){
                            if(users[searchUser].posts[i].likes[x] != null && users[searchUser].posts[i].likes[x].username == username){
                                flag=1;
                                System.out.println(this.username + " liked this post before!!!\n");
                            }
                        }
                        if(flag == 0) users[searchUser].posts[i].addLike(postId,username,interactionId);
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
        for(int i = 1; i<getPostNumber();i++){
            if(posts[i] != null && posts[i].postId == postId) posts[i].printLikes(postId);                
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
    public void sendMessage(Account users[], int senderId, int receiverId, String content, int messageId){
        int checkFollow = 0;
        if(loggedIn == true){
            int searchUser = findUserAccountId(users, receiverId);
            for(int i = 0 ;i < 20; i++){
                if(following[i] != null && users[searchUser].username == following[i].username) checkFollow = 1;
            }
            if(searchUser != -1){//checks account register
                if(checkBlock(users[searchUser].username) == 0){
                    if(checkFollow == 1){
                        if(senderId != this.accountId){System.out.println("Sender must be current user!!\n");}
                        else{
                            if(receiverId == senderId){System.out.println("You can not send message to yourself!!\n");}
                            else{
                                int senderUser = findUserAccountId(users, senderId);
                                int index = -1;
                            
                                for (int i = 20; i >= 0; i--) {
                                    if (users[senderUser].messages[i] != null) {
                                        index = i+1;
                                        break;
                                    }
                                }
                                if(index == -1) index = 0;
                                users[senderUser].messages[index] = new Message(senderId,receiverId,content,messageId);
                                
                                int receiverUser = findUserAccountId(users, receiverId);
                                System.out.println("Sending a message to " + users[receiverUser].username + "...\n");
                                index = -1;
                                for (int i = 20; i >= 0; i--) {
                                    if (users[receiverUser].messages[i] != null) {
                                        index = i+1;
                                        break;
                                    }
                                }
                                if(index == -1) index = 0;
                                users[receiverUser].messages[index] = new Message(senderId,receiverId,content,messageId);
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
    public void checkInbox(Account users[]){
        int messageNumber = 0;
        if(loggedIn == true){
            System.out.println("...Checking Inbox...");
                for (int i = 0; i < 20; i++) {
                    if(messages[i] != null && messages[i].receiverId == this.accountId) messageNumber++;
                }
            System.out.println("There is/are " + messageNumber + " message(s) in the inbox.\n");
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    /**
     * viewInbox gives all messages in inbox of users.
     * @param users is array of accounts.
     */
    public void viewInbox(Account users[]){
        if(loggedIn == true){
            System.out.println("...Viewing Inbox...");
                for (int i = 0; i < 30; i++) {
                    if(messages[i] != null && messages[i].receiverId == this.accountId){int searchUser = findUserAccountId(users, messages[i].senderId);
                        messages[i].viewInbox(this.username,users[searchUser].username);}
                }  
        }else{System.out.println("Current account is not '" + username + "'s account!!!");}
    }
    /**
     * checkOutbox gives the number of messages in outbox of users.
     * @param users is the array of accounts.
     */
    public void checkOutbox(Account users[]){
        int messageNumber = 0;
        if(loggedIn == true){
            System.out.println("...Checking Outbox...");
                for (int i = 0; i < 30; i++) {
                    if(messages[i] != null && messages[i].senderId == this.accountId) messageNumber++;
                }
            System.out.println("There is/are " + messageNumber + " message(s) in the outbox.\n");
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    /**
     * viewOutbox prints all messages in the outbox of users.
     * @param users is array of accounts.
     */
    public void viewOutbox(Account users[]){
        if(loggedIn == true){
            System.out.println("...Viewing Outbox...");
            for (int i = 0; i < 20; i++) {
                if(messages[i] != null && messages[i].senderId == this.accountId){ int searchUser = findUserAccountId(users, messages[i].receiverId);
                    messages[i].viewOutbox(users[searchUser].username,this.username);
                }
            }
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    /**
     * getPostNumber method finds the number of posts of users.
     * @return the number of posts.
     */
    public int getPostNumber(){
        int index = -1;
        for (int i = 99; i >=0; i--) {
            if (posts[i] != null) {
                index = i + 1;
                break;
            }
        }
        return index;
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
            posts[postId] = new Post(accountId, post, postId,this.username);
        }else{System.out.println("Current account is not '" + username + "'s account!!!\n");}
    }
    /**
     * follow method is adding new following to user's account.
     * At the same time calles getfollewer and adds new follower to following account.
     * @param users is the array of accounts.
     * @param username is the username that wants to follow.
     */
    public void follow(Account users[], String username){
        int checkFollow = 0;
        if(loggedIn == true){
            if(username == this.username){System.out.println("You can not follow yourself!\n");}
            else{
                int searchUser = findUser(users, username);
                for(int i = 0; i < 20; i++){
                    if(following[i] != null && following[i].username == users[searchUser].username) checkFollow = 1;
                }
                if(searchUser != -1){
                    if(checkFollow == 1){System.out.println("You already followed " + username + "...\n");}
                    else{
                        users[searchUser].getFollower(users, this.username);

                        int x=0;
                        System.out.println("...Following " + username + "...\n");
                        for(int i = 99; i>=0;i--){
                            if(following[i] != null) x = i+1;   
                        }
                        following[x] = new Account(users[searchUser].accountId, users[searchUser].username,users[searchUser].birthdate,users[searchUser].location, true);
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
    private void getFollower(Account users[],String username){
        int searchUser = findUser(users, username);
        int x=0;
        for(int i = 99; i>=0;i--){
            if(followers[i] != null) x = i+1;
        }
        followers[x] = new Account(users[searchUser].accountId, users[searchUser].username,users[searchUser].birthdate,users[searchUser].location, true);
    }
    /**
     * printProfile method prints all informations of an account.
     * @param users is array of accounts.
     * @param username is the name of user whose profile will be printed. 
     */
    public void printProfile(Account users[], String username)
    {   
        if(checkBlock(username) == 0){
            int searchUser = findUser(users, username);
            if(searchUser != -1){
                System.out.println("...Viewing " + users[searchUser].username + "'s profile...");
                System.out.println("--------------------------------------------------------");
                System.out.println("User ID: " + users[searchUser].accountId);
                System.out.println("Username: " + users[searchUser].username);
                System.out.println("Location: " + users[searchUser].location);
                System.out.println("Birthdate: " + users[searchUser].birthdate);
                int x=-1,y=-1;
                for(int i = 0; i<100;i++){
                    if(users[searchUser].following[i] != null) x = i;
                }
                
                for(int i = 0; i<100;i++){
                    if(users[searchUser].followers[i] != null) y = i;
                }
                if(x == -1 && y == -1){ System.out.println(users[searchUser].username + " is following 0 account and has 0 follower(s).");}
                else{System.out.println(users[searchUser].username + " is following " + (x+1) + " account(s) and has " + (y+1) + " follower.");}

                if(y!=-1){
                    System.out.print("The followers of " + users[searchUser].username + " are: ");
                    for(int i = 0; i<=y;i++){
                        if(users[searchUser].followers[i] != null) System.out.print(users[searchUser].followers[i].username + ", ");
                    }
                    System.out.print("\n");
                }
                if(x!=-1){
                    System.out.print(users[searchUser].username + " is following: ");
                    for(int i = 0; i<=x;i++){
                        if(users[searchUser].following[i] != null) System.out.print(users[searchUser].following[i].username + ", ");
                    }
                    System.out.print("\n");
                }

                int postNumber = 0;
                for(int i=1;i<20;i++){
                    if(users[searchUser].posts[i]!=null){
                        if(users[searchUser].posts[i].accountId == users[searchUser].accountId) postNumber++;       
                    }
                }
                System.out.println(users[searchUser].username + " has " + postNumber + " posts.");
                System.out.println("--------------------------------------------------------\n");
            }else{System.out.println("User not found!!!\n");}
        }else{System.out.println("This user blocked you :( \n");}
    }
    /**
     * printPosts method prints all posts of users.
     * @param users is array of accounts.
     * @param username is the name of user whose posts will be printed.
     */
    public void printPosts(Account users[], String username){
        int searchUser = findUser(users, username);
        if(loggedIn == true){
            if(searchUser != -1){
                System.out.println("...Viewing " + users[searchUser].username + "'s posts...");
                if(users[searchUser].getPostNumber() == -1){System.out.println("This user has no posts!!!");}
                for(int i=1;i<20;i++){
                    if(users[searchUser].posts[i]!=null){
                        if(users[searchUser].posts[i].accountId == users[searchUser].accountId) users[searchUser].posts[i].printPosts();
                    }
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
    public void blockUser(Account users[], String blockUser){
        if(loggedIn == true){
            int searchUser = findUser(users, this.username);
            
            int blocker = findUser(users, blockUser);
            if(searchUser != -1 && blocker != -1){
                int x=0;
                for(int i = 99; i>=0;i--){
                    if(blocked[i] != null) x = i+1;
                }
                System.out.println(blockUser + " is blocked...\n");
                users[blocker].blocked[x] = users[searchUser];
            }else{System.out.println("User not found!!!\n");}
        }else{System.out.println("Current account is not '" + this.username + "'s account!!!\n");}
    }
    /**
     * finds the index of user according to names.
     * @param username is the name of user whose index will be find.
     */
    private int findUser(Account users[], String username){
        int searchUser=-1;
        for(int i=0;i<100;i++){
            if(users[i] != null){
                if(users[i].username == username) searchUser = i;
            }
        }
        return searchUser;
        
    }
    /**
     * finds the index of user according to postid
     */
    private int findUserPost(Account users[], int postid){
        int searchUser=-1;
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(users[i] != null){
                    if(users[i].posts[j] != null){
                        if(users[i].posts[j].postId == postid) searchUser = i;
                    }
                }
            }
        }
        return searchUser;
    }
    /**
     * finds the index of user according to accountid
     */
    private int findUserAccountId(Account users[], int accountid){
        int searchUser=-1;
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(users[i] != null){
                    if(users[i] != null){
                        if(users[i].accountId == accountid) searchUser = i;
                    }
                }
            }
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
        for(int i = 0; i < 20; i++){
            if(blocked[i] != null)
            if(blocked[i] != null && blocked[i].username == username){
                x = 1;
            }
        }
        return x;
    }
}
