import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Daniel Dang on 1/28/2017.
 */

/*
 * Run class
 */
public class Main {
    public static void main(String args[]){
        //Get the file name and get the initial state from the input file
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = sc.next();
        getInputs(fileName);

        System.out.print("Pick a search algorithm: ");
        int choice = sc.nextInt();

        switch(choice){
            case 1:     //bfs
                break;
            case 2:     //dfs
                break;
            case 3:     //ucs
                break;
            case 4:     //gfs
                break;
            case 5:     //A*
                break;
            default: System.out.println("Invalid choice");
        }
    }

    public static String getInputs(String fileName){
        String input = null;
        File file = new File(fileName);
        try {
            Scanner sc = new Scanner(file);
            input = sc.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return input;
    }
}
