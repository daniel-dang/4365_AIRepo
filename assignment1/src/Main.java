import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Daniel Dang on 1/28/2017.
 * Co-authored by Mavis Francia.
 */

/*
 * Run class
 */
public class Main {

    private static boolean variableCosts = false;    //true if -cost argument is included

    public static boolean hasVariableCosts() {
        return variableCosts;
    }

    public static void main(String args[]){
        //---------------------------------------------TEST CODE-------------------------------------------
        //test();
        //---------------------------------------------MAIN CODE-------------------------------------------
        String fileName = "";
        String iniState;              //initial state
        int mode = 0;
        Stack<Node> goalPath = null;

        //Get program mode from args
        //[-cost] <BFS|DFS|UCS|GS|A-star> <inputfile>
        //output error message if input is invalid

        try {
            //Check number of arguments
            if(args.length < 2 || args.length > 3)
                throw new Exception();

            //Check if cost flag is first (if included)
            if(args.length == 3 && !args[0].equals("-cost"))
                throw new Exception();

            int searchTypeArgIndex = 0; //holds index in args array that contains search type
                                        //index 0 if 2 arguments supplied

            //cost flag set and search type in index 1
            if(args.length == 3) {
                variableCosts = true;
                searchTypeArgIndex = 1;
            }

            //select search type
            if(args[searchTypeArgIndex].equalsIgnoreCase("BFS"))
                mode = 1;
            else if(args[searchTypeArgIndex].equalsIgnoreCase("DFS"))
                mode = 2;
            else if(args[searchTypeArgIndex].equalsIgnoreCase("UCS"))
                mode = 3;
            else if(args[searchTypeArgIndex].equalsIgnoreCase("GS"))
                mode = 4;
            else if(args[searchTypeArgIndex].equalsIgnoreCase("A-star"))
                mode = 5;
            else
                throw new Exception(); //invalid search type supplied

            //file name is last argument
            fileName = args[args.length - 1];

            //get input from file
            iniState = getInputs(fileName);

            //execute selected search type and get goal path
            switch(mode) {
                case 1:
                    //BFS selected
                    BFS bfs = new BFS(iniState);
                    goalPath = bfs.search();
                    break;
                case 2:
                    //DFS selected
                    DFS dfs = new DFS(iniState);
                    goalPath = dfs.search();
                    break;
                case 3:
                    //UCS selected
                    UCS ucs = new UCS(iniState);
                    goalPath = ucs.search();
                    break;
                case 4:
                    //GS selected
                    Greedy greedy = new Greedy(iniState);
                    goalPath = greedy.search();
                    break;
                case 5:
                    //A-star selected
                    AStar astar = new AStar(iniState);
                    goalPath = astar.search();
                    break;
                default:
                    System.out.println("ERROR: invalid mode");
            }

            printGoalPath(goalPath);

        }
        catch(FileNotFoundException f) {
            System.out.println("ERROR: file \"" + fileName + "\" not found.");
        }
        catch(Exception e) { // output error message
            System.out.println("ERROR: invalid arguments.");
            System.out.println("\tusage: java Main [-cost] <BFS|DFS|UCS|GS|A-star> <inputfile>");
            System.out.println("\t   ex: java Main -cost BFS input.txt");
        }
    }

    //gets input line from file
    public static String getInputs(String fileName) throws FileNotFoundException{
        //System.out.println(new File(".").getAbsoluteFile());
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        String input = sc.nextLine();
        return input;
    }

    //prints information of nodes along goal path
    public static void printGoalPath(Stack<Node> goalPath){
        int step = 0;
        //if no goal path found by search algorithm, print no solution
        if (goalPath == null){
            System.out.println("No solution.");
        }
        //else, print goal path
        //for each node, output step number, move executed, and state
        else {
            System.out.println("Step " + (step++) + ": " + goalPath.pop().getState());
            //no cost flag
            if(!variableCosts) {
                while (!goalPath.isEmpty()) {
                    Node temp = goalPath.pop();
                    System.out.println("Step " + (step++) + ": " + "Move " + temp.getMove() + " " + temp.getState());
                }
            }
            //cost flag supplied, output cost of each move as well
            else {
                while (!goalPath.isEmpty()) {
                    Node temp = goalPath.pop();
                    System.out.println("Step " + (step++) + ": " + "Move " + temp.getMove() + " " + temp.getState() +
                            " (c=" + temp.getMoveCost() + ")");
                }
            }
        }
    }

    /*
    //-------------------------------TEST METHOD------------------------------
    public static void test(){

        //----initial state----
        //String iniState = "BWWBxWBWWBB";
        //String iniState = "WXBBW";

        //----search type---
        //DFS dfs = new DFS(iniState);
        //Stack<Node> goalPath = dfs.search();

        //BFS bfs = new BFS(iniState);
        //Stack<Node> goalPath = bfs.search();

        //UCS bfs = new UCS(iniState);
        //Stack<Node> goalPath = ucs.search();

        //Greedy greedy = new Greedy(iniState);
        //Stack<Node> goalPath = greedy.search();

        //AStar astar = new AStar(iniState);
        //Stack<Node> goalPath = astar.search();

        //----print goal path----
        printGoalPath(goalPath);
    }
    */
}
