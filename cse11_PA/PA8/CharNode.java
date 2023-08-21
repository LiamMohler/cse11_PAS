/**
 * This file is for use in CSE 11 PA 8: MyStringBuilder.
 * 
 * DO NOT MODIFY THIS FILE.
 */

/**
 * This class represents a character in our MyStringBuilder data structure. 
 * Stores a next pointer (null if non-existent) and the char represented. 
 */
public class CharNode {
    private CharNode next;
    private char data;

    /**
     * Creates a CharNode object that represents the passed-in char. 
     *
     * @param  ch The char represented by this node. 
     */
    public CharNode(char ch) {
        data = ch;
    }

    /**
     * Returns the char represented by this CharNode. 
     *
     * @return The char stored in this node. 
     */
    public char getData() {
        return data;
    }

    /**
     * Returns a direct reference to the next CharNode in the MSB. 
     * (Returns null if non-existent)
     *
     * @return The next CharNode of the MyStringBuilder. 
     */
    public CharNode getNext() {
        return next;
    }

    /**
     * Changes the data represented by this node. 
     *
     * @param  newData The new char to be stored in this node. 
     * @return This CharNode for chaining and testing.
     */
    public CharNode setData(char newData) {
        data = newData;
        return this;
    }

    /**
     * Changes the next CharNode. 
     *
     * @param  newNext The CharNode to change this node's next pointer to. 
     * @return This node for chaining and testing.
     */
    public CharNode setNext(CharNode newNext) {
        next = newNext;
        return this;
    }
}