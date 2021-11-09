import java.util.ArrayList;

public class KnapSack {
    //problem parameters
    private int numberOfItem;
    private double knapsackSize;
    int[][] pairs; //weight and values
    //GA parameter
    private int chromosomeLength; //= number of items
    private int population_size;
    private ArrayList<String> Population;
    private ArrayList<String> Crossoverresult;
    private double Pc; //crossover [0.4->0.7] e.g. 0.6
    private double Pm; //mutation [0.001->0.1] e.g. 0.015

    KnapSack(int numberOfItem, double knapsackSize, int[][] pairs){
        this.numberOfItem =numberOfItem;
        this.chromosomeLength=numberOfItem;
        this.knapsackSize=knapsackSize;
        this.pairs=pairs;
        //initialize  parameters
        this.Pc=0.6;
        this.Pm=0.015;
        //
        this.population_size=numberOfItem;

        //call function to initialize population
    }
    private void initializePopulation()
    {
        //Make pop size chromosome
        for (int i = 0; i < this.population_size; i++) {
            String chromosome = "";
            double RemaingWeight=  this.knapsackSize;
            //generate gene randomly
            for (int j = 0; j < this.chromosomeLength; j++) {
                int gene = (int) Math.round( Math.random() );
                // to achieve constraint if we pick an item (1)
                // it should be in weight  capacity
                if(gene==1)
                    RemaingWeight=RemaingWeight-pairs[j][0];
                if (RemaingWeight >= 0||gene==0) {
                    chromosome += gene;
                }
                else {
                    chromosome+="0".repeat(chromosomeLength-j);
                }
            }
            Population.add(chromosome);
        }
    }
    //Fitness
    private int Fitness(String chromosome)
    {
        int total = 0;
        for(int j=0; j<chromosome.length(); j++)
        {
            if(chromosome.charAt(j) == '1')
            {
                total+= pairs[j][0];
            }
        }
        return  total ;
    }
    //Mutation
    private void Mutation(String chromosome){

        double RemaingWeight=  this.knapsackSize;
        for(int i=0;i<chromosome.length();i++)
        {
            double r2=Math.random();//->[0,1[
            //we generate r2 before to performance
            if(r2 <= this.Pm){
                // to check it in weight  capacity
                RemaingWeight=RemaingWeight-pairs[i][0];

                if (RemaingWeight >= 0&&chromosome.charAt(i)=='0') {
                    chromosome.replace(chromosome.charAt(i),'1');
                }
                else
                {
                    RemaingWeight=RemaingWeight+pairs[i][0];
                    if (chromosome.charAt(i)=='1') {
                    chromosome.replace(chromosome.charAt(i),'0');
                }
                }
            }

        }


    }
    //Crossover
    private boolean CrossOver(String chromosome1, String chromosome2){
        String offString1,offString2;
        double r2=Math.random();//->[0,1[
        //we generate r2 before to performance
        if(r2 <= this.Pc){
            //Math.floor(Math.random()*(max-min+1)+min)
            int CrossOverPoint = (int) Math.floor(Math.random()*((this.chromosomeLength-1)-1+1)+1);//->[1,l-1]
            offString1=chromosome1.substring(0,CrossOverPoint)+chromosome2.substring(CrossOverPoint);
            offString2=chromosome2.substring(0,CrossOverPoint)+chromosome1.substring(CrossOverPoint);
            this.Crossoverresult.add(offString1);
            this.Crossoverresult.add(offString2);
            return true;
        }
        return false;
    }
}
