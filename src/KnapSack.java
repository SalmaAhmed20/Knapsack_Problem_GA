import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class KnapSack {
    private final double knapsackSize;
    //GA parameter
    private final int chromosomeLength; //= number of items
    private final int population_size;
    private final Random rand = new Random();
    private final double Pc; //crossover [0.4->0.7] e.g. 0.6
    private final double Pm; //mutation [0.001->0.1] e.g. 0.015
    int[][] pairs; //weight and values
    String bestSol;
    private ArrayList<String> Population;
    private ArrayList<String> Crossoverresult = new ArrayList<>();
    private ArrayList<Integer> Fitnesses = new ArrayList<>();
    private ArrayList<Double> Probability_Of_Fitnesses;
    private ArrayList<String> new_Population = new ArrayList<>();
    private int bestFitness;


    KnapSack(int numberOfItem, double knapsackSize, int[][] pairs) {
        //problem parameters
        this.chromosomeLength = numberOfItem;
        this.knapsackSize = knapsackSize;
        this.pairs = pairs;
        this.Population = new ArrayList<>();
        Probability_Of_Fitnesses = new ArrayList<>();
        //initialize  parameters
        this.Pc = 0.8;
        this.Pm = 0.015;
        this.population_size = numberOfItem;

        //call function to initialize population
        this.initializePopulation();
        //100 generation max
        for (int i = 0; i < 99; i++) {
            Replacement();
        }

        Fitnesses.clear();
        for (int i = 0; i < Population.size(); i++) {
            Fitnesses.add(Fitness(Population.get(i)));
        }
        System.out.println(Collections.max(Fitnesses));
        bestSol = Population.get(Fitnesses.indexOf(Collections.max(Fitnesses)));
        System.out.println(bestSol);
        for (int i = 0; i < bestSol.length(); i++)
            if (bestSol.charAt(i) == '1') {
                System.out.println("item: " + pairs[i][0] + " " + pairs[i][1]);
            }

    }

    private void initializePopulation() {
        //Make pop size chromosome
        for (int i = 0; i < this.population_size; i++) {
            String chromosome = "";
            //generate gene randomly
            for (int j = 0; j < this.chromosomeLength; j++) {
                int gene = (int) Math.round(Math.random());
                chromosome += gene;
            }
            if(Fitness(chromosome)==-1)
                continue;
            Population.add(chromosome);
        }
    }

    //Fitness
    private int Fitness(String chromosome) {
        int fit = 0;
        int weight = 0;
        for (int j = 0; j < this.chromosomeLength; j++) {
            if (chromosome.charAt(j) == 49) {
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
    private String Mutation(String chromosome) {
        double RemaingWeight = this.knapsackSize;
        String chromosome2 = chromosome;
        for (int i = 0; i < chromosome.length(); i++) {
            double r = Math.random();//->[0,1[
            if (r <= this.Pm) {
                //if change from 0 -> 1 check  if it in weight
                chromosome=changeBit(i, chromosome);
                if (Fitness(chromosome) == -1) {
                    chromosome = chromosome2;
                }
            }
        }
        return chromosome;
    }

    private String changeBit(int idx, String str) {
        String s = "";
        for (int i = 0; i < str.length(); i++) {
            if (i == idx) {
                if (str.charAt(i) == 49)
                    s += 0;
                else
                    s += 1;
            } else
                s += str.charAt(i);
        }
        return s;
    }

    //Selection
    private void Roulette_Wheel_Selection() {
        Fitnesses = new ArrayList<>();
        Probability_Of_Fitnesses = new ArrayList<>();
        new_Population = new ArrayList<>();
        //sumOfFitnesses = total of fitnesses to calculate ratio of each chromosome
        int sumOfFitnesses = 0;
        for (String s : Population) {
            Fitnesses.add(Fitness(s));
            sumOfFitnesses += Fitness(s);
        }
        for (Integer fitness : Fitnesses) Probability_Of_Fitnesses.add((double) (fitness / sumOfFitnesses));

        for (int i = (Probability_Of_Fitnesses.size()) - 1; i >= 0; i--) {
            double sumofprob = 0;
            for (int j = i; j >= 0; j--)
                sumofprob += Probability_Of_Fitnesses.get(j);
            Probability_Of_Fitnesses.set(i, sumofprob);
        }
        // we will select two chromosomes then crossover them
        for (int i = 0; i < 2; i++) {
            double r1 = rand.nextDouble();
            for (int j = 1; j < Probability_Of_Fitnesses.size(); j++) {
                new_Population.add(Population.get(0));
                Population.remove(0);//select it
                break;
            }
        }
    }

    private void Replacement() {
        for (int i = 1; i < this.population_size ; i++) {
            new_Population.clear();
            Fitnesses.clear();
            Roulette_Wheel_Selection();
            if (CrossOver(new_Population.get(0), new_Population.get(1))) {
                this.Population.add(Mutation(Crossoverresult.get(0)));
                this.Population.add(Mutation(Crossoverresult.get(1)));
            } else {
                this.Population.add(new_Population.get(0));
                this.Population.add(new_Population.get(1));
            }
            Crossoverresult.clear();
        }
    }
}
