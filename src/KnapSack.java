import java.util.ArrayList;

public class KnapSack {
    //problem parameters
    private int numberOfItem;
    private double knapsackSize;
    ArrayList[][] pairs; //weight and values
    //GA parameter
    private int chromosomeLength; //= number of items
    private int population_size;
    private ArrayList<String> Population;
    private double Pc; //crossover [0.4->0.7] e.g. 0.6
    private double Pm; //mutation [0.001->0.1] e.g. 0.015

    KnapSack(int numberOfItem,double knapsackSize, ArrayList[][] pairs){
        this.numberOfItem =numberOfItem;
        this.chromosomeLength=numberOfItem;
        this.knapsackSize=knapsackSize;
        this.pairs=pairs;
        //initialize  parameters
        this.Pc=0.6;
        this.Pm=0.015;
        //this.population_size=50;

        //call function to initialize population
    }
    private void initializePopulation()
    {
        String chromosome;
    }
}
