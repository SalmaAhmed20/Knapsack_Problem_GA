public class Main {
    public static void main(String[] args) {
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

    }
}