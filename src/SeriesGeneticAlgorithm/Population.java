package SeriesGeneticAlgorithm;

import java.util.ArrayList;
import java.util.Random;

public class Population  {

    private double mutationRate;
    private double crossOverRate;
    private int maxPopulation;
    private ArrayList<Series> population;
    private ArrayList<Series> newPopulation;
    private Random rand = new Random();
    public Population(double mutationRate, double crossOverRate, int maxPopulation, int teams, int amount) {
        this.mutationRate = mutationRate;
        this.maxPopulation = maxPopulation;
        this.crossOverRate = crossOverRate;
        population  = new ArrayList<>();
        newPopulation = new ArrayList<>();

        for(int i = 0; i < maxPopulation; i++){
            population.add(new Series(teams, amount));
        }
        for (Series s : population) {
            s.calculateFitness();
            System.out.println(s.fitness);
        }
    }

    public void newGeneration(int k) {
        while(newPopulation.size() < maxPopulation) {
            //TODO DO crossover or just pick one parent
            //TODO Tournament selection for cloning
            //TODO Tournament selection for two parents

            //TODO Crossover parents to make two child

            //TODO Mutation-phase for child
        }
    }

    public Series[] tournamentSelection(int picks){
        int p = rand.nextInt(maxPopulation);

    }
}
