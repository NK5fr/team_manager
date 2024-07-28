package comparator;

import java.util.Comparator;

import dto.Joueur;
import util.LettersManager;

public class LastnameComparator implements Comparator<Joueur> {

    @Override
    public int compare(Joueur o1, Joueur o2) {
        return LettersManager.sortableWord(o1.getNom()).compareTo(LettersManager.sortableWord(o2.getNom()));
    }

}
