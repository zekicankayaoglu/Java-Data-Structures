import java.util.Stack;

public class Password1 {
    
    public String password1;
    public String username;
    /**
     * creates new password1 object
     * @param password1
     * @param username
     */
    public Password1(String password1,String username){
        this.password1 = password1;
        this.username = username;
    }
    /**
     * first check of password1 Checks length, bracket number and letter number
     * @return if it is invalid returns false or returns the main usernamespirit method
     */
    public boolean checkUserNameSpirit(){
        int bracketCounter = 0,flag=0;
        if(password1.length() < 8){
            System.out.println("Password1 is invalid.It should have at least 8 characters.\n");
            return false;
        }
        for(int i = 0; i < password1.length(); i++){
            if(password1.charAt(i) == '[' ||password1.charAt(i) == ']' ||password1.charAt(i) == '{' ||
            password1.charAt(i) == '}' || password1.charAt(i) == '(' ||password1.charAt(i) == ')') bracketCounter++;
        }
        if(bracketCounter < 2){
            System.out.println("Password1 is invalid. It should have at least 2 brackets.\n");
            return false;
        }
        for(int i = 0; i < password1.length(); i++){
            if(Character.isLetter(password1.charAt(i))) flag = 1;
        }
        if (flag==0) {
        
            System.out.println("Password1 is invalid. It should have letters too.\n");
            return false;
        }
        if(flag == 0 && bracketCounter == 0){
            System.out.println("Password1 is invalid. It should contain only letter and bracket.\n");
            return false;
        }
        else return containsUserNameSpirit();
    }

    /**
     * Check the password1 to contain any character from username
     * @return
     */
    public boolean containsUserNameSpirit() {
        Stack<Character> name = new Stack<>();
        Stack<Character> password = new Stack<>();
        //convert username and password to stack
        
        for (int i = 0; i < this.password1.length(); i++) { //pushes elements stack if it is letter
            if(Character.isLetter(this.password1.charAt(i))) password.push(this.password1.charAt(i));
        }
        for (int i = 0; i < this.username.length(); i++) {
            if(Character.isLetter(this.username.charAt(i))) name.push(this.username.charAt(i));
        }
        while(!password.isEmpty()){  //until password is empty
            char top = password.pop();  //gets top of password
            if(name.search(top) != -1){   //if name contains top of password returns true
                return true;
            }
        }
        System.out.println("The password1 is invalid. It should have at least 1 character from the username.\n");
        return false; //if it did not return true in while it returns false because did not find any same letter
    }

    /**
     * checks the balance of brackets by using stack If there is open bracket then check is there it's close bracket
     * @return if it is not balanced returns false if it is balanced returns true without any printing
     */
    public boolean isBalancedPassword() {
        Stack<Character> password = new Stack<>();
        String newPassword = this.password1.replaceAll("[a-zA-Z]*", "");//removes letters from string

        for (int i = 0; i < newPassword.length(); i++) {
            char character = newPassword.charAt(i);
            if (character == '[' || character == '{' || character == '(') {//push open brackets to stack
                password.push(character);
            } else if (character == ']' || character == '}' || character == ')') {
                if (password.isEmpty()) {//if there is close bracket before open it returns false
                    System.out.println("The password1 is invalid. It should be balanced.\n");
                    return false;
                }
                char top = password.pop();
                if ((character == ']' && top != '[') || (character == '}' && top != '{') || (character == ')' && top != '(')) {
                    System.out.println("The password1 is invalid. It should be balanced.\n");

                    return false;
                }
            }
        }
        if(password.isEmpty()==true){
            return true;
        }
        else{
            System.out.println("The password1 is invalid. It should be balanced.\n");
            return false;
        }
    }
    /**
     * removes the brackets from password1 then calls isPalindromePossible method
     * @return other method to control palindrome
     */
    public boolean checkPalindromePossible(){
        String password = password1.replaceAll("[\\[\\]\\{\\}\\(\\)]", "");//deleting all brackets from string
        int[] check = new int[password.length()];
        
        return isPalindromePossible(password,0,check);
    }
    /**
     * Checks the possibility of palindrome
     * @param password1 password1 input
     * @param counter counts the number of pair elements
     * @param check keeps the pairs that are same elements in this array
     * @return if it can be palindrome returns true else returns false
     */
    private boolean isPalindromePossible(String password1, int counter, int check[]){
        if(counter == password1.length() || counter == password1.length()-1) return true;
        //finds the pairs of letters in password to check palindrome possibility
        for(int i = 0; i < password1.length(); i++){
            for(int j = i+1; j < password1.length(); j++){//makes the array element 1 if there are same letters
                if(check[i] != 1 && check[j] != 1 && password1.charAt(i) == password1.charAt(j)){
                    check[i] = 1;
                    check[j] = 1;
                    counter += 2;
                    return isPalindromePossible(password1, counter, check);
                }
            }
        }
        System.out.println("The password1 is invalid. It should be possible to obtain a palindrome from the password1.\n");
        return false;
    }
}
