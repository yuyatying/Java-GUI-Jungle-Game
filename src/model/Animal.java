package model;

public class Animal implements BoardObject {
    private Player player;
    private Pieces piece;

    public Animal(Player player, Pieces piece){
        this.player = player;
        this.piece = piece;
    }

    public Animal(){

    }

    public Animal(Player player, int rank){
        this.player = player;
        if (rank==0){
            this.piece=Pieces.Trapped;
        }else if(rank==1){
            this.piece = Pieces.Rat;
        }else if(rank==2){
            this.piece = Pieces.Cat;
        }else if(rank==3){
            this.piece = Pieces.Dog;
        }else if(rank==4){
            this.piece = Pieces.Wolf;
        }else if(rank==5){
            this.piece = Pieces.Leopard;
        }else if(rank==6){
            this.piece = Pieces.Tiger;
        }else if(rank==7){
            this.piece = Pieces.Lion;
        }else if(rank==8){
            this.piece = Pieces.Elephant;
        }else if (rank==9){
            this.piece=Pieces.RatInRiver;
        }
    }
    public static String capture(Animal own, Animal enemy){
        if (own.getRank()==8 && enemy.getRank()==1){return "An Elephant(8) cannot capture a Rat(1)";}
        else if (own.getRank()==9&&enemy.getRank()!=9){return "A Rat in River cannot capture Animals on Land";}
        else if (own.getRank()!=9&&enemy.getRank()==9){return "Animals on Land cannot capture Animals in River";}
        else if (own.getRank()==1 && enemy.getRank()==8) {return null;}
        else if (own.getRank()>= enemy.getRank()){return null;}
        else return "Your rank is lower than that of the opponent's";
    }
    public String getAnimalType(){
        return this.piece.name();
    }

    public String getPlayerName(){
        return this.player.getPlayer_name();
    }

    public Player getPlayer(){
        return player;
    }

    public int getPlayerId(){
        return this.player.getPlayer_id();
    }

    public int getRank(){
        return this.piece.getRank();
    }

    @Override
    public String[] toString(String[] printMessage){
        int player_id = this.getPlayerId();
        String animal_name = this.getAnimalType();
        String side = " ";
        if(animal_name.length()>5){
            animal_name = animal_name.substring(0,5);
        }else{
            animal_name += new String(new char[5-animal_name.length()]).replace("\0", side);
        }
        int ranking = this.getRank();
        printMessage[0] += "_______";
        printMessage[1] += "|" + animal_name + "|";
        printMessage[2] += "|  " + ranking + "  |";
        printMessage[3] += "|  " + player_id + "  |";
        printMessage[4] += "_______";
        return printMessage;
    }
}