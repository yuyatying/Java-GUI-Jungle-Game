package model;

public class Trap implements BoardObject {
    private Player player;
    private Animal animal;
    private boolean trappedAnimal;

    public Trap(Player player){
        this.player = player;
        this.trappedAnimal = false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
        this.trappedAnimal = true;
    }

    public void removeAnimal(){
        this.trappedAnimal = false;
    }

    public boolean containAnimal(){
        return this.trappedAnimal;
    }

    @Override
    public String[] toString(String[] printMessage){
        if(containAnimal()){
            String animal_name = animal.getAnimalType();
            String side = " ";
            if(animal_name.length()>5){
                animal_name = animal_name.substring(0,5);
            }else{
                animal_name += new String(new char[5-animal_name.length()]).replace("\0", side);
            }
            int ranking = animal.getRank();
            int playerID = animal.getPlayerId();
            printMessage[0] += "#######";
            printMessage[1] += "#" + animal_name + "#";
            printMessage[2] += "#  " + ranking + "  #";
            printMessage[3] += "#  " + playerID + "  #";
            printMessage[4] += "#######";
            return printMessage;
        }else {
            printMessage[0] += "#######";
            printMessage[1] += "#     #";
            printMessage[2] += "#  " + player.getPlayer_id() + "  #";
            printMessage[3] += "#     #";
            printMessage[4] += "#######";
            return printMessage;
        }
    }
}
