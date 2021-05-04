package model;

public class Printings {
    public static void startmenu(){
        System.out.println("WELCOME TO JUNGLE GAME");
        System.out.println("Please Input Command to start the game:");
        System.out.println("Start New Game: new_game");
        System.out.println("Load Game: open [filePath]");
        System.out.println("In-game Command Lists: commands");
        System.out.println("Show Game rules: gamerule");
        System.out.println("Exit: exit");
    }
    public static void gamerule(){
        System.out.println("The goal is the reach the den");
        System.out.println("Each chess has its own rank(1-8)");
        System.out.println("Higher rank can capture smaller ones");
        System.out.println("Special rules: ");
        System.out.println("Rat(1) can capture Elephant(8)");
        System.out.println("Rat(1) can go into the river");
        System.out.println("Rat(1) cannot capture anything when moving");
        System.out.println("    through water/land");
        System.out.println("Tiger(6) and Lion(7) can jump over river,");
        System.out.println("    if there isn't any Rat(1) on the way");
        System.out.println("Any animals that go to the traps will become");
        System.out.println("    capturable by any other animals");
    }

    public static void gamemenu() {
        System.out.println("Command Lists:");
        System.out.println("Move a piece: move [fromPosition] [toPosition]");
        System.out.println("Save Game: save [filePath]");
        System.out.println("Load Game: open [filePath]");
        System.out.println("In-game Command Lists: commands");
    }

    public static void print_board(BoardObject[][] board){
        int x,y;
        for (y=8;y>=0;y--){
            String[] objects= new String[5];
            for (int i=0;i<5;i++)
                objects[i]="";
            for (x=0;x<7;x++) {
                String ObjectType = board[x][y].getClass().getSimpleName();
                objects = ((BoardObject)board[x][y]).toString(objects);
            }
            print_object(objects, y);
        }
        System.out.println();
        System.out.println("   A      B      C      D      E      F      G");
    }
    public static void print_object(String[] objects, int row){
        for (int i=0;i<5;i++) {
            if(i!=2) {
                System.out.println(objects[i]);
            }else{
                System.out.println(objects[i] + "  " + (row+1));
            }
        }
    }
}