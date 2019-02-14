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

        for(int i = 0; i < maxPopulation; i++){
            population.add(new Series(teams, amount));
        }
        for (Series s : population) {
            s.calculateFitness();
            System.out.println(s.fitness);
        }
    }

    public void newGeneration(int k) {

        newPopulation = new ArrayList<>();

        while(newPopulation.size() < maxPopulation) {
            // do crossover or not
            if(crossOverRate < rand.nextDouble()){
                System.out.println("Do crossover!");
                //Use tournament selection to pick one from population
                Series[] picked = tournamentSelectionForParents(5);
               // Do crossover
                crossOver(picked[0], picked[1]);

            }else {
                //TODO Tournament selection for cloning
                newPopulation.add(tournamentSelectionForOneParent(5));
                System.out.println("Pick one for cloning");

            }

            //TODO Crossover parents to make two child

            //TODO Mutation-phase for child
        }
    }

    public Series tournamentSelectionForOneParent(int k) {

        Series best = new Series();
        Series temp;

        for(int i = 0; i < k; i++) {
            temp = new Series(population.get(rand.nextInt(maxPopulation)));
            if(i == 0) {
                best = temp;
            } else if( best.getFitness() < temp.getFitness()) {
                best = temp;
            }
        }
        System.out.println("Best from selection is :" + best.getFitness());
        return best;
    }

    public Series[] tournamentSelectionForParents(int k) {

        Series[] picked = new Series[2];

        for(int j = 0; j < 2; j++) {
            Series best = new Series();
            Series temp;
            for (int i = 0; i < k; i++) {
                temp = new Series(population.get(rand.nextInt(maxPopulation)));
                if (i == 0) {
                    best = temp;
                } else if (best.getFitness() < temp.getFitness()) {
                    best = temp;
                }
            }
            picked[j] = best;
        }
    return picked;
    }
    public Series[] crossOver(Series parent1, Series parent2) {
        //TODO fix variable names
        int howMany = rand.nextInt(parent1.rounds)+1;
        Series[] rtn = new Series[2];
        Series child1 = new Series(parent2);
        parent1.printSeries();
        Series child2 = new Series(parent1);
        for(int i = 0; i < howMany; i++) {
            child1.getSeries()[i] = parent1.getSeries()[i];
        }
        for (int i = howMany; i < parent2.rounds; i++ ) {
            child2.getSeries()[i] = parent2.getSeries()[i];
        }
        //TODO remove duplicates from child
        rtn[0] = child1;
        rtn[1] = child2;
        child1.printSeries();
        //child2.printSeries();
        return rtn;
    }
}
