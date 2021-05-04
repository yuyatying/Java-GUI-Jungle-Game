package controller;

import model.JungleGame;

import java.io.InputStream;
import java.io.PrintStream;

public class MoveBoardCommand implements Command{
    private JungleGame jungleGame;
    private String[] command;
    private int currentPlayer;
    private boolean success;

    public MoveBoardCommand(JungleGame jungleGame, String[] command, int currentPlayer){
        this.jungleGame = jungleGame;
        this.command = command;
        this.currentPlayer = currentPlayer;
        this.success = false;
    }

    @Override
    public JungleGame execute(InputStream in){
        if (jungleGame != null) {
            if (command.length == 3) {
                if (command[1].length() == 2 && command[2].length() == 2) {
                    if(jungleGame.Move(command[1], command[2], currentPlayer, System.out)) {
                        this.success = true;
                    }else{
                        System.out.println();
                        jungleGame.printBoard();
                    }
                }
            } else {
                System.out.println();
                System.out.println("Invalid Move Command Format");
                this.success = false;
                jungleGame.printBoard();
            }
        } else {
            System.out.println();
            System.out.println("Games not started yet!");
            this.success = false;
            jungleGame.printBoard();
        }
        return jungleGame;
    }

    public boolean isSuccess(){
        return success;
    }
}
