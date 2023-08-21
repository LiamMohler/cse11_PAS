/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file (Post.java) will great a post object with a title, content, 
 * author, upvote/downvote, and the reply to.
 */
import java.util.ArrayList;

/**
 * This class will simulate a post on redit, where it will have a title, 
 * author, upvote/downvote count, and content.
 */
public class Post {
    //post vars
    /* Constants (Magic numbers) */
    private static final String OPENBRACKET = "[";
    private static final String CLOSEBRACKET = "]";
    private static final String BACKSLASHT = "\t";
    private static final String LINE = "|";
    private static final String BACKSLASHNBACKSLASHT = "\n\t";
    private String title;
    private String content;
    private Post replyTo;
    private User author;
    private int upvoteCount;
    private int downvoteCount;

    /**
     * Will create a post object w title , content, and an author (orig post)
     * 
     * @param title - the title of the post
     * @param content - the content of the post
     * @param author - the author of the post.
     */
    public Post(String title, String content, User author){
        //initalize vars
        this.downvoteCount = 0;
        this.upvoteCount = 1;
        this.title = title;
        this.content = content;
        this.author = author;
    }
    /**
     * Will create a post object w content, replyTo, and an author. (comment)
     * 
     * @param content - the content of the post
     * @param replyTo - the post that it is replying to
     * @param author - the author of the post.
     */
    public Post(String content, Post replyTo, User author){
        // initialize vars
        this.content = content;
        this.replyTo = replyTo;
        this.author = author;
        this.downvoteCount = 0;
        this.upvoteCount = 1;
    }
    /**
     * Will return the title of the post.
     * 
     * @return - will reutrn the title of the post
     */
    public String getTitle(){
        //title
        return this.title;
    }
    /**
     * Will return the post that the current post is replying to.
     * 
     * @return - will return the post that it is replying to
     */
    public Post getReplyTo(){
        return this.replyTo;
        //return the post this is replying to
    }
    /**
     * Will return the author of a post
     * 
     * @return - will return the author of a post
     */
    public User getAuthor(){
        //author
        return this.author;
    }
    /**
     * Will return the upvote count for a certain post
     * 
     * @return - will reutrn the upvote count
     */
    public int getUpvoteCount(){
        //ret upvote
        return this.upvoteCount;
    }
    /**
     * Will return downvote count of a post
     * 
     * @return - downvote count
     */
    public int getDownvoteCount(){
        return this.downvoteCount;
    }
    /**
     * Will update the upvote count of a post
     * 
     * @param isIncrement - whethor or not to add or subtract
     */
    public void updateUpvoteCount(boolean isIncrement){
        //check isincrement
        if(isIncrement)
            ++upvoteCount;
        else
            --upvoteCount;
    }
    /**
     * Will update the downvote count of a post
     * 
     * @param isIncrement - whethor or not to add or subtract
     */
    public void updateDownvoteCount(boolean isIncrement){
        //check isincrement
        if(isIncrement)
            ++downvoteCount;
        else
            --downvoteCount;
    }

    /**
     * Will return the thread of all the posts above a certain post, that it is
     * replying to
     * 
     * @return - the arraylist of posts
     */
    public ArrayList<Post> getThread(){
        ArrayList<Post> posts = new ArrayList<>();
        //loop through the reply to, then return backwards
        //add to front of array list each time
        Post curpost = this;
        while(curpost.getReplyTo() != null){
            posts.add(0,curpost.getReplyTo());
            curpost = curpost.getReplyTo();
        }
        posts.add(this);
        return posts;
    }
    /**
     * Will return a string in format of how a post or comment would be,
     * depends on the current instance of the method.
     * 
     * @return - the string form of the post/comment
     */
    public String toString(){
        if(replyTo == null){
            return(stringFormat(true));
        }
        else{
            return(stringFormat(false));
        }
    }
    /**
     * Will make the string that will be posted and return it to toString.
     * 
     * @param isPost - whether or not the object is a post.
     * @return - the string form of the post/comment
     */
    private String stringFormat(boolean isPost){
        if(isPost){
            String TO_STRING_POST_FORMAT = OPENBRACKET + upvoteCount + LINE + 
                downvoteCount + CLOSEBRACKET + BACKSLASHT + title  + 
                BACKSLASHNBACKSLASHT + content;
            return TO_STRING_POST_FORMAT;
        }
        else{
            String TO_STRING_COMMENT_FORMAT = OPENBRACKET + upvoteCount + 
                LINE + downvoteCount + CLOSEBRACKET + BACKSLASHT + content;
            return TO_STRING_COMMENT_FORMAT;
        }
    }


}