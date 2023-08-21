public class Teser {
    public static void main(String[] args) {
        MyStringBuilder msb1 = new MyStringBuilder("ones");
        MyStringBuilder msb2 = new MyStringBuilder(msb1);
        System.out.println(msb2.length());
    }
}
