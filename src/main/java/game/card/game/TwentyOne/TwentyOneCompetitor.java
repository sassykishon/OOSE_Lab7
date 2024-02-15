package game.card.game.TwentyOne;
import java.util.Random;

import game.Player;
import game.card.entity.CompetitorType;

public class TwentyOneCompetitor extends Player{

    protected int levelOfRisk;

    public TwentyOneCompetitor(CompetitorType competitorType, String name){
        super(competitorType, name);
    }
    public TwentyOneCompetitor(CompetitorType competitorType, String name, int levelOfRisk){
        super(competitorType, name);
        int risk;
        Random rand = new Random();
        if (levelOfRisk == 0){
            risk = 11 + rand.nextInt() % 8;
        } else {
            risk = levelOfRisk;
        }
        this.levelOfRisk = risk;
    }

    public int getLevelOfRisk() {
        return levelOfRisk;
    }   
}