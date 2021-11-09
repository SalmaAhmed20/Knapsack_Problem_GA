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

//        File file = new File("input_example.txt");
//        FileReader fr = null;   //reads the file
//
//            fr = new FileReader(file);
//        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
//        StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters
//        String line = "";
//        while (true) {
//
//            if (!((line = br.readLine()) != null)) break;
//            sb.append(line);      //appends line to string buffer
//            sb.append("\n");//line feed
//            if(!line.equals("\n\n"))
//                lines.add(line);
//        }
//        fr.close();    //closes the stream and release the resources
//        System.out.println("Contents of File: ");
//        //System.out.println(sb.toString());//returns a string that textually represents the object
//        System.out.println(lines);
        //System.out.println(lines.get(1).replace("\\s+","111"));
        //System.out.println(lines.get(1));

    }

}