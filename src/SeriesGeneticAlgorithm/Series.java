package SeriesGeneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;

// This is representing DNA in this case...
public class Series implements Comparable<Series>{

    static final String[] teamNames = { "Ässät", "Lukko", "HIFK", "HPK", "Ilves", "Jukurit", "JYP", "Kalpa", "KooKoo",
            "Kärpät", "Pelicans", "Saipa", "Sport", "Tappara", "TPS" };

    int teams;
    int amount; //keskinäiset kohtaamiset
    int rounds;
    int gamesPerRound;
    double fitness;
    
    ArrayList<Game>[] series;

    // Alustetaan muuttujat ja lasketaan pelattavat kierrokset

    public Series(int teams, int amount) {

        this.teams = teams;
        this.amount = amount;

        ArrayList<Game> gameList = new ArrayList<>();

        rounds = teams % 2 == 0 ? (teams-1)*amount : teams * amount;
        gamesPerRound = teams / 2;
        series = new ArrayList[rounds];
        fitness = 0.0;

        //TODO Refactor this shit to something better. Methods?!
        ArrayList<Game> partOfGameListRightWay = new ArrayList<>();
        // tee yksi pelilista
        for(int i =1; i < teams+1; i++) {
            for(int j = i+1; j < teams+1; j++) {
                partOfGameListRightWay.add(new Game(i,j));
            }
        }

        if ( amount > 1) {
            ArrayList<Game> partOfGameListReverse = new ArrayList<>();
            for(int i =1; i < teams+1; i++) {
                for(int j = i+1; j < teams+1; j++) {
                    partOfGameListReverse.add(new Game(j,i));
                }
            }
            for(int i = 0; i < amount; i++) {
                if(i % 2 == 0) {
                    gameList.addAll(partOfGameListReverse);
                }else {
                    gameList.addAll(partOfGameListRightWay);
                }
            }
        }
        
        // Shuffle game list to have random 
        // data at start point
        Collections.shuffle(gameList);
        
        // Initialize series list
        for( int i = 0; i < rounds; i++ ) {
            series[i] = new ArrayList<>();
            for(int j = 0; j < gamesPerRound; j++) {
                if(gameList.size() == 0) {
                    break;
                }else {
                    series[i].add(gameList.get(0));
                    gameList.remove(0);
                }
            }
        }
    }

    // Tulostaa sarjataulukon konsoliin.
    // Jos joukkueita on vähemmän kuin 16 niin käytetään Liiga-joukkueiden nimiä, muuten tulostetaan vain numeroita.
    public void printSeries() {
        for (int i = 0; i < series.length; i++) {
            System.out.printf("Round %d: ", i+1);
            for (int j = 0; j < series[i].size(); j++) {
                if(teams < 16) {
                    System.out.print(teamNames[series[i].get(j).home - 1] + " vs " + teamNames[series[i].get(j).away -1]);
                } else {
                    System.out.print(series[i].get(j) + "  ");
                }
                if(j+1 != series[i].size()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
    // Calculate first "errors" by row and then summarize that
    // subtract that from max errors
    public void calculateFitness() {
        double tempFitness = 0;
        for(int i = 0; i < series.length; i++) {
            for(int j = 0; j <series[i].size(); j++){
                for(int k = 0; k <series[i].size(); k++){
                    if ( j != k){
                        if ( series[i].get(j).home == series[i].get(k).home || series[i].get(j).home == series[i].get(k).away) {
                            //System.out.println("kierros" + i +" : "+j +"vs" +k);
                            tempFitness++;
                        }
                        if (series[i].get(j).away == series[i].get(k).home  || series[i].get(j).away == series[i].get(k).away) {
                            //System.out.println("kierros" + i +" : "+j +"vs" +k);
                            tempFitness++;
                        }
                    }
                }
            }
        }
        // 2 * gamesPerRound * rounds / 10
        this.fitness = (2 * gamesPerRound * rounds - tempFitness)/10;
    }

    public ArrayList<Game>[] getSeries() {
        return series;
    }
    public double getFitness(){
        return fitness;
    }

    @Override
    public int compareTo(Series o) {
        if(this.fitness > o.getFitness()) {
            return 1;
        } else if(this.fitness < o.getFitness()) {
            return -1;
        }
        return 0;
    }
}
