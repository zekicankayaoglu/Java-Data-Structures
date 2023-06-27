
public class Main {
    private static int[] denominations = {4, 17, 29};

    public static void main(String[] args) {
        System.out.println("Test1: \n");
        Username username1 = new Username("sibelgulmez");
        Password1 password1_1 = new Password1("[rac()ecar]","sibelgulmez");
        Password2 password2 = new Password2(74);
        if(username1.checkUsername())
            if(password1_1.checkUserNameSpirit())
                if(password1_1.isBalancedPassword())
                    if(password1_1.checkPalindromePossible())
                        if(password2.checkPassword2(denominations))
        System.out.println("The username and passwords are valid. The door is opening, please wait...\n");
        
        System.out.println("Test2: \n");
        Username username2 = new Username("");
        Password1 password1_2 = new Password1("[rac()ecar]","");
        Password2 password2_2 = new Password2(74);
        if(username2.checkUsername())
            if(password1_2.checkUserNameSpirit())
                if(password1_2.isBalancedPassword())
                    if(password1_2.checkPalindromePossible())
                        if(password2_2.checkPassword2(denominations))
        System.out.println("The username and passwords are valid. The door is opening, please wait...\n");
        
        System.out.println("Test3: \n");
        Username username3 = new Username("sibel1");
        Password1 password1_3 = new Password1("[rac()ecar]","sibel1");
        Password2 password2_3 = new Password2(74);
        if(username3.checkUsername())
            if(password1_3.checkUserNameSpirit())
                if(password1_3.isBalancedPassword())
                    if(password1_3.checkPalindromePossible())
                        if(password2_3.checkPassword2(denominations))
        System.out.println("The username and passwords are valid. The door is opening, please wait...\n");

        System.out.println("Test4: \n");
        Username username4 = new Username("sibel");
        Password1 password1_4 = new Password1("pass[]","sibel");
        Password2 password2_4 = new Password2(74);
        if(username4.checkUsername())
            if(password1_4.checkUserNameSpirit())
                if(password1_4.isBalancedPassword())
                    if(password1_4.checkPalindromePossible())
                        if(password2_4.checkPassword2(denominations))
        System.out.println("The username and passwords are valid. The door is opening, please wait...\n");

        System.out.println("Test5: \n");
        Username username5 = new Username("sibel");
        Password1 password1_5 = new Password1("abcdabcd","sibel");
        Password2 password2_5 = new Password2(74);
        if(username5.checkUsername())
            if(password1_5.checkUserNameSpirit())
                if(password1_5.isBalancedPassword())
                    if(password1_5.checkPalindromePossible())
                        if(password2_5.checkPassword2(denominations))
        System.out.println("The username and passwords are valid. The door is opening, please wait...\n");

        System.out.println("Test6: \n");
        Username username6 = new Username("sibel");
        Password1 password1_6 = new Password1("[[[[]]]]","sibel");
        Password2 password2_6 = new Password2(74);
        if(username6.checkUsername())
            if(password1_6.checkUserNameSpirit())
                if(password1_6.isBalancedPassword())
                    if(password1_6.checkPalindromePossible())
                        if(password2_6.checkPassword2(denominations))
        System.out.println("The username and passwords are valid. The door is opening, please wait...\n");

        System.out.println("Test7: \n");
        Username username7 = new Username("sibel");
        Password1 password1_7 = new Password1("[no](no)","sibel");
        Password2 password2_7 = new Password2(21);
        if(username7.checkUsername())
            if(password1_7.checkUserNameSpirit())
                if(password1_7.isBalancedPassword())
                    if(password1_7.checkPalindromePossible())
                        if(password2_7.checkPassword2(denominations))
        // if all inputs are valid
        System.out.println("The username and passwords are valid. The door is opening, please wait...\n");

        System.out.println("Test8: \n");
        Username username8 = new Username("sibel");
        Password1 password1_8 = new Password1("[rac()ecar]]","sibel");
        Password2 password2_8 = new Password2(74);
        if(username8.checkUsername())
            if(password1_8.checkUserNameSpirit())
                if(password1_8.isBalancedPassword())
                    if(password1_8.checkPalindromePossible())
                        if(password2_8.checkPassword2(denominations))
        // if all inputs are valid
        System.out.println("The username and passwords are valid. The door is opening, please wait...\n");

        System.out.println("Test9: \n");
        Username username9 = new Username("sibel");
        Password1 password1_9 = new Password1("[rac()ecars]","sibel");
        Password2 password2_9 = new Password2(74);
        if(username9.checkUsername())
            if(password1_9.checkUserNameSpirit())
                if(password1_9.isBalancedPassword())
                    if(password1_9.checkPalindromePossible())
                        if(password2_9.checkPassword2(denominations))
        // if all inputs are valid
        System.out.println("The username and passwords are valid. The door is opening, please wait...\n");

        System.out.println("Test10: \n");
        Username username10 = new Username("sibel");
        Password1 password1_10 = new Password1("[rac()ecar]","sibel");
        Password2 password2_10 = new Password2(5);
        if(username10.checkUsername())
            if(password1_10.checkUserNameSpirit())
                if(password1_10.isBalancedPassword())
                    if(password1_10.checkPalindromePossible())
                        if(password2_10.checkPassword2(denominations))
        // if all inputs are valid
        System.out.println("The username and passwords are valid. The door is opening, please wait...\n");

        System.out.println("Test11: \n");
        Username username11 = new Username("sibel");
        Password1 password1_11 = new Password1("[rac()ecar]","sibel");
        Password2 password2_11 = new Password2(35);
        if(username11.checkUsername())
            if(password1_11.checkUserNameSpirit())
                if(password1_11.isBalancedPassword())
                    if(password1_11.checkPalindromePossible())
                        if(password2_11.checkPassword2(denominations))
        // if all inputs are valid
        System.out.println("The username and passwords are valid. The door is opening, please wait...\n");
    }
    
}