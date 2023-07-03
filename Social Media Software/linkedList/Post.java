package linkedList;
import java.util.LinkedList;

public class Post extends Account {

    public int postId;
    private String content;
    LinkedList<Like> likes = new LinkedList<Like>();
    LinkedList<Comment> comments = new LinkedList<Comment>();

    /**
     * 
     * @param accountId keeps the account id
     * @param post      keeps the content of the post
     * @param postId    keeps the postid
     * @param username  keeps the name of the owner of the post
     */
    public Post(int accountId, String post, int postId, String username) {
        super(accountId, username, "", "", true);
        this.postId = postId;
        this.content = post;
    }

    /**
     * adds new comment to posts
     * 
     * @param postId        keeps the postid
     * @param username      keeps the name of the owner of the post
     * @param interactionId keeps the interaction id
     * @param comment       keeps the comment
     */
    public void addComment(int postId, String username, int interactionId, String comment) {
        comments.add(new Comment(accountId, username, interactionId, comment));
    }
    public void removeComment(int postId, String username, int interactionId){
        for(int i = 0; i < comments.size(); i++){
            if(comments.get(i).username == username) comments.remove(i);
        }
    }
    /**
     * prints comments one by one
     * 
     * @param postId keeps the postid
     */
    public void printComments(int postId) {
        if (comments.size() == 0)
            System.out.println("The post has no comments.");
        else if (comments.size() > 0)
            System.out.println("The post has " + comments.size() + " comment(s).");
        
        for (int i = 0; i < comments.size(); i++) {
            System.out.print("Comment " + (i+1) + ": ");
            comments.get(i).printComments(postId);
        }
    }

    /**
     * creates new like object to keep the informations
     * 
     * @param postId        keeps the postid
     * @param username      keeps name of the liker
     * @param interactionId keeps the interaction id
     */
    public void addLike(int postId, String username, int interactionId) {
        likes.add(new Like(accountId, username, interactionId));
    }
    public void removeLike(int postId, String username, int interactionId){
        for(int i = 0; i < likes.size(); i++){
            if(likes.get(i).username == username) likes.remove(i);
        }
    }

    /**
     * prints the likers of posts one by one
     * 
     * @param postId keeps the postid
     */
    public void printLikes(int postId) {
        if (likes.size() == 0)
            System.out.println("The post has no likes.");
        else {
            System.out.print("The post was liked by the following account(s): ");
            for (int i = 0; i < likes.size(); i++) {
                
                likes.get(i).printLikes(postId);
            }
            System.out.print("\n");
        }
    }

    /**
     * returns the content of current post
     * 
     * @return post content
     */
    public String getContent() {
        return this.content;
    }

    @Override
    public int getId() {
        return this.postId;
    }

    /**
     * prints the postid's and contents
     */
    public void printPosts() {
        System.out.println("(Postid: " + getId() + ") " + username + ": " + getContent() + ".");
    }
}