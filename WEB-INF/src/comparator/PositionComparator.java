package comparator;

import java.util.Comparator;

import dto.Joueur;

public class PositionComparator implements Comparator<Joueur>{

    @Override
    public int compare(Joueur o1, Joueur o2) {
        return o1.getPosition().getValue() - o2.getPosition().getValue();
    }
    
}
