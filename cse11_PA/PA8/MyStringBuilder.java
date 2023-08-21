/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file will use nodes like a linked list and create a string by 
 * connecting them.
 */

/**
 * This class will simulate a string builder through the use of nodes, kinda 
 * like a linked list.
 */
public class MyStringBuilder {
    //post vars
    /* Constants (Magic numbers) */

    private CharNode start;
    private CharNode end;
    private int length;


    /**
     * Constructor that takes in a char - will create a MyStringBuilder obj.
     * 
     * @param ch - the character to make it on
     */
    public MyStringBuilder(char ch) {
        start = new CharNode(ch);
        end = start;
        length = 1;
    }

    /**
     * Constructor that takes in a string
     * 
     * @param str - the string that the MyStringBuilder obj will be based off.
     */
    public MyStringBuilder(String str) {
        try{
            length = str.length();
            if(length == 0){
                return;
            }
            if(length == 1){
                start = new CharNode(str.charAt(0));
                end = start;
                return;
            }
            CharNode thisBuilder = new CharNode(str.charAt(0));
            start = thisBuilder;
            for(int i = 1; i < str.length(); i++){
                thisBuilder.setNext(new CharNode(str.charAt(i)));
                thisBuilder = thisBuilder.getNext();
            }
            end = thisBuilder;
            
        }
        catch(Exception e){
            throw new NullPointerException();
        }
    }

    /**
     * Constructor that takes in a MyStringBuilder obj.
     * 
     * @param other - the MyStringBuilder obj to copy.
     */
    public MyStringBuilder(MyStringBuilder other) {
        try{
            length = other.length;
            if(length == 0){
                return;
            }
            if(length == 1){
                start = new CharNode(other.start.getData());
                end = start;
                return;
            }
            CharNode last = other.start;
            CharNode thisBuilder = new CharNode(last.getData());
            start = thisBuilder;
            while (last.getNext() != null) {
                thisBuilder.setNext(new CharNode(last.getNext().getData()));
                thisBuilder = thisBuilder.getNext();
                last = last.getNext();
            }
            end = thisBuilder;
        }
        catch(Exception e){
            throw new NullPointerException();
        }
    }

    /**
     * Will return the length of the object.
     * 
     * @return will return the length of the MyStringBuilder obj.
     */
    public int length() {
        return length;
    }

    /**
     * Will apend a character to the end of the object.
     * 
     * @param ch - the character to append
     * @return - returns the object
     */
    public MyStringBuilder append(char ch) {
        if(length == 0){
            start = new CharNode(ch);
            end = start;
            length = 1;
            return this;
        }
        CharNode newLast = new CharNode(ch);
        end.setNext(newLast);
        end = newLast;
        length++;
        return this;
    }

    /**
     * Will apend a string to the end of the object.
     * 
     * @param str - the string to append
     * @return - returns the object
     */
    public MyStringBuilder append(String str) {
        if(length == 0){
            length = str.length();
            CharNode thisBuilder = new CharNode(str.charAt(0));
            start = thisBuilder;
            for(int i = 1; i < str.length(); i++){
                thisBuilder.setNext(new CharNode(str.charAt(i)));
                thisBuilder = thisBuilder.getNext();
            }
            end = thisBuilder;
            return this;
        }
        for(char c : str.toCharArray()){
            append(c);
        }
        return this;
    }

    /**
     * Will turn the MyStringBuilder object into string form and return it.
     * 
     * @return - returns the string version
     */
    public String toString() {
        if(length == 0){
            return "";
        }
        CharNode last = start;
        String retVal = "" + start.getData();
        while(last.getNext() != null){
            retVal = retVal + last.getNext().getData();
            last = last.getNext();
        }
        return retVal;
    }

    /**
     * Will take a substring of the MyStringBuilder object 
     * 
     * @param startIdx - the start index
     * @return - returns the substring in string form
     */
    public String subString(int startIdx) {
        if(startIdx < 0 || startIdx >= length){
            throw new IndexOutOfBoundsException();
        }
        CharNode last = start;
        String retVal = "";
        if(length == 0){
            return retVal;
        }
        else if(startIdx == 0){
            retVal = retVal + last.getData();
        }
        int indexCount = 1;
        while(last.getNext() != null){
            if(indexCount >= startIdx)
                retVal = retVal + last.getNext().getData();
            indexCount++;
            last = last.getNext();
        }
        return retVal;
    }

    /**
     * Will take a substring of the MyStringBuilder object 
     * 
     * @param startIdx - the start index
     * @param endIdx - the end index
     * @return - returns the substring in string form
     */
    public String subString(int startIdx, int endIdx) {
        if(startIdx < 0 || startIdx >= length 
            || endIdx < startIdx || endIdx > length){
            throw new IndexOutOfBoundsException();
        }
        CharNode last = start;
        String retVal = "";
        if(startIdx == endIdx || length == 0){
            return retVal;
        }
        else if(startIdx == 0){
            retVal = retVal + last.getData();
        }
        int indexCount = 1;
        while(last.getNext() != null){
            if(indexCount >= startIdx && indexCount < endIdx)
                retVal = retVal + last.getNext().getData();
            indexCount++;
            last = last.getNext();
        }
        return retVal;
    }
}