package game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import communication.ConsoleInOut;
import communication.InOutInterface;
import game.card.entity.CompetitorType;



public abstract class Game <ExtendedENum extends Enum<ExtendedENum>> {

    protected static int winningScore = 0;
    public final static String askName = "What is your name";
    public final static String numberOfCompetitors = "How many competitors, minimum of two?";
    public final static String playerNamesFile = "src/main/resources/card_player.csv";
    public final static int userIndex = 1;
    protected HashMap<CompetitorType, String[]> computerNames = new HashMap<CompetitorType, String[]>();
    
    protected InOutInterface inOut = new ConsoleInOut();
    private List<Player> players = new ArrayList<Player>();
    protected LoadCSV loadCSV = new LoadCSV();
    protected boolean finishGame = false;
    protected Class<ExtendedENum> helpClass = null;
    
    public void setInOut(InOutInterface inOut){
        this.inOut = inOut;
    }

    protected void setFinishGame(boolean finshGame) {
        this.finishGame = finshGame;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public Player getUser(){
        return getPlayer(userIndex);
    }

    public List<Player> getPlayers(){
        return players;
    }

    public boolean getFinishGame(){
        return finishGame;
    }

    public int getPlayersSize(){
        return players.size();
    }

    public void clearPlayers(){
        players.clear();
    }

    protected void print(String message) {
        inOut.print(message);
    }

    protected ExtendedENum generateHelp() {
        return inOut.getEnumIndex("", helpClass);
    }

    protected String getInputString(String message) {
        return inOut.getInputString(message);
    }

    protected int getInputInteger(String message) {
        return inOut.getInputInteger(message);
    }

    protected void displayPlayer(Player player){
        inOut.displayPlayer(player);
    }

    protected void displayPlayerWithVisibility(Player player){
        inOut.displayPlayerWithVisibility(player);
    }

    public void resetPlayers() {
        for (Player competitor : players) {
            competitor.setWinner(false);
        }
    }

    protected int getNumberOfPlayers(){
        return getInputInteger(numberOfCompetitors);
    }

    public Player createPlayer(CompetitorType competitorType, String name){
        Player player = new Player(competitorType, name);
        players.add(player);
        return player;
    }

    public void initiatePlayers() {
        players.clear();
        createHumanPlayer();
        createComputerCompetitors(getNumberOfPlayers() - 1);
    }

    protected void addPlayer(Player competitor) {
        players.add(competitor);
    }

    public void createHumanPlayer(){
        String name = getInputString(askName);
        addPlayer(new Player(CompetitorType.USER, name));
    }

  

    protected HashMap<CompetitorType, String[]> getCompupterPlayersNames(){
        CompetitorType competitorType = null;
        int counter = 0;
        if (computerNames.size() == 0){
            List<String[]> rawPlayerNames = loadCSV.getCSVRows(playerNamesFile);
            for (String[] row : rawPlayerNames){
                competitorType = CompetitorType.valueOf(row[0]);
                if (!computerNames.containsKey(competitorType)){
                    counter = 0;
                    computerNames.put(competitorType, new String[rawPlayerNames.size()]);
                }
                computerNames.get(competitorType)[counter] = row[1];
                counter ++;
            }
        }
        return computerNames;
    }

    public void createComputerCompetitors(int noOfComputerCompetitors) {
        HashMap<CompetitorType, String[]> computerNames = getCompupterPlayersNames();
        for (int counter = 0; counter < noOfComputerCompetitors; counter++) {
            addPlayer(new Player(CompetitorType.COMPUTER, computerNames.get(CompetitorType.COMPUTER)[counter]));
        }
    }
    protected boolean hasPlayerWon(Player competitor) {
        boolean aPlayerHasWon = false;
        return aPlayerHasWon;
    }

    public abstract int getScore(Player player);

    protected boolean hasBestScore(int bestScore, int currentScore) {
        boolean hasWon = false;
        if (bestScore < 0 || currentScore > bestScore) {
            hasWon = true;
        }
        return hasWon;
    }

    public Player determineWinner(List<Player> competitors){
        winningScore = -1;
        Player winningPlayer = null;
        int currentScore = 0;
        for (Player player : players){
            currentScore = getScore(player);
            if (hasBestScore(winningScore, currentScore) || hasPlayerWon(player)) {
                winningScore = currentScore;
                winningPlayer = player;
            }
            player.setScore(currentScore);
        }
        winningPlayer.setWinner(true);
        return winningPlayer;
    }

    protected abstract void beforePlayOfRound();

    protected abstract void afterPlayOfRound();

    protected abstract void userPlays(Player competitor);

    protected abstract void computerPlays(Player competitor);

    protected void playerPlaysHand(Player competitor) {
        if (competitor.getCompetitorType() == CompetitorType.USER) {
            userPlays(competitor);
        } else {
            computerPlays(competitor);
        }
    }

    protected void beforePlayerPlays(Player player){

    }

    protected void afterPlayerPlays(Player player){

    }

    protected void playARound() {
        int counter = 0;
        Player player = null;
        do {
            player = players.get(counter);
            beforePlayerPlays(player);
            playerPlaysHand(player);
            afterPlayerPlays(player);
            counter ++;
        } while (counter < players.size() && !player.hasWon());
    }

    protected void showPlayers() {
        for (Player player : players) {
            displayPlayerWithVisibility(player);
        }
    }

    protected abstract void initiate();

    protected void playTillFinish(){
        while (!finishGame) {
            beforePlayOfRound();
            playARound();
            afterPlayOfRound();
        }
    }

    protected void play() {
        initiate();
        playTillFinish();
        resetPlayers();
        Player winner = determineWinner(players);
        winner.setWinner(true);
        showPlayers();
    }

}
