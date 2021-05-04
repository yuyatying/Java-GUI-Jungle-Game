package model;
/**
 * the class Player is used to store the player's information which includes player's name and id
 */
public class Player {
    private String playerName;
    private int playerID;
    private int animalLeft;

    /**
     * constructor for the class Player, contains player's name and id.
     * @param playerName the player's name
     * @param playerID the player's id
     *
     */
    public Player(String playerName, int playerID){
        this.playerName = playerName;
        this.playerID = playerID;
        this.animalLeft = 8;
    }

    /**
     * return the player's name
     * @return player_name return the player's name
     */
    public String getPlayer_name() {
        return this.playerName;
    }

    /**
     * set the player's name
     * @param player_name the player's name
     */
    public void setPlayer_name(String player_name) {
        this.playerName = player_name;
    }

    /**
     * return the player's id
     * @return player_id return the player's id
     */
    public int getPlayer_id() {
        return this.playerID;
    }

    public void animalEaten(){
        this.animalLeft--;
    }

    public int getAnimalLeft(){
        return this.animalLeft;
    }

}