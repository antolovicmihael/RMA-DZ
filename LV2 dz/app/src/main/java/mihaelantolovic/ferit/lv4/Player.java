package mihaelantolovic.ferit.lv4;

public class Player {
    private int playerID;
    private String playerName;
    private int numTries;

    public Player(){};

    public Player(String playerName, int numTries){
        this.playerName = playerName;
        this.numTries   = numTries;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getNumTries() {
        return numTries;
    }

    public void setNumTries(int numTries) {
        this.numTries = numTries;
    }
}
