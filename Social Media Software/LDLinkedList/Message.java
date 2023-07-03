package LDLinkedList;
public class Message extends Account {
    public int messageId, senderId, receiverId;
    private String content;

    /**
     * Message constructor
     * 
     * @param senderId   keeps account id of sender user
     * @param receiverId keeps account id of receiver user
     * @param content    keeps the message content
     * @param messageId  keeps the id of message
     */
    public Message(int senderId, int receiverId, String content, int messageId) {
        super(senderId, "", "", "", true);
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.messageId = messageId;

    }

    /**
     * helps to print inbox messages one by one
     * 
     * @param receiver keeps the name of receiver user
     * @param sender   keeps the name of sender
     */
    public void viewInbox(String receiver, String sender) {
        System.out.println("------------------------------------");
        System.out.println("Message ID: " + this.messageId);
        System.out.println("From: " + sender);
        System.out.println("To: " + receiver);
        System.out.println("Message: " + this.content + "\n");
    }

    /**
     * helps to print outbox messages one by one
     * 
     * @param receiver keeps the name of receiver user
     * @param sender   keeps the name of sender user
     */
    public void viewOutbox(String receiver, String sender) {
        System.out.println("------------------------------------");
        System.out.println("Message ID: " + this.messageId);
        System.out.println("From: " + sender);
        System.out.println("To: " + receiver);
        System.out.println("Message: " + this.content + "\n");
    }
}