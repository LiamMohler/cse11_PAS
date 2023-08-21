/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file (Calculator.java) acts as a calculator, allowing for the
 * addition and multiplication of numbers.
 * In addition to this, it is able to add/remove zeros and 
 * extract numbers.
 */


 /**
 * This class will add/multiply strings together as if they were doubles.
 * It will also strip zeros, extract numbers, and add zeros.
 */
public class Calculator{
    /** Constants (Magic Numbers) */
    // numbers, characters, and strings that care used in if statements and 
    // logic throughout the code.
    public static final int NINE = 9;
    public static final int TEN = 10;
    public static final char CHARPERIOD = '.';
    public static final char CHARZERO = '0';
    public static final String STRPERIOD = ".";
    public static final String STRZERO = "0";
    public static final String STRPOINTZERO = ".0";
    public static final String STRZEROPOINTZERO = "0.0";

public static void main(String[] args) {
    System.out.println(stripZeros("000123000.00000"));
}



    /**
     * This method will extract the whole number portion of the inputted string
     * of a number. 
     * 
     * @param number  - the number in the form of a str that will be extracted
     * @return - the whole number
     */

    public static String extractWholeNumber(String number){
        int index = number.indexOf(CHARPERIOD);
        // if first char is '.', then there is no whole #
        if(index == 0){
            return STRZERO;
        }
        // if no '.' then will return all back bc it is all whole #
        else if(index == -1){
            return(number);
        }
        // otherwise then we will return from left to the .
        else{
            return (number.substring(0, index));
        }
    }

    /**
     * This method will extract the decimal portion of the inputted string
     * of a number. 
     * 
     * @param number  - the number in the form of a str that will be extracted
     * @return - the decimal
     */

    public static String extractDecimal(String number){
        int index = number.indexOf(CHARPERIOD);
        // if there is no '.' then there is no decimal
        if(index == -1)
            return STRZERO;
        // if there is nothing after . then we will want to return 0
        if(index+1 == number.length())
            return STRZERO;
        // return all str after the period
        return (number.substring(index+1));
    }


    /**
     * This method will add a specified ammount of zeros in front of the 
     * inputted number as a string.
     * 
     * @param number  - the number in the form of a str
     * @param numZeros - the number of zeros to be prepended
     * @return - the number with the zeros added to front
     */


    public static String prependZeros(String number, int numZeros){
        //while still need to add zeros, we will loop
        while(numZeros > 0){
            // add to front of str
            number = STRZERO + number;
            numZeros--;
        }
        return number;
    }
    
    /**
     * This method will add a specified ammount of zeros at the end of the 
     * inputted number as a string.
     * 
     * @param number  - the number in the form of a str
     * @param numZeros - the number of zeros to be prepended
     * @return - the number zeros appended to it
     */

    public static String appendZeros(String number, int numZeros){
        //while still need to add zeros, we will loop
        while(numZeros > 0){
            // add to end of str
            number = number + STRZERO;
            numZeros--;
        }
        return number;
    }

    /**
     * This method will strip the extra zeros that are either before the 
     * whole number or after the decimal.
     * 
     * @param number  - the number in the form of a str
     * @return - the number without zeros before or after
     */

    public static String stripZeros(String number){
        StringBuilder tempNum = new StringBuilder(number);
        //will loop through removing the first character if it is a zero,
        //until the first character is not a zero.
        boolean isTrue = false;
        while(true){
            if(tempNum.indexOf(STRZERO) == 0){
                tempNum.delete(0, 1);
                isTrue = true;
            } 
            //will break if it is not a zero
            else{
                break;
            }
        }
        // if the last character if a period, then can return do not need
        // to go from right to left
        if(number.lastIndexOf(STRPERIOD) == number.length()-1){
            return tempNum.toString();
        }

        //will go from right to left and remove zeros until it reaches a num
        //or period
        while(true){
            if(number.indexOf(CHARPERIOD) != -1 && 
            tempNum.lastIndexOf(STRZERO) == tempNum.toString().length()-1){
                tempNum.delete(tempNum.toString().length()-1, 
                        tempNum.toString().length());
            } 
            else{
                break;
            }
            if(tempNum.toString().equals(STRPERIOD) && !isTrue)
                return STRPOINTZERO;
            else if(tempNum.toString().equals(STRPERIOD) && isTrue)
                return STRZEROPOINTZERO;
        }
        number = tempNum.toString();
        //if there is nothing then ret 0
        if(number.equals(STRPERIOD) || number.equals(""))
            return STRZERO;
        //if the last letter is 0, then append 0
        if(number.lastIndexOf(STRPERIOD) == number.length()-1){
            number = appendZeros(number, 1);
        }
        if(isTrue && number.charAt(0) == CHARPERIOD){
            return prependZeros(number, 1);
        }
        return number;
    }


    /**
     * This method will add the firstDigit, secondDigit, and carryIn together,
     * and return the number.
     * 
     * @param firstDigit  - the first digit to add in the form of a char
     * @param secondDigit  - the second digit to add in the form of a char
     * @param carryIn  - whether or not a one has been carried over
     * @return - the two digits added together
     */

    public static char addDigits
            (char firstDigit, char secondDigit, boolean carryIn){
        int addNum = 0;
        int firstDig = firstDigit - CHARZERO;
        int secondDig = secondDigit - CHARZERO;
        if(carryIn){
            addNum = 1;
        }
        //if the three are > 9, then want to return the number - 10
        if(firstDig + secondDig + addNum > NINE){
            int temp = firstDig + secondDig + addNum - TEN;
            return ((char)(CHARZERO + temp)); 
        }
        //if the three are < 9, then want to return the number
        else{
            int temp = firstDig + secondDig + addNum;
            return ((char)(CHARZERO + temp));
        }
    }


    /**
     * This method will add the firstDigit, secondDigit, and carryIn together,
     * and return whether or not it will carry over.
     * 
     * @param firstDigit  - the first digit to add in the form of a char
     * @param secondDigit  - the second digit to add in the form of a char
     * @param carryIn  - whether or not a one has been carried over
     * @return - true if carryout, false if not
     */

    public static boolean carryOut
            (char firstDigit, char secondDigit, boolean carryIn){
        int addNum = 0;
        int firstDig = firstDigit - CHARZERO;
        int secondDig = secondDigit - CHARZERO;
        if(carryIn){
            addNum = 1;
        }
        // if all 3 combined > 9, then will carry a 1 over, return true
        if(firstDig + secondDig + addNum > NINE){
            return true;
        }
        //otherwise return false
        else
            return false;
    }
    
    /**
     * This method will return the length of an inputted string.
     * 
     * @param number - the string that will be evaluated.
     * @return - the length of number
     */

    public static int getLength(String number){
        return number.length();
    }

    /**
     * This method will add the two numbers inputted and return the sum by
     * using the carryOut and addDigits methods.
     * 
     * @param firstNumber - the first string to be added
     * @param secondNumber - the second string to be added
     * @return - the two added numbers
     */

    public static String add(String firstNumber, String secondNumber){
        // get the string in the same format (make sure both have decimal)
        StringBuilder first = new StringBuilder(stripZeros(firstNumber));
        StringBuilder second = new StringBuilder(stripZeros(secondNumber));
        first = new StringBuilder (addDecimalZero(first.toString()));
        second = new StringBuilder(addDecimalZero(second.toString()));
        StringBuilder addedNums = new StringBuilder("");

        //this will make sure add zeros to make sure it is the same length
        if(getLength(extractWholeNumber(first.toString())) > 
                getLength(extractWholeNumber(second.toString()))){
            second = new StringBuilder(prependZeros(second.toString(), 
            getLength(extractWholeNumber(first.toString())) - 
                getLength(extractWholeNumber(second.toString()))));
        }
        //this will make sure add zeros to make sure it is the same length
        else if(getLength(extractWholeNumber(first.toString())) < 
                getLength(extractWholeNumber(second.toString()))){
            first = new StringBuilder(prependZeros(first.toString(), 
            getLength(extractWholeNumber(second.toString())) - 
                getLength(extractWholeNumber(first.toString()))));
        }
        //this will make sure add zeros to make sure it is the same length
        if(getLength(extractDecimal(first.toString())) > 
                getLength(extractDecimal(second.toString()))){
            second = new StringBuilder(appendZeros(second.toString(), 
            getLength(extractDecimal(first.toString())) - 
                getLength(extractDecimal(second.toString()))));
        }
        //this will make sure add zeros to make sure it is the same length
        else if(getLength(extractDecimal(first.toString())) < 
                getLength(extractDecimal(second.toString()))){
            first = new StringBuilder(appendZeros(first.toString(), 
            getLength(extractDecimal(second.toString())) - 
                getLength(extractDecimal(first.toString()))));
        }
        boolean carryIn = false;
        //this will loop through all the characters and use the addDigits and 
        //carryOut methods to add the two strings together.
        for(int i = first.length()-1; i >= 0; i--){
            if(first.charAt(i) == '.'){
                addedNums.insert(0, first.charAt(i));
            }
            else{
                addedNums.insert(0, addDigits(first.charAt(i), 
                    second.charAt(i), carryIn));
                carryIn = 
                    carryOut(first.charAt(i), second.charAt(i), carryIn);
            }
        }
        //if at the end one more carry in, then add it at the end
        if(carryIn){
            addedNums.insert(0, 
                    addDigits(CHARZERO, CHARZERO, carryIn));
        }
        addedNums = new StringBuilder(stripZeros(addedNums.toString()));
        addedNums = new StringBuilder(addDecimalZero(addedNums.toString()));
        return(addedNums.toString());
    }

    /**
     * This function will manipulate the inputted string and return it with 
     * added 0s and periods.
     * 
     * @param number - the number to manipulate
     * @return - the manipulated number
     */

    private static String addDecimalZero(String number){
        StringBuilder newNum = new StringBuilder(number);
        //if the first character is a period, then add a zero before
        if(newNum.charAt(0) == CHARPERIOD){
            newNum = new StringBuilder
                    (prependZeros(newNum.toString(), 1));
        }
        //if the last character is a period, then add a zero
        else if(newNum.charAt(newNum.toString().length()-1) == CHARPERIOD){
            newNum.append(CHARZERO);
        }
        // if there is no period, then add a decimal zero
        else if(newNum.toString().indexOf(CHARPERIOD) == -1){
            newNum.append(STRPOINTZERO);
        }
        return(newNum.toString());
    }

    /**
     * This function will multiply the inputted string number by the int 
     * numtries.
     * 
     * @param number - the number that is being multiplied
     * @param numTimes - the ammount to multiply by
     * @return - the number multiplied by numTimes
     */

    public static String multiply(String number, int numTimes){
        number = addDecimalZero(number);
        String output = number;
        //if mult by 0 then return 0
        if(numTimes == 0){
            return STRZEROPOINTZERO;
        }
        //if mult by 1 then return the number
        else if(numTimes == 1){
            return output;
        }
        //loop through the tries and use add method for however many times
        while(numTimes-1 > 0){
            output = add(number, output);
            numTimes--;
        }
        return output;
    }
}