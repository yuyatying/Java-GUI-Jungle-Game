package view;

import controller.MoveBoardCommand;
import controller.NewGameCommand;
import model.JungleGame;
import model.Printings;

import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        String input;
        String[] command;
        int currentPlayer = 0;
        JungleGame jungleGame = new JungleGame();
        Scanner scan = new Scanner(System.in);
        while(true) {
            if(!jungleGame.isInitialized()){
                Printings.startmenu();
            }else{
                System.out.println();
                System.out.println("It is Player" + (currentPlayer + 1) + " " + jungleGame.getPlayerByID(currentPlayer+1).getPlayer_name() + "'s turn");
            }
            input = scan.nextLine();
            command = input.toLowerCase().split(" ");
            switch (command[0]) {
                case "new_game":
                    NewGameCommand newgame = new NewGameCommand();
                    jungleGame = newgame.execute(System.in);
                    break;
                case "move":
                    if(jungleGame.isInitialized()) {
                        MoveBoardCommand moveBoardCommand = new MoveBoardCommand(jungleGame, command, currentPlayer+1);
                        jungleGame = moveBoardCommand.execute(System.in);
                        if(moveBoardCommand.isSuccess()){
                            currentPlayer = (currentPlayer + 1)%2;
                        }
                    }
                    break;
                case "commands":
                    Printings.startmenu();
                    break;
                case "gamerule":
                    Printings.gamerule();
                    break;
                case "exit":
                    System.out.println("Thanks for playing");
                    System.exit(1);
                case "save":
                    break;
                case "open":
                    break;
                default:
                    System.out.println("There is no commands named " + input);
                    System.out.println("Please Input again");
                    break;
            }
            //PlayerInput.readinput();
            // start playing the game
        }
    }
}
