import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
         * Take Number of items (N)
         * Take Size of Knapsack (S)
         * item is a pair of weight and benefit
         * Length of chromosome (N)
         * Encoding Binary
         * initialize population
         * Constraint to each chromosome <= knapsack size
         * Maximize benefit (fitness)*/

        int[][] pairs={{4,1},{7,7},{1,22},{3,23},{3,6}};
        System.out.println("Case 1 ");new KnapSack(5,14,pairs);

        int[][] pair2={{10,27},{9,27},{8,12},{8,28},{3,23}};
        System.out.println("Case 2 ");new KnapSack(5,28,pair2);
        ArrayList<String> lines=new ArrayList<>();

        File file = new File("input_example.txt");
        FileReader fr = null;   //reads the file

            fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
        String line = "";
        while (true) {

            if (!((line = br.readLine()) != null)) break;
            if(!line.isEmpty())
                lines.add(line);
        }
        fr.close();    //closes the stream and release the resources
        System.out.println(lines);
       int numOfTstCases = Integer.parseInt(lines.get(0));

        for (int i = 0; i <numOfTstCases ; i++) {
            System.out.println("Case "+(i+1)+ ": ");

        }

    }

}