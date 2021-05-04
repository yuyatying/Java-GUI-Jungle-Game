package model;

public class River implements BoardObject {
    private Animal animal;
    private boolean trappedAnimal;

    public River(){
        this.trappedAnimal = false;
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
        if (trappedAnimal) {
            int player_id = animal.getPlayerId();
            String animal_name = animal.getAnimalType();
            String side = " ";
            animal_name = animal_name.substring(0,5);
            int ranking = animal.getRank();
            printMessage[0] += "~~~~~~~";
            printMessage[1] += "~" + animal_name + "~";
            printMessage[2] += "~  " + ranking + "  ~";
            printMessage[3] += "~  " + player_id + "  ~";
            printMessage[4] += "~~~~~~~";
        }
        else {
            printMessage[0] += "~~~~~~~";
            printMessage[1] += "~     ~";
            printMessage[2] += "~     ~";
            printMessage[3] += "~     ~";
            printMessage[4] += "~~~~~~~";
        }
        return printMessage;
    }
}
