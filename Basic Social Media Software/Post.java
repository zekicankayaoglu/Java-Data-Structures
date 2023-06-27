package homework1;
public class Post extends Account {
    
    public int postId;
    private String content;
    Like likes[] = new Like[100];
    Comment comments[] = new Comment[100];
    
    /**
     * 
     * @param accountId keeps the account id
     * @param post keeps the content of the post
     * @param postId keeps the postid
     * @param username keeps the name of the owner of the post
     */
    public Post(int accountId, String post, int postId, String username) {
        super(accountId,username,"","",true);
        this.postId = postId;
        this.content = post;
    }
    /**
     * adds new comment to posts
     * @param postId keeps the postid
     * @param username keeps the name of the owner of the post
     * @param interactionId keeps the interaction id
     * @param comment keeps the comment
     */
    public void addComment(int postId,String username, int interactionId,String comment){
        comments[interactionId] = new Comment(accountId,username,interactionId,comment);
    }
    /**
     * prints comments one by one
     * @param postId keeps the postid
     */
    public void printComments(int postId){
        int x = 1;
        for(int i = 0; i <getCommentNumber(); i++){
            if(comments[i]!= null){
                x++;
            }
        }
        if(x == 1) System.out.println("The post has no comments.");
        else if(x > 1) System.out.println("The post has " + (x-1) + " comment(s).");
        x=1;
        for(int i = 0; i <getCommentNumber(); i++){
            if(comments[i]!= null){
                System.out.print("Comment " + x + ": ");
                comments[i].printComments(postId);
                x++;
            }
        }
    }
    /**
     * creates new like object to keep the informations
     * @param postId keeps the postid
     * @param username keeps name of the liker
     * @param interactionId keeps the interaction id
     */
    public void addLike(int postId,String username, int interactionId){
        likes[interactionId] = new Like(accountId,username,interactionId);
    }
    /**
     * prints the likers of posts one by one
     * @param postId keeps the postid
     */
    public void printLikes(int postId){
        int x = 0;
        for(int i = 0; i <getLikeNumber(); i++){
            if(likes[i]!= null){
                x++;
            }
        }
        if(x == 0) System.out.println("The post has no likes.");
        else{
            System.out.print("The post was liked by the following account(s): ");
            for(int i = 0; i <getLikeNumber(); i++){
                if(likes[i]!= null)
                    likes[i].printLikes(postId);
            }
            System.out.print("\n");
        }
    }
    /**
     * finds the like number of a post
     * @return like number
     */
    private int getLikeNumber(){
        int index = -1;
        for (int i = 99; i >=0; i--) {
            if (likes[i] != null) {
                index = i+1;
                break;
            }
        }
        return index;
    }
    /**
     * finds the comment number of a post
     * @return
     */
    private int getCommentNumber(){
        int index = -1;
        for (int i = 99; i >=0; i--) {
            if (comments[i] != null) {
                index = i+1;
                break;
            }
        }
        return index;
    }
    /**
     * returns the content of current post
     * @return post content
     */
    public String getContent(){    
        return this.content;
    }

    @Override
    public int getId(){
        return this.postId;
    }
    /**
     * prints the postid's and contents
     */
    public void printPosts(){
        System.out.println("(Postid: " + getId()+ ") " + username + ": "+ getContent() + ".");
    }
}