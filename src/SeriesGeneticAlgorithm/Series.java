package SeriesGeneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;

public class Series {

    static final String[] teamNames = { "Ässät", "Lukko", "HIFK", "HPK", "Ilves", "Jukurit", "JYP", "Kalpa", "KooKoo",
            "Kärpät", "Pelicans", "Saipa", "Sport", "Tappara", "TPS" };

    int teams;
    int amount; //keskinäiset kohtaamiset
    int rounds;
    int gamesPerRound;

    ArrayList<Game> gameList = new ArrayList<>();
    ArrayList<Game>[] series;

    // Alustetaan muuttujat ja lasketaan pelattavat kierrokset

    public Series(int teams, int amount) {

        this.teams = teams;
        this.amount = amount;

        rounds = teams % 2 == 0 ? (teams-1)*amount : teams * amount;
        gamesPerRound = teams / 2;
        series = new ArrayList[rounds];
        ArrayList<Game> temp = new ArrayList<>();
        // tee yksi pelilista
        for(int i =1; i < teams+1; i++) {
            for(int j = i+1; j < teams+1; j++) {
                temp.add(new Game(i,j));
            }
        }

        if ( amount > 1) {
            ArrayList<Game> tmp = new ArrayList<>();
            for(int i =1; i < teams+1; i++) {
                for(int j = i+1; j < teams+1; j++) {
                    tmp.add(new Game(j,i));
                }
            }
            for(int i = 0; i < amount; i++) {
                if(i % 2 == 0) {
                    gameList.addAll(tmp);
                }else {
                    gameList.addAll(temp);
                }
            }
        }



        // Random data at start
        Collections.shuffle(gameList);
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

    public ArrayList<Game> getList() {
        return gameList;
    }

    public ArrayList<Game>[] getSeries() {
        return series;
    }
}
