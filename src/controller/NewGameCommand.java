package controller;

import model.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class NewGameCommand implements Command {
    @Override
    public JungleGame execute(InputStream in){
        Printings print = new Printings();
        String player1_name,player2_name;
        System.out.println("Please input Player names");
        System.out.print("Player 1: ");
        Scanner scan= new Scanner(in);
        player1_name=scan.nextLine();
        System.out.print("Player 2: ");
        player2_name=scan.nextLine();
        Player player1= new Player(player1_name, 1);
        Player player2= new Player(player2_name, 2);
        Board newboard=new Board(player1,player2);
        BoardObject[][] board;
        board=newboard.returnBoard();
        print.print_board(board);
        JungleGame junglegame = new JungleGame(player1, player2, board);
        return junglegame;
    }
}
