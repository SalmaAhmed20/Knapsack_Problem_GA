import java.util.ArrayList;
import java.util.Random;

public class KnapSack {
    //problem parameters
    private final int numberOfItem;
    private final double knapsackSize;
    //GA parameter
    private final int chromosomeLength; //= number of items
    private final int population_size;
    private final Random rand = new Random();
    private final double Pc; //crossover [0.4->0.7] e.g. 0.6
    private final double Pm; //mutation [0.001->0.1] e.g. 0.015
    int[][] pairs; //weight and values
    private ArrayList<String> Population;
    private ArrayList<String> Crossoverresult;
    private ArrayList<Integer> Fitnesses;
    private ArrayList<Double> Probability_Of_Fitnesses;
    private ArrayList<String> new_Population;


    KnapSack(int numberOfItem, double knapsackSize, int[][] pairs) {
        this.numberOfItem = numberOfItem;
        this.chromosomeLength = numberOfItem;
        this.knapsackSize = knapsackSize;
        this.pairs = pairs;
        //initialize  parameters
        this.Pc = 0.6;
        this.Pm = 0.015;
        //
        this.population_size = numberOfItem;

        //call function to initialize population
        this.initializePopulation();
    }

    private void initializePopulation() {
        //Make pop size chromosome
        for (int i = 0; i < this.population_size; i++) {
            String chromosome = "";
            double RemaingWeight = this.knapsackSize;
            //generate gene randomly
            for (int j = 0; j < this.chromosomeLength; j++) {
                int gene = (int) Math.round(Math.random());
                // to achieve constraint if we pick an item (1)
                // it should be in weight  capacity
                if (gene == 1)
                    RemaingWeight = RemaingWeight - pairs[j][0];
                if (RemaingWeight >= 0 || gene == 0) {
                    chromosome += gene;
                } else {
                    chromosome += "0".repeat(chromosomeLength - j);
                }
            }
            Population.add(chromosome);
        }
    }

    //Fitness
    private int Fitness(String chromosome) {
        int fit = 0;
        int weight = 0;
        for (int j = 0; j < chromosome.length(); j++) {
            if (chromosome.charAt(j) == '1') {
                //value
                weight += pairs[j][0];
                fit += pairs[j][1];
            }
        }
        if (weight <= this.knapsackSize)
            return fit;
        else
            return -1;
    }

    //Crossover
    private boolean CrossOver(String chromosome1, String chromosome2) {
        String offString1, offString2;
        double r2 = Math.random();//->[0,1[
        //we generate r2 before to performance
        if (r2 <= this.Pc) {
            //Math.floor(Math.random()*(max-min+1)+min)
            int CrossOverPoint = (int) Math.floor(Math.random() * ((this.chromosomeLength - 1) - 1 + 1) + 1);//->[1,l-1]
            offString1 = chromosome1.substring(0, CrossOverPoint) + chromosome2.substring(CrossOverPoint);
            offString2 = chromosome2.substring(0, CrossOverPoint) + chromosome1.substring(CrossOverPoint);
            this.Crossoverresult.add(offString1);
            this.Crossoverresult.add(offString2);
            return true;
        }
        return false;
    }


    //Mutation
    private void Mutation(String chromosome) {

        double RemaingWeight = this.knapsackSize;
        for (int i = 0; i < chromosome.length(); i++) {
            double r = Math.random();//->[0,1[
            if (r <= this.Pm) {
                //if change from 0 -> 1 check  if it in weight
                RemaingWeight = RemaingWeight - pairs[i][0];
                if (RemaingWeight >= 0 && chromosome.charAt(i) == '0') {
                    chromosome=chromosome.substring(0,i)+'1'+chromosome.substring(i+1);
                    RemaingWeight = RemaingWeight + pairs[i][0];
                    if (chromosome.charAt(i) == '1') {
                        chromosome=chromosome.substring(0,i)+'0'+chromosome.substring(i+1);

                    }
                }
            }

        }
    }


    //Selection
    private ArrayList<String> Roulette_Wheel_Selection(ArrayList<String> Population) {
        Fitnesses = null;
        Probability_Of_Fitnesses = null;
        int sumOfFitnesses = 0;
        for (int i = 0; i < Population.size(); i++) {
            Fitnesses.set(i, Fitness(Population.get(i)));
            sumOfFitnesses += Fitness(Population.get(i));
        }
        for (int i = 0; i < Fitnesses.size(); i++)
            Probability_Of_Fitnesses.set(i, (double) (Fitnesses.get(i) / sumOfFitnesses));

        for (int i = Probability_Of_Fitnesses.size(); i >= 0; i--) {
            double sumofprob = 0;
            for (int j = i; j >= 0; j--)
                sumofprob += Probability_Of_Fitnesses.get(j);
            Probability_Of_Fitnesses.set(i, sumofprob);
        }
        //-2 de lsa h7ot rate badalha
        for (int i = 0; i < (Population.size() - 2); i++) {
            double r1 = rand.nextDouble();
            for (i = 1; i < Probability_Of_Fitnesses.size(); i++) {
                if (r1 == Probability_Of_Fitnesses.get(0) && r1 >= 0) {
                    new_Population.add(Population.get(i));
                    break;
                } else if (r1 <= Probability_Of_Fitnesses.get(i) && r1 >= Probability_Of_Fitnesses.get(i - 1)) {
                    new_Population.add(Population.get(i));
                    break;
                }
            }
        }
        return new_Population;
    }
}
