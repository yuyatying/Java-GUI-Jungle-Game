package model;

import java.io.PrintStream;
import java.util.ArrayList;

public class JungleGame {
    private Player player1, player2;
    private BoardObject[][] board;

    public JungleGame(Player player1, Player player2, BoardObject[][] board){
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
    }

    public JungleGame(){
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public BoardObject[][] getBoard() {
        return board;
    }

    public void setBoard(BoardObject[][] board) {
        this.board = board;
    }

    public Player getPlayerByID(int playerID){
        if(playerID == 1){
            return player1;
        }else{
            return player2;
        }
    }
    public boolean isInitialized(){
        try {
            if(this.getPlayer1() != null){
                return true;
            }else{
                return false;
            }
        } catch(Exception e){
            return false;
        }
    }

    public boolean Move(String from, String to, int currentPlayer, PrintStream out){
        int FromX = 0, FromY = 0, ToX = 0, ToY = 0;
        boolean flag = true;
        boolean haswon = false;
        String winMessage = "";
        Player currentplaying = getPlayerByID(currentPlayer);

        ArrayList<Integer> temp = this.checkValidCommand(from, to, System.out);
        if (temp.size()==0) {
            out.println("Invalid Move Command Format");
            return false;
        }
        FromX = temp.get(0);
        FromY = temp.get(1);
        ToX = temp.get(2);
        ToY = temp.get(3);

        String fromObjectType = board[FromX][FromY].getClass().getSimpleName();
        String toObjectType = board[ToX][ToY].getClass().getSimpleName();

        Animal animalFrom = checkValidMovement(fromObjectType, toObjectType, FromX, FromY, ToX, ToY, currentPlayer, currentplaying, System.out);
        if(animalFrom == null){
            return false;
        }

        boolean captureAnimal = checkValidCapture(toObjectType, ToX, ToY, animalFrom, currentplaying, System.out);
        if(!captureAnimal){
            return false;
        }

        String[] gameMessage = updateBoard(FromX, FromY, ToX, ToY, animalFrom, currentplaying, currentPlayer, fromObjectType, System.out);
        if(gameMessage == null){
            return false;
        }else{
            winMessage=gameMessage[0];
            if(gameMessage[1]!=null){
                haswon = true;
            }
        }

        printBoard();
        if (haswon){
            win(currentplaying, winMessage, System.out);
        }
        return true;
    }

    public String[] updateBoard(int FromX, int FromY, int ToX, int ToY, Animal animalFrom, Player currentPlaying, int currentPlayer, String fromObjectType, PrintStream out){
        String[] gameMessage = new String[2];
        boolean ateAnimal = false;
        String objType = board[ToX][ToY].getClass().getSimpleName();
        if (objType.equals("Animal")) {
            ateAnimal = true;
        }
        if (objType.equals("Trap")) {
            if(((Trap)board[ToX][ToY]).containAnimal()){
                ateAnimal = true;
            }
        }

        if (!ateAnimal) {
            if (objType.equals("Trap")) {
                Trap trap = (Trap) board[ToX][ToY];
                trap.setAnimal(animalFrom);
                //board[ToX][ToY] = new Animal(currentplaying, 0);
            }
            else if (objType.equals("River")){
                River river = (River) board[ToX][ToY];
                if (animalFrom.getRank()==1||animalFrom.getRank()==9){
                    river.setAnimal(new Animal(currentPlaying,9));
                    //board[ToX][ToY]=new Animal(currentplaying,9);
                }
                else {
                    out.println("Only a Rat(1) can get into river!");
                    return null;
                }
            }
            else if (objType.equals("Den")){
                Den den=(Den) board[ToX][ToY];
                if (den.getPlayer().getPlayer_id()==currentPlayer) {
                    out.println("You cannot go to your own Den!");
                    return null;
                }
                board[ToX][ToY]=animalFrom;
                gameMessage[0] = "Player" + currentPlayer + "'s " + animalFrom.getAnimalType() + " has reached Player" + (currentPlayer)%2 + "'s Den.";
                gameMessage[1] = "true";
            }
            else if (objType.equals("Land")){
                if (animalFrom.getRank()==9){
                    board[ToX][ToY]=new Animal(currentPlaying,1);
                }
                else{
                    board[ToX][ToY]=animalFrom;
                }
            }
        }
        else if(board[ToX][ToY] instanceof Animal){
            Animal animalTo = ((Animal) board[ToX][ToY]);
            out.println(currentPlaying.getPlayer_name() + "'s " + animalFrom.getAnimalType() + " has captured " +
                    animalTo.getPlayerName() + "'s " + animalTo.getAnimalType());
            board[ToX][ToY]= animalFrom;
            animalTo.getPlayer().animalEaten();
            if(animalTo.getPlayer().getAnimalLeft() == 0){
                gameMessage[0] = "Player" + currentPlayer + " has captured all of " + this.getPlayerByID((currentPlayer+1)%2) + "'s animals.";
                gameMessage[1] = "true";
            }
        }else{
            Animal animalTo = ((Trap) board[ToX][ToY]).getAnimal();
            out.println(currentPlaying.getPlayer_name() + "'s " + animalFrom.getAnimalType() + " has captured " +
                    animalTo.getPlayerName() + "'s " + animalTo.getAnimalType());
            Trap temp_trap = ((Trap) board[ToX][ToY]);
            temp_trap.setAnimal(animalFrom);
            animalTo.getPlayer().animalEaten();
            if(animalTo.getPlayer().getAnimalLeft() == 0){
                gameMessage[0] = "Player" + currentPlayer + " has captured all of " + this.getPlayerByID((currentPlayer+1)%2) + "'s animals.";
                gameMessage[1] = "true";
            }
        }

        if (((FromX==2&&FromY==8)||(FromX==4&&FromY==8)||(FromX==3&&FromY==7)||(FromX==2&&FromY==0)||(FromX==4&&FromY==0)||(FromX==3&&FromY==1))&&fromObjectType.equals("Trap")){
            ((Trap)board[FromX][FromY]).removeAnimal();
        }
        else if ((FromX==1&&FromY==3)||(FromX==2&&FromY==3)||(FromX==4&&FromY==3)||(FromX==5&&FromY==3)||
                (FromX==1&&FromY==4)||(FromX==2&&FromY==4)||(FromX==4&&FromY==4)||(FromX==5&&FromY==4)||
                (FromX==1&&FromY==5)||(FromX==2&&FromY==5)||(FromX==4&&FromY==5)||(FromX==5&&FromY==5)){
            ((River)board[FromX][FromY]).removeAnimal();
        }
        else board[FromX][FromY]=new Land();
        return gameMessage;
    }

    public boolean checkValidCapture(String toObjectType, int ToX, int ToY, Animal animalFrom, Player currentplaying, PrintStream out){
        if (toObjectType.equals("Animal") || (toObjectType.equals("Trap") && ((Trap)board[ToX][ToY]).containAnimal())){
            Animal animalTo;
            if(toObjectType.equals("Trap")){
                animalTo = ((Trap)board[ToX][ToY]).getAnimal();
            }else {
                animalTo = (Animal) board[ToX][ToY];
            }
            String capturable;
            if (animalTo.getPlayerId()!= currentplaying.getPlayer_id()) {
                if(toObjectType.equals("Trap") && !(animalTo.getPlayerId()==((Trap)board[ToX][ToY]).getPlayer().getPlayer_id())){
                    if(currentplaying == player1) {
                        capturable = Animal.capture(animalFrom, new Animal(player2, 0));
                    }else{
                        capturable = Animal.capture(animalFrom, new Animal(player1, 0));
                    }
                }else {
                    capturable = Animal.capture(animalFrom, animalTo);
                }
            }
            else {
                out.println("Invalid Move: It is occupied by your own Animal!");
                return false;
            }
            if (capturable!=null){
                out.println(""+capturable);
                return false;
            }
        }
        return true;
    }

    public Animal checkValidMovement(String fromObjectType, String toObjectType, int FromX, int FromY, int ToX, int ToY, int currentPlayer, Player currentPlaying, PrintStream out){
        boolean available = false, flag = false;
        Animal animalFrom = new Animal();
        if(fromObjectType.equals("Animal")) {
            animalFrom = (Animal) board[FromX][FromY];
        }else if(fromObjectType.equals("Trap")){
            if(((Trap) board[FromX][FromY]).containAnimal()){
                animalFrom = ((Trap) board[FromX][FromY]).getAnimal();
            }else{
                out.println("Please move an Animal but not a "+ board[FromX][FromY].getClass().getSimpleName());
                return null;
            }
        }else if(fromObjectType.equals("River")){
            if(((River) board[FromX][FromY]).containAnimal()){
                animalFrom = ((River) board[FromX][FromY]).getAnimal();
            }else{
                out.println("Please move an Animal but not a "+ board[FromX][FromY].getClass().getSimpleName());
                return null;
            }
        }else{
            out.println("Please move an Animal but not a "+ board[FromX][FromY].getClass().getSimpleName());
            return null;
        }
        if (!((ToX==FromX-1&&ToY==FromY) || (ToX==FromX+1&&ToY==FromY) || (ToX==FromX&&ToY==FromY+1) || (ToX==FromX&&ToY==FromY-1))){
            flag=false;
        }else available=true;
        boolean RatInRiver=false;
        if (animalFrom.getRank()==6||animalFrom.getRank()==7){
            if(!(ToX==FromX+3&&ToY==FromY)){
                flag=false;
            }
            else if(!((River)board[FromX+1][FromY]).containAnimal() && !((River)board[FromX+2][FromY]).containAnimal()){available=true;}
            else RatInRiver=true;
            if(!(ToX==FromX-3&&ToY==FromY)){
                flag=false;
            }else if (!((River)board[FromX-1][FromY]).containAnimal() && !((River)board[FromX-2][FromY]).containAnimal()){available=true;}
            else RatInRiver=true;
            if(!(ToX==FromX&&ToY==FromY+4)){
                flag=false;
            }else if (!((River)board[FromX][FromY+1]).containAnimal() && !((River)board[FromX][FromY+2]).containAnimal()
                    && !((River)board[FromX][FromY+3]).containAnimal()){available=true;}
            else RatInRiver=true;
            if(!(ToX==FromX&&ToY==FromY-4)){
                flag=false;
            }else if (!((River)board[FromX][FromY-1]).containAnimal() && !((River)board[FromX][FromY-2]).containAnimal()
                    && !((River)board[FromX][FromY-3]).containAnimal()){available=true;}
            else RatInRiver=true;
        }
        if (!flag&&!available){
            if (RatInRiver){
                out.println("The Rat in river is blocking the Jump!");
            }
            else out.println("Please move horizontally or vertically for one block only");
            return null;
        }
        if (animalFrom.getPlayerId()!=currentPlayer) {
            out.println("You have attempted to move Player" + animalFrom.getPlayerId() +"'s "+ animalFrom.getAnimalType() + "");
            out.println("Please move only your own's Animal");
            return null;
        }
        return animalFrom;
    }

    public ArrayList<Integer> checkValidCommand(String from, String to, PrintStream out){
        ArrayList<Integer> temp = new ArrayList<Integer>();

        if (from.charAt(0)<='g' && from.charAt(0)>='a'){
            temp.add(from.charAt(0)-'a');
        }
        if (from.charAt(1)<='9' && from.charAt(1)>='1') {
            temp.add(Character.getNumericValue(from.charAt(1))-1);
        }
        if (to.charAt(0)<='g' && to.charAt(0)>='a'){
            temp.add(to.charAt(0)-'a');
        }
        if (to.charAt(1)<='9' && to.charAt(1)>='1'){
            temp.add(Character.getNumericValue(to.charAt(1))-1);
        }
        boolean isEmpty = false;
        if(temp.size()<4){
            isEmpty = true;
            return new ArrayList<Integer>();
        }
        return temp;
    }

    public void win(Player player, String winMessage, PrintStream out){
        out.println(winMessage);
        out.println("As a result, player "+player.getPlayer_id()+" "+player.getPlayer_name()+" has won!");
        System.exit(1);
    }

    public void printBoard(){
        Printings.print_board(board);
    }
}
