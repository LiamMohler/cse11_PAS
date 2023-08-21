/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file (User.java) will create a user object with posts, karma, and a 
 * username.
 */
import java.util.ArrayList;

/**
 * This class will simulate a user on redit, where they will have a username, 
 * karma, posts, and what they have upvoted and downvoted as a user.
 */
public class User {
    //user vars
    /* Constants (Magic numbers) */
    private static final String USLASH = "u/";
    private static final String KARMAFORMAT = " Karma: ";
    private int karma;
    private String username;
    private ArrayList<Post> posts;
    private ArrayList<Post> upvoted;
    private ArrayList<Post> downvoted;

    /**
     * Contructor for User when passed in a username. Will initalize values.
     *
     * @param username - the username of the User
     */
    public User(String username){
        //initialize vars
        karma = 0;
        this.username = username;
        posts = new ArrayList<Post>();
        upvoted = new ArrayList<Post>();;
        downvoted = new ArrayList<Post>();
    }
    /**
     * will add a post to the list of the user's posts.
     *
     * @param post - the post you want to add
     */
    public void addPost(Post post){
        //if null dont add
        if(post == null){
            return;
        }
        //add post and update karma
        posts.add(post);
        updateKarma();
    }
    /**
     * Will update the karma for the user by looking at all their posts.
     *
     */
    public void updateKarma(){
        karma = 0;
        for (Post p : posts) {
            //loop through posts and add to karma
            karma += p.getUpvoteCount();
            karma -= p.getDownvoteCount();
        }
    }
    /**
     * Will get the users karma and return it
     *
     * @return - will return the karma of the user
     */
    public int getKarma(){
        //ret karma
        return karma;
    }
    /**
     * Will upvote the passed in post.
     *
     * @param post - post that want to upvote
     */
    public void upvote(Post post){
        //if null or is their post, or already upvoted then return
        if(post == null || posts.contains(post) || upvoted.contains(post))
            return;
        if(post.getAuthor() == this)
            return;
        //if it is downvoted then remove from downvoed
        if(downvoted.contains(post)){
            downvoted.remove(post);
            post.updateDownvoteCount(false);
        }
        //add to upvoted
        upvoted.add(post);
        post.updateUpvoteCount(true);
        post.getAuthor().updateKarma();
    }
    /**
     * Will downvote a certain post.
     *
     * @param post - the post that want to downvote
     */
    public void downvote(Post post){
        //if null or alrady downvoted or is their post then return
        if(post == null || downvoted.contains(post) || posts.contains(post))
            return;
        if(post.getAuthor() == this)
            return;
        // if the post is already upvoted then remove
        if(upvoted.contains(post)){
            upvoted.remove(post);
            post.updateUpvoteCount(false);
        }
        //downvote the post
        downvoted.add(post);
        post.updateDownvoteCount(true);
        post.getAuthor().updateKarma();
    }
    /**
     * Will get the user's top post
     *
     * @return - will return the users top post w most upvotes
     */
    public Post getTopPost(){
        Post curTop = null;

        //loop through the posts
        for(Post p : posts){
            if(p.getReplyTo() == null){
                //is original post
                // if it is first look then set it to the curTop
                if(curTop == null){
                    curTop = p;
                }
                else{
                    //if it is not first time then we want to check if it is 
                    //greater than the curTop, if ti is then update curTop/
                    if( (p.getUpvoteCount()-p.getDownvoteCount()) > 
                        (curTop.getUpvoteCount() - curTop.getDownvoteCount())){
                        curTop = p;
                    }
                }
            }
        }
        //ret curTop
        return curTop;
    }
    /**
     * Will get the top comment on a post (going by upvotes)
     *
     * @return - will return the top comment
     */
    public Post getTopComment(){
        Post curTop = null;
        //loop through posts
        for(Post p : posts){
            //if it is a reply to (comment)
            if(p.getReplyTo() != null){
                //is original post
                if(curTop == null){
                    curTop = p;
                }
                else{
                    //check if it is greater or less than if it is then change
                    if( (p.getUpvoteCount()-p.getDownvoteCount()) > 
                        (curTop.getUpvoteCount() - curTop.getDownvoteCount())){
                        curTop = p;
                    }
                }
            }
        }
        //return curTop
        return curTop;
    }
    /**
     * Will return the posts of a user
     *
     * @return - returns a users posts
     */
    public ArrayList<Post> getPosts(){
        return posts;
    }
    /**
     * Will return the user's username and karma in a string format.
     * 
     * @return - the username and karma in a format
     */
    public String toString(){
        return (USLASH + username + KARMAFORMAT + karma);
    }
}