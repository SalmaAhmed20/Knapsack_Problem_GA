import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
         * Maximize benefit (fitness)
         */


        File inputFile = new File("input_example.txt");
        Scanner scanner = new Scanner(inputFile);
        int numberOfTestcases = scanner.nextInt();
        for (int i = 0; i < numberOfTestcases; i++) {
            int numofitem = scanner.nextInt();
            int capacity = scanner.nextInt();
//            System.out.println(numofitem + "   " + capacity);
            int[][] items = new int[numofitem][2];

            for (int j = 0; j < numofitem; j++) {
                for (int k = 0; k < 2; k++) {
                    items[j][k] = scanner.nextInt();
                }
            }
//            for (int j = 0; j < numofitem; j++) {
//                for (int k = 0; k < 2; k++) {
//                    System.out.print(items[j][k] + " ");
//                }
//                System.out.println("\n");
//            }
            System.out.println("Number of item : "+numofitem);
            System.out.print("Case " + (i + 1) + ": ");

            new KnapSack(numofitem, capacity, items);
            System.out.println("------------------------------------------------------------");
        }
        scanner.close();

    }
}