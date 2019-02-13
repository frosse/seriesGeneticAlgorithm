package SeriesGeneticAlgorithm;

public class Game {
    int home;
    int away;

    public Game(int home, int away) {
        this.home = home;
        this.away = away;
    }

    public int getHome() {
        return home;
    }

    public int getAway() {
        return away;
    }

    public void swap() {
        int tmp = this.home;
        this.home = this.away;
        this.away = tmp;
        System.out.println(home);
        System.out.println(away);
    }

    @Override
    public String toString() {
        return home + " vs " + away;
    }
}
