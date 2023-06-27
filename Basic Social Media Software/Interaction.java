package homework1;
public class Interaction extends Post{

    public int interactionID;
    public int accountId;
    public int postId;
    /**
     * interaction class
     * @param accountId keeps accountid 
     * @param username keeps the username
     * @param interactionId keeps the interaction id
     */
    public Interaction(int accountId, String username, int interactionId){
        super(accountId,"",1,username);
        this.interactionID = interactionId;
    }
}