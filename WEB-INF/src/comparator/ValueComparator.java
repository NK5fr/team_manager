package comparator;

import java.util.Comparator;

import dto.Joueur;

public class ValueComparator implements Comparator<Joueur>{
    
    @Override
    public int compare(Joueur o1, Joueur o2) {
        return o1.getValeur() - o2.getValeur();
    }

}
