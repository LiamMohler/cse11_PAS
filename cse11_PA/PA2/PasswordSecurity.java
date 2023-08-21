// Name : Liam Mohler
// Email : lmohler@ucsd.edu
// PID : A17432488
// Sources Used : String Builder java doc - https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
// 
// This file is used to comput and assess a passwords strenth,
// then suggest a new password if the one entered is not strong enough.


// scanner import
import java.util.Scanner;



// The class PasswordSecurity will take in a users password as an input and assess the strength of the
// inputted password if it is of length 8 or greater. Then if it is not considered strong it will suggest 
// a stronger password by adding to and modifying the password that the user input.
public class PasswordSecurity{

    private static final String PW_PROMPT = "Please enter a password: ";
    private static final String PW_TOO_SHORT = "Password is too short";
    private static final String PW_VERY_WEAK = "Password strength: very weak";
    private static final String PW_WEAK = "Password strength: weak";
    private static final String PW_MEDIUM = "Password strength: medium";
    private static final String PW_STRONG = "Password strength: strong";
    private static final String SUGGESTION_PROMPT = "Here is a suggested stronger password: ";
    private static final String LETTER_RULE_SUGGESTION = "Cse";
    private static final String SYMBOL_RULE_SUGGESTION = "@!";

    private static final int MIN_PW_LENGTH = 8;
    private static final int VERY_WEAK_THRESHOLD = 1;
    private static final int WEAK_THRESHOLD = 2;
    private static final int MEDIUM_THRESHOLD = 3;
    private static final int STRONG_THRESHOLD = 4;
    private static final int LETTER_COUNT_THRESHOLD = 2;
    private static final int DIGIT_INTERVAL = 5;
    private static final int MOD_FACTOR = 10;
    private static final int FIRST_INDEX = 0;
    private static final int LETTER_UPPER_LOWER_COUNT_THRESHOLD = 1;
    private static final int MOD_NUMBER = 4;



    
     public static void main(String[] args){
        
        //this is the main method - it will take in a users passward then evaluate its strength, suggesting a new 
        //password if not strong enough 

        // The scanner "input" will take in the users input then store it into the String "origPass" then "origPass"
        // will be stored into the StringBuilder "userPass"
        // The booleans "lower" "upper" "number" and "symbol" all keep track of whether or not the password contains these certain attributes  
        // The int "countTrue" will keep track of how many of the booleans are true (how many of the attributes the inputted password contains)
        // The int "numLower" and "numUpper" will keep track of how many lower and upper characters the password contains
        // the char "highestLowerCase" will keep track of the highest lower case letter in the inputted password.


        // ask for user input then store into vars
        System.out.print(PW_PROMPT);
        Scanner input = new Scanner(System.in);
        String origPass = input.nextLine();
        StringBuilder userPass = new StringBuilder(origPass);
        input.close();


        // create variables to assess code
        boolean lower = false;
        boolean upper = false;
        boolean number = false;
        boolean symbol = false;
        int countTrue = 0;
        int numLower = 0;
        int numUpper = 0;
        char highestLowerCase = 0;


        // if statement will check to make sure the inputted string is long enough, if not it will go to the else statement where
        // it says the password is too short.
        if(userPass.toString().length() >= MIN_PW_LENGTH){
            // use a for each loop to check every charcter's ASCII value and set/change variables according to 
            // the different characters in userPass
            for(char c : userPass.toString().toCharArray()){
                if (Character.isUpperCase(c)) {upper = true; ++numUpper;}
                else if(Character.isLowerCase(c)) {
                    lower = true; 
                    ++numLower;
                    if(c > highestLowerCase){
                        highestLowerCase = c;
                    }
                }
                else if(Character.isDigit(c)) {number = true;}
                else {symbol = true;}
            }


            //will check how many of the different attributes the password contains and add it to the var "countTrue"
            if(upper) ++countTrue;
            if(lower) ++countTrue;
            if(number) ++countTrue;
            if(symbol) ++countTrue;


            // will tel the user how strong password is, if it is strong will exit the method.
            if(countTrue == STRONG_THRESHOLD){ System.out.println(PW_STRONG); return;}
            else if(countTrue == MEDIUM_THRESHOLD) System.out.println(PW_MEDIUM);
            else if(countTrue == WEAK_THRESHOLD) System.out.println(PW_WEAK);
            else if (countTrue == VERY_WEAK_THRESHOLD) System.out.println(PW_VERY_WEAK);


            // these statements will edit the suggested password using rules #1-3 of the assignment
            if(numLower + numUpper < LETTER_COUNT_THRESHOLD){
                //add Cse to front
                userPass.insert(FIRST_INDEX, LETTER_RULE_SUGGESTION);
            }
            else if (numLower < LETTER_UPPER_LOWER_COUNT_THRESHOLD){
                //make first lower - GOT TO MAKE SURE IT IS NOT A NUMBER - to do so I loop through list to find first instance of 
                //upper case latter
                int firstIndexLower = 0;
                for(int i = 0;i<userPass.toString().length();++i){
                    if(Character.isUpperCase(userPass.charAt(i))){
                        firstIndexLower = i;
                        break;
                    }
                }
                userPass.replace(firstIndexLower, firstIndexLower+1, 
                userPass.toString().substring(firstIndexLower, 
                firstIndexLower+1).toLowerCase()); 
            }
            else if(numUpper < LETTER_UPPER_LOWER_COUNT_THRESHOLD){
                //make highest ASCII upper (last instance of it) (CANNOT BE FIRST)
                userPass.replace(userPass.toString().lastIndexOf(highestLowerCase),
                userPass.toString().lastIndexOf(highestLowerCase)+1,
                userPass.toString().substring(userPass.toString().lastIndexOf(highestLowerCase),
                userPass.toString().lastIndexOf(highestLowerCase) +1).toUpperCase()); 
            }


            // rule #4 of the assignment
            if(!number){
                int insertNum = origPass.length() % MOD_FACTOR;
                String curPass = userPass.toString();
                // add insetNum (k) every 4 letters
                for(int i = 4;i<userPass.toString().length();i+=DIGIT_INTERVAL){
                    userPass.insert(i, insertNum);
                }
                // add insetNum(k) if origPass divisible by 4
                if(curPass.length() % MOD_NUMBER == 0){
                    userPass.append(insertNum);
                }
            }


            // rule #5 of the assignment
            if(!symbol){
                // add "@!" to end if no symbols
                userPass.append(SYMBOL_RULE_SUGGESTION);
            }
            // print the suggested password
            System.out.println(SUGGESTION_PROMPT + userPass.toString());
        }


        else{
            //print password is too short if length < 8
            System.out.println(PW_TOO_SHORT);
        }

     }
}