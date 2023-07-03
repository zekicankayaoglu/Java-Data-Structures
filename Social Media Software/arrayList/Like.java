package arrayList;
import java.util.ArrayList;

public class Like extends Interaction {

    public Like(int accountId, String username, int interactionId) {
        super(accountId, username, interactionId);

    }

    /**
     * prints liker names according to postids
     */
    @Override
    public void printLikes(int postId) {
        System.out.print(getName() + ", ");
    }
}