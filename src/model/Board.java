package model;

public class Board {
    private BoardObject[][] board = new BoardObject[7][9];

    public Board(Player player1, Player player2){
        initializeDefaultBoard(player1, player2);
    }

    public void initializeDefaultBoard(Player player1, Player player2){
        board[2][0] = new Trap(player1);
        board[4][0] = new Trap(player1);
        board[3][1] = new Trap(player1);
        board[3][0] = new Den(player1);

        board[2][8] = new Trap(player2);
        board[4][8] = new Trap(player2);
        board[3][7] = new Trap(player2);
        board[3][8] = new Den(player2);

        board[1][3] = new River();
        board[2][3] = new River();
        board[4][3] = new River();
        board[5][3] = new River();
        board[1][4] = new River();
        board[2][4] = new River();
        board[4][4] = new River();
        board[5][4] = new River();
        board[1][5] = new River();
        board[2][5] = new River();
        board[4][5] = new River();
        board[5][5] = new River();

        board[0][0] = new Animal(player1, Pieces.Tiger);
        board[6][0] = new Animal(player1, Pieces.Lion);
        board[1][1] = new Animal(player1, Pieces.Cat);
        board[5][1] = new Animal(player1, Pieces.Dog);
        board[0][2] = new Animal(player1, Pieces.Elephant);
        board[2][2] = new Animal(player1, Pieces.Wolf);
        board[4][2] = new Animal(player1, Pieces.Leopard);
        board[6][2] = new Animal(player1, Pieces.Rat);

        board[6][8] = new Animal(player2, Pieces.Tiger);
        board[0][8] = new Animal(player2, Pieces.Lion);
        board[5][7] = new Animal(player2, Pieces.Cat);
        board[1][7] = new Animal(player2, Pieces.Dog);
        board[6][6] = new Animal(player2, Pieces.Elephant);
        board[4][6] = new Animal(player2, Pieces.Wolf);
        board[2][6] = new Animal(player2, Pieces.Leopard);
        board[0][6] = new Animal(player2, Pieces.Rat);

        for(int x=0; x<7; x++){
            for(int y=0; y<9; y++){
                if(board[x][y] == null){
                    board[x][y] = new Land();
                }
            }
        }
    }

    public BoardObject[][] returnBoard(){
        return board;
    }
}
