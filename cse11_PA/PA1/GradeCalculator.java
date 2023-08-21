import java.util.Scanner;
public class GradeCalculator{
    public static void main(String args[]){


        Scanner input = new Scanner(System.in);
        int numAssignments = input.nextInt();
        int count = numAssignments;
        int programmingSum = 0;
        boolean invalid = false;

        if(numAssignments > 0){
            while(count > 0){
                int curAdd = input.nextInt();
                if(curAdd > 100 || curAdd <0){
                    invalid = true;
                    break;
                }
                programmingSum += curAdd;
                count--;
            }
        }
        else{
            invalid = true;
        }

        int mid = input.nextInt();
        int fin = input.nextInt();
        input.close();
        if(mid > 100 || mid < 0 || fin > 100 || fin < 0){
            invalid = true;
        }
        

        if(!invalid){
            double averagePA = (double)programmingSum/(double)numAssignments;
            double finalScore = (averagePA * .5) + (mid * .125) + (fin * .375);
            System.out.println(finalScore);
            if(finalScore >=90){
                System.out.println("A");
            }
            else if(finalScore >= 80){
                System.out.println("B");
            }
            else if(finalScore >= 70){
                System.out.println("C");
            }
            else if(finalScore >= 60){
                System.out.println("D");
            }
            else{
                System.out.println("F");
            }
        }
        else{
            System.out.println("invalid input");
        }
    }
}