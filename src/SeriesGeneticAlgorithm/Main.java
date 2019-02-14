package SeriesGeneticAlgorithm;

public class Main {
    public static void main(String[] args) {
//        Series sr = new Series(4, 2);
//        sr.printSeries();
//        sr.calculateFitness();
        Population pop = new Population(0.2, 0.3, 50, 12,2);
    }
}
/*
TODO: Kirjota tämä selkokielellä ja englanniksi!
Lasketaan virheet riveittäin(per kierros) ja
summataan koko taulukon virheet ja
näistä tehdään fitness value.

Gene == yksi peli

Generaation parhaat valitaan (montako ?!?)
Kun ollaan valittu niin crossover randomilla ja se kertoo montako kierrosta siirretään.
Mutation == jokin prosentuaalinen osuus taulukosta
Muutation tapahtuessa, valitaan random peli ja se vain paiskataan jonnekkin välittämättä mistään.

 */