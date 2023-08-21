/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file (Tester.java) will run tests on the user and post classes.
 */

/**
 * This class will test both the User and Post files to make sure they are
 * working as intended.
 */
public class Tester {
    /**
     * Will create user and post objects, testing their code.
     *
     * @param args - the arg that is passed in
     */
    private static final String USERNAMEONE = "liam";
    private static final String USERNAMETWO = "ray";
    private static final String TITLEONE = "first";
    private static final String CONTENTONE = "second third";
    private static final String CONTENTTWO = "no?";
    private static final String CONTENTTHREE = "yes?";
    public static void main(String[] args) {
        //create users
        User liam = new User(USERNAMEONE);
        User ray = new User(USERNAMETWO);
        //create posts
        liam.addPost(new Post(TITLEONE, CONTENTONE, liam));
        ray.addPost(new Post(CONTENTTWO, liam.getTopPost(), ray));
        //check downvote
        ray.downvote(liam.getTopPost());
        //add another post
        liam.addPost(new Post(CONTENTTHREE, ray.getPosts().get(0), 
            liam));
        //get posts
        liam.getPosts();
        //upvote
        liam.upvote(ray.getPosts().get(0));
        //get karma
        liam.getKarma();
        //downvote a post already upvoted
        liam.downvote(ray.getPosts().get(0));
        //get comments
        liam.getTopComment();
        ray.getTopComment();
        //get threads
        ray.getTopComment().getThread();

    }
}
