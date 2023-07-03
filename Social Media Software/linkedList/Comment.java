package linkedList;
import java.util.LinkedList;

public class Comment extends Interaction {

    private String content;

    /**
     * Comment class has content variable
     * 
     * @param accountId
     * @param username
     * @param interactionId
     * @param comment       keeps the content of the comment
     */
    public Comment(int accountId, String username, int interactionId, String comment) {
        super(accountId, username, interactionId);
        this.content = comment;
    }

    /**
     * prints comments and owners one by one
     */
    @Override
    public void printComments(int postId) {
        System.out.println("'" + getName() + "' said '" + content + "'");
    }

}