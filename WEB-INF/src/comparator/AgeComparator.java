package comparator;

import java.util.Comparator;

import dto.Joueur;

public class AgeComparator implements Comparator<Joueur>{

    @Override
    public int compare(Joueur o1, Joueur o2) {
        return o1.getAge() - o2.getAge();
    }
    
}
