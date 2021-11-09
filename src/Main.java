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
        String n="01011";
        System.out.println(n=n.substring(0,1)+'0'+n.substring(2));
//        CrossOver("10100", "01011");

    }
//Test Crossover
//    static void CrossOver(String chromosome1, String chromosome2) {
//        String offString1, offString2;
//        double r2 = Math.random();
//        if (r2 <= 0.6) {
//            //Math.floor(Math.random()*(max-min+1)+min)
//            int CrossOverPoint = (int) Math.floor(Math.random() * ((5 - 1) - 1 + 1) + 1);//->[1,l-1]
//            System.out.println(CrossOverPoint);
//            offString1 = chromosome1.substring(0, CrossOverPoint) + chromosome2.substring(CrossOverPoint);
//            offString2 = chromosome2.substring(0, CrossOverPoint) + chromosome1.substring(CrossOverPoint);
//            System.out.println(offString1 + "," + offString2);
//
//        }
//
//    }
}