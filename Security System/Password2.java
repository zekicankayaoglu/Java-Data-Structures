public class Password2 {
    public int password2;
    /**
     * creates password2 object
     * @param password2
     */
    public Password2(int password2){
        this.password2 = password2;
    }

    /**
     * checks the password number valid or not
     * @param denominations gets denominations array as parameter
     * @return if it is invalid false if not returns main method of password2 control
     */
    public boolean checkPassword2(int denominations[]){
        if(this.password2 < 10 || this.password2 > 10000){
            System.out.println("The password2 is invalid. It should be between 10 and 1000.\n");
            return false;
        }
        if(isExactDivision(password2,denominations,0)) return true;
        else{
            System.out.println("The password2 is invalid. It is not compatible with the denominations.\n");
            return false;
        } 

    }
    /**
     * checks the password is exact division to denomination elements
     * @param password the password
     * @param denominations the denominations array
     * @param index first comes as 0 then increase 1 in each call
     * @return
     */
    private boolean isExactDivision(int password,int[] denominations,int index)
    {
        if(password == 0){ //if passsword remains 0 it returns true
            return true;
        }
        if(index == denominations.length){//if index becomes bigger than length and if password is not 0 it returns false
            
            return false;
        }
        int coefficient = password / denominations[index];
        //starts from first element of denominations and divide password into this element's number
        for(int i = 0; i <= coefficient;i++){
            int tempPassword = password - i * denominations[index];
            if(isExactDivision(tempPassword,denominations,index + 1)){
                return true;
            }
        }
        return false;
    }
}
