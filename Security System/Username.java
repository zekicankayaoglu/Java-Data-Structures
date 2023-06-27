
public class Username {
    
    public String username;
    /**
     * creates username object
     * @param username
     */
    public Username(String username){
        this.username = username;
    }
    /**
     * Checks the length of the username if it is valid returns the checkIfValidUsername method
     * @return checkIfValidUsername method or false
     */
    public boolean checkUsername(){
        if(username.length() < 1){
            System.out.println("The username is invalid. It should have at least 1 character.\n");
            return false;
        }
        else return checkIfValidUsername(username,username.length()-1);
    }

    /**
     * checks all elements of username by recursion
     * @param username the input username
     * @param index is length of the username than it decreases 1 in each call
     * @return
     */
    private boolean checkIfValidUsername(String username, int index) {
        
        if(!Character.isLetter(username.charAt(index))){ 
            System.out.println("The username is invalid. It should have letters only.\n");
            return false;
        }
        if(index == 0) return true;
        else return checkIfValidUsername(username,index-1);

    }

    
}
