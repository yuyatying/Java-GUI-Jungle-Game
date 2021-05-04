package view;

import model.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
/**
 * the class JungleGameGraphic is used to start a JungleGame in GUI
 */
public class JungleGameGraphic extends Application implements EventHandler<ActionEvent> {
    /**
     *  main to start the GUI
     * @param args GUI
     */
    public static void main(String[] args) {
        launch(args);
    }
    private int count=0;
    private int currentPlayer = 1;
    private int FromX,FromY,ToX,ToY;
    private BoardObject[][] board;
    private Player player1 = new Player("Player1", 1);
    private Player player2 = new Player("Player2", 2);
    private Board newboard = new Board(player1, player2);
    private Button[][] gameboard = new Button[7][9];
    private GridPane gamegrid = new GridPane();
    private GridPane gamegridText =new GridPane();
    @Override
    public void start(Stage Junglegame) {
        GridPane gamegridmain= new GridPane();
        Scene mainscene;
        Scene rulescene;
        Scene newgamescene;
        final int NORMAL_SPACING=10;
        final int BIG_FONT_SIZE=26;
        final int SMALL_FONT_SIZE=18;
        VBox mainlayout = new VBox(NORMAL_SPACING);
        VBox rulelayout = new VBox(NORMAL_SPACING*2);
        ByteArrayOutputStream pipe = new ByteArrayOutputStream();
        PrintStream buffer = new PrintStream(pipe);
        Button gameruleBut;
        Button bkToGameBut;
        Button newgamebutton;
        Button exitgamebutton;
        Button menubutton;
        Button gameruleBut2;
        Image gamestart = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/GameStart.png"));
        ImageView gsimgview = new ImageView(gamestart);
        final int width = 1080;
        final int height = 720;
        gamegrid.setPadding(new Insets(10, 10, 10, 10));
        gamegridmain.setPadding(new Insets(10, 10, 10, 10));
        gamegridText.setPadding(new Insets(10, 10, 10, 10));
        mainlayout.setAlignment(Pos.BASELINE_CENTER);
        newgamebutton = new Button("New Game");
        gameruleBut = new Button("Show Gamerules");
        exitgamebutton = new Button("Exit");
        gameruleBut2 = new Button("Show Gamerules");
        menubutton = new Button("Return to Menu/Game");
        mainlayout.getChildren().addAll(gsimgview, newgamebutton, gameruleBut, exitgamebutton);
        mainscene = new Scene(mainlayout, width, height);
        System.setOut(buffer);
        Printings.gamerule();
        Text gamerule = new Text(pipe.toString());
        rulelayout.getChildren().addAll(gamerule, menubutton);
        rulescene = new Scene(rulelayout, width, height);
        newgamescene = new Scene(gamegridmain, width, height);

        newgamebutton.setOnAction(e -> {
            Junglegame.setScene(newgamescene);
        });

        menubutton.setOnAction(e -> {
            Junglegame.setScene(mainscene);
        });
        gameruleBut.setOnAction(e -> Junglegame.setScene(rulescene));
        exitgamebutton.setOnAction(e -> System.exit(1));

        bkToGameBut = new Button("Back To Menu");
        bkToGameBut.setOnAction(e -> {
            Junglegame.setScene(mainscene);
        });


        gameruleBut2.setOnAction(e -> {
            Junglegame.setScene(rulescene);
        });
        //
        board = newboard.returnBoard();
        updateboard();

        gamegridmain.add(gamegrid,0,1);
        Text p1=new Text("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nPlayer1");
        p1.setFont(Font.font("Verdana",BIG_FONT_SIZE));
        p1.setFill(Color.BLACK);
        Text p2=new Text("Player2");
        p2.setFont(Font.font("Verdana",BIG_FONT_SIZE));
        p2.setFill(Color.RED);
        Text cp=new Text("Player 1 move first");
        cp.setFont(Font.font("Verdana",SMALL_FONT_SIZE));
        gamegridText.add(p1,0,2);
        gamegridText.add(cp,0,1);
        gamegridText.add(p2,0,0);
        gamegridmain.add(gamegridText,1,1);

        Junglegame.setScene(mainscene);
        Junglegame.setTitle("Jungle Game");
        Junglegame.show();
    }
    private void updateboard() {
        final double squareSize = 65;
        Image catB = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/CatBlack.png"), squareSize, squareSize, true, true);
        Image dogB = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/DogBlack.png"), squareSize, squareSize, true, true);
        Image elephantB = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/ElephantBlack.png"), squareSize, squareSize, true, true);
        Image leopardB = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/LeopardBlack.png"), squareSize, squareSize, true, true);
        Image lionB = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/LionBlack.png"), squareSize, squareSize, true, true);
        Image mouseB = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/MouseBlack.png"), squareSize, squareSize, true, true);
        Image RivermouseB = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/RiverMouseBlack.png"), squareSize, squareSize, true, true);
        Image tigerB = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/TigerBlack.png"), squareSize, squareSize, true, true);
        Image wolfB = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/WolfBlack.png"), squareSize, squareSize, true, true);
        Image TrappedB = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/TrappedB.png"), squareSize, squareSize, true, true);
        Image catR = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/CatRed.png"), squareSize, squareSize, true, true);
        Image dogR = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/DogRed.png"), squareSize, squareSize, true, true);
        Image elephantR = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/ElephantRed.png"), squareSize, squareSize, true, true);
        Image leopardR = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/LeopardRed.png"), squareSize, squareSize, true, true);
        Image lionR = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/LionRed.png"), squareSize, squareSize, true, true);
        Image mouseR = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/MouseRed.png"), squareSize, squareSize, true, true);
        Image RivermouseR = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/RiverMouseRed.png"), squareSize, squareSize, true, true);
        Image tigerR = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/TigerRed.png"), squareSize, squareSize, true, true);
        Image wolfR = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/WolfRed.png"), squareSize, squareSize, true, true);
        Image TrappedR = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/TrappedR.png"), squareSize, squareSize, true, true);
        Image ground = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/Ground.png"), squareSize, squareSize, true, true);
        Image DenR = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/DenRed.png"), squareSize, squareSize, true, true);
        Image DenB = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/DenBlack.png"), squareSize, squareSize, true, true);
        Image TrapR = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/TrapRed.png"), squareSize, squareSize, true, true);
        Image TrapB = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/TrapBlack.png"), squareSize, squareSize, true, true);
        Image River = new Image(JungleGameGraphic.class.getResourceAsStream("Chess/River.png"), squareSize, squareSize, true, true);
        for (int y = 8; y >= 0; y--) {
            for (int x = 0; x < 7; x++) {
                gameboard[x][y] = new Button();
                gameboard[x][y].setOnAction(this);
                if (board[x][y].getClass().getSimpleName().equals("Animal")) {
                    Animal dummy = (Animal) board[x][y];
                    if (dummy.getRank() == 1 && dummy.getPlayerId() == 2) {
                        gameboard[x][y].setGraphic(new ImageView(mouseR));
                    } else if (dummy.getRank() == 2 && dummy.getPlayerId() == 2) {
                        gameboard[x][y].setGraphic(new ImageView(catR));
                    } else if (dummy.getRank() == 3 && dummy.getPlayerId() == 2) {
                        gameboard[x][y].setGraphic(new ImageView(dogR));
                    } else if (dummy.getRank() == 4 && dummy.getPlayerId() == 2) {
                        gameboard[x][y].setGraphic(new ImageView(wolfR));
                    } else if (dummy.getRank() == 5 && dummy.getPlayerId() == 2) {
                        gameboard[x][y].setGraphic(new ImageView(leopardR));
                    } else if (dummy.getRank() == 6 && dummy.getPlayerId() == 2) {
                        gameboard[x][y].setGraphic(new ImageView(tigerR));
                    } else if (dummy.getRank() == 7 && dummy.getPlayerId() == 2) {
                        gameboard[x][y].setGraphic(new ImageView(lionR));
                    } else if (dummy.getRank() == 8 && dummy.getPlayerId() == 2) {
                        gameboard[x][y].setGraphic(new ImageView(elephantR));
                    }
                    else if (dummy.getRank() == 9 && dummy.getPlayerId() == 2) {
                        gameboard[x][y].setGraphic(new ImageView(RivermouseR));
                    }

                    else if (dummy.getRank() == 0 && dummy.getPlayerId() == 2) {
                        gameboard[x][y].setGraphic(new ImageView(TrappedB));
                    }
                    else if (dummy.getRank() == 1 && dummy.getPlayerId() == 1) {
                        gameboard[x][y].setGraphic(new ImageView(mouseB));
                    } else if (dummy.getRank() == 2 && dummy.getPlayerId() == 1) {
                        gameboard[x][y].setGraphic(new ImageView(catB));
                    } else if (dummy.getRank() == 3 && dummy.getPlayerId() == 1) {
                        gameboard[x][y].setGraphic(new ImageView(dogB));
                    } else if (dummy.getRank() == 4 && dummy.getPlayerId() == 1) {
                        gameboard[x][y].setGraphic(new ImageView(wolfB));
                    } else if (dummy.getRank() == 5 && dummy.getPlayerId() == 1) {
                        gameboard[x][y].setGraphic(new ImageView(leopardB));
                    } else if (dummy.getRank() == 6 && dummy.getPlayerId() == 1) {
                        gameboard[x][y].setGraphic(new ImageView(tigerB));
                    } else if (dummy.getRank() == 7 && dummy.getPlayerId() == 1) {
                        gameboard[x][y].setGraphic(new ImageView(lionB));
                    } else if (dummy.getRank() == 8 && dummy.getPlayerId() == 1) {
                        gameboard[x][y].setGraphic(new ImageView(elephantB));
                    } else if (dummy.getRank() == 9 && dummy.getPlayerId() == 1) {
                        gameboard[x][y].setGraphic(new ImageView(RivermouseB));
                    }else if (dummy.getRank() == 0 && dummy.getPlayerId() == 1){
                        gameboard[x][y].setGraphic(new ImageView(TrappedR));
                    }
                } else if (board[x][y].getClass().getSimpleName().equals("Trap")) {
                    Trap dummy = (Trap) board[x][y];
                    if (!dummy.containAnimal()) {
                        if (dummy.getPlayer().getPlayer_id() == 1) {
                            gameboard[x][y].setGraphic(new ImageView(TrapB));
                        } else if (dummy.getPlayer().getPlayer_id() == 2) {
                            gameboard[x][y].setGraphic(new ImageView(TrapR));

                        }
                    }
                    else {
                        Animal dummyT=dummy.getAnimal();
                        if (dummyT.getRank() == 1 && dummyT.getPlayerId() == 2) {
                            gameboard[x][y].setGraphic(new ImageView(mouseR));
                        } else if (dummyT.getRank() == 2 && dummyT.getPlayerId() == 2) {
                            gameboard[x][y].setGraphic(new ImageView(catR));
                        } else if (dummyT.getRank() == 3 && dummyT.getPlayerId() == 2) {
                            gameboard[x][y].setGraphic(new ImageView(dogR));
                        } else if (dummyT.getRank() == 4 && dummyT.getPlayerId() == 2) {
                            gameboard[x][y].setGraphic(new ImageView(wolfR));
                        } else if (dummyT.getRank() == 5 && dummyT.getPlayerId() == 2) {
                            gameboard[x][y].setGraphic(new ImageView(leopardR));
                        } else if (dummyT.getRank() == 6 && dummyT.getPlayerId() == 2) {
                            gameboard[x][y].setGraphic(new ImageView(tigerR));
                        } else if (dummyT.getRank() == 7 && dummyT.getPlayerId() == 2) {
                            gameboard[x][y].setGraphic(new ImageView(lionR));
                        } else if (dummyT.getRank() == 8 && dummyT.getPlayerId() == 2) {
                            gameboard[x][y].setGraphic(new ImageView(elephantR));
                        }
                        else if (dummyT.getRank() == 9 && dummyT.getPlayerId() == 2) {
                            gameboard[x][y].setGraphic(new ImageView(RivermouseR));
                        }

                        else if (dummyT.getRank() == 0 && dummyT.getPlayerId() == 2) {
                            gameboard[x][y].setGraphic(new ImageView(TrappedB));
                        }
                        else if (dummyT.getRank() == 1 && dummyT.getPlayerId() == 1) {
                            gameboard[x][y].setGraphic(new ImageView(mouseB));
                        } else if (dummyT.getRank() == 2 && dummyT.getPlayerId() == 1) {
                            gameboard[x][y].setGraphic(new ImageView(catB));
                        } else if (dummyT.getRank() == 3 && dummyT.getPlayerId() == 1) {
                            gameboard[x][y].setGraphic(new ImageView(dogB));
                        } else if (dummyT.getRank() == 4 && dummyT.getPlayerId() == 1) {
                            gameboard[x][y].setGraphic(new ImageView(wolfB));
                        } else if (dummyT.getRank() == 5 && dummyT.getPlayerId() == 1) {
                            gameboard[x][y].setGraphic(new ImageView(leopardB));
                        } else if (dummyT.getRank() == 6 && dummyT.getPlayerId() == 1) {
                            gameboard[x][y].setGraphic(new ImageView(tigerB));
                        } else if (dummyT.getRank() == 7 && dummyT.getPlayerId() == 1) {
                            gameboard[x][y].setGraphic(new ImageView(lionB));
                        } else if (dummyT.getRank() == 8 && dummyT.getPlayerId() == 1) {
                            gameboard[x][y].setGraphic(new ImageView(elephantB));
                        } else if (dummyT.getRank() == 9 && dummyT.getPlayerId() == 1) {
                            gameboard[x][y].setGraphic(new ImageView(RivermouseB));
                        }else if (dummyT.getRank() == 0 && dummyT.getPlayerId() == 1){
                            gameboard[x][y].setGraphic(new ImageView(TrappedR));
                        }
                    }
                } else if (board[x][y].getClass().getSimpleName().equals("Den")) {
                    Den dummy = (Den) board[x][y];
                    if (dummy.getPlayer().getPlayer_id() == 1) {
                        gameboard[x][y].setGraphic(new ImageView(DenB));

                    } else if (dummy.getPlayer().getPlayer_id() == 2) {
                        gameboard[x][y].setGraphic(new ImageView(DenR));

                    }
                } else if (board[x][y].getClass().getSimpleName().equals("River")) {
                    gameboard[x][y].setGraphic(new ImageView(River));

                } else {
                    gameboard[x][y].setGraphic(new ImageView(ground));
                }
            }
        }
        for (int y = 8; y >= 0; y--) {
            for (int x = 0; x < 7; x++) {
                gamegrid.add(gameboard[x][y], x, 9 - y, 1, 1);
            }
        }
    }

    @Override
    public void handle(ActionEvent event) {
        buttonpressed(event);
    }
    private void buttonpressed(ActionEvent event){
        final int BIG_FONT_SIZE=26;
        final int SMALL_FONT_SIZE=18;
        Text alert;
        String dummy="";
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 9; y++) {
                if (event.getSource() == gameboard[x][y]) {
                    gameboard[x][y].setStyle("-fx-background-color: red;");
                    if (count==0) {
                        FromX=x;
                        FromY=y;
                        count += 1;
                    }
                    else if (count==1) {
                        ToX=x;
                        ToY=y;
                        alert=Move();
                        gamegridText.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 0);
                        gamegridText.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 2);
                        Text p1=new Text("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nPlayer1");
                        p1.setFont(Font.font("Verdana",BIG_FONT_SIZE));
                        p1.setFill(Color.BLACK);
                        Text p2=new Text("Player2");
                        p2.setFont(Font.font("Verdana",BIG_FONT_SIZE));
                        p2.setFill(Color.RED);
                        gamegridText.add(p1,0,2);
                        gamegridText.add(p2,0,0);
                        if (dummy!=alert.getText()) {
                            gamegridText.getChildren().removeIf(node -> GridPane.getRowIndex(node) == 1);
                            if (currentPlayer==2){
                                alert.setFill(Color.RED);
                            }
                            alert.setFont(Font.font("Verdana",SMALL_FONT_SIZE));
                            gamegridText.add(alert, 0, 1);
                        }
                        dummy=alert.getText();
                        updateboard();
                        count=0;
                    }
                }
            }
        }
    }
    private Text Move (){
        Text alert;
        Boolean flag=true;
        Boolean haswon=false;
        Player currentplaying;
        ByteArrayOutputStream pipe = new ByteArrayOutputStream();
        PrintStream buffer = new PrintStream(pipe);
        System.setOut(buffer);

        System.out.println("Current Player: "+currentPlayer);
        if (currentPlayer==1){
            currentplaying=player1;
        }
        else currentplaying=player2;
        alert = new Text("Current Player: "+currentPlayer+"\n\n\n\n");
        if ((!(board[FromX][FromY] instanceof Animal))&&!(board[FromX][FromY].getClass().getSimpleName().equals("Trap"))){
            System.out.println("Please move an Animal but not a "+ board[FromX][FromY].getClass().getSimpleName()+"\n\n");
            alert = new Text(pipe.toString());
            return alert;
        }
        Animal animalFrom;
        if((board[FromX][FromY].getClass().getSimpleName().equals("Trap"))){
            if (((Trap)board[FromX][FromY]).containAnimal()){
                animalFrom=((Trap)board[FromX][FromY]).getAnimal();
        }
            else {
                System.out.println("The Trap is Empty!"+"\n\n");
                alert = new Text(pipe.toString());
                return alert;}
        }
        else animalFrom=(Animal)board[FromX][FromY];
        Boolean available=false;
        if (!(ToX==FromX-1&&ToY==FromY)){
            flag=false;
        }else available=true;
        if (!(ToX==FromX+1&&ToY==FromY)){
            flag=false;
        }else available=true;
        if (!(ToX==FromX&&ToY==FromY+1)){
            flag=false;
        }else available=true;
        if (!(ToX==FromX&&ToY==FromY-1)){
            flag=false;
        }else available=true;
        Boolean RatInRiver=false;
        if (animalFrom.getRank()==6||animalFrom.getRank()==7){
            if(!(ToX==FromX+3&&ToY==FromY)){
                flag=false;
            }
            else if(board[FromX+1][FromY] instanceof River&&board[FromX+2][FromY]instanceof River){available=true;}
            else RatInRiver=true;
            if(!(ToX==FromX-3&&ToY==FromY)){
                flag=false;
            }else if (board[FromX-1][FromY] instanceof River&& board[FromX-2][FromY] instanceof River){available=true;}
            else RatInRiver=true;
            if(!(ToX==FromX&&ToY==FromY+4)){
                flag=false;
            }else if (board[FromX][FromY+1] instanceof River&& board[FromX][FromY+2] instanceof River
                    &&board[FromX][FromY+3] instanceof River){available=true;}
            else RatInRiver=true;
            if(!(ToX==FromX&&ToY==FromY-4)){
                flag=false;
            }else if (board[FromX][FromY-1] instanceof River&& board[FromX][FromY-2] instanceof River
                    &&board[FromX][FromY-3] instanceof River){available=true;}
            else RatInRiver=true;
        }
        if (!flag&&!available){
            if (RatInRiver){
                System.out.println("The Rat in river is blocking the Jump!\n\n");
                alert = new Text(pipe.toString());
                return alert;
            }
            else System.out.println("Please move horizontally or \nvertically for one block only\n");alert = new Text(pipe.toString());
            return alert;
        }
        if (animalFrom.getPlayerId()!=currentPlayer) {
            System.out.println("It is Player" + currentPlayer+ "'s Turn");
            System.out.println("Please Move own's Animal\n");
            alert = new Text(pipe.toString());
            return alert;
        }
        if (board[ToX][ToY].getClass().getSimpleName().equals("Animal")){
            Animal animalTo=(Animal)board[ToX][ToY];
            String capturable;
            if (animalTo.getPlayerId()!=currentPlayer) {
                capturable=Animal.capture(animalFrom,animalTo);
            }
            else {
                System.out.println("Invalid Move: It is occupied by your own Animal!\n\n");
                alert = new Text(pipe.toString());
                return alert;
            }
            if (capturable!=null){
                System.out.println(""+capturable+"\n\n");
                alert = new Text(pipe.toString());
                return alert;
            }
        }
        if (!(board[ToX][ToY].getClass().getSimpleName().equals("Animal"))) {
            if (board[ToX][ToY].getClass().getSimpleName().equals("Trap")) {
                Trap trap = (Trap) board[ToX][ToY];
                trap.setAnimal(animalFrom);
            }
            if (board[ToX][ToY].getClass().getSimpleName().equals("Land")){
                if (animalFrom.getRank()==9){
                    board[ToX][ToY]=new Animal(currentplaying,1);
                }
                else if (board[FromX][FromY].getClass().getSimpleName().equals("Trap")){
                    board[ToX][ToY]=((Trap)board[FromX][FromY]).getAnimal();
                }
                else board[ToX][ToY]=board[FromX][FromY];
            }
            if (board[ToX][ToY].getClass().getSimpleName().equals("River")){
                if (animalFrom.getRank()==1||animalFrom.getRank()==9){
                    board[ToX][ToY]=new Animal(currentplaying,9);
                }
                else {
                    System.out.println("Only a Rat(1) can get into river!\n\n");
                    alert = new Text(pipe.toString());

                    return alert;
                }
            }
            if (board[ToX][ToY].getClass().getSimpleName().equals("Den")){
                Den den=(Den) board[ToX][ToY];
                if (den.getPlayer().getPlayer_id()==currentPlayer) {
                    System.out.println("You cannot go to your own Den!\n\n");
                    alert = new Text(pipe.toString());
                    return alert;
                }
                board[ToX][ToY]=board[FromX][FromY];
                haswon=true;
            }
        }
        else if (board[FromX][FromY].getClass().getSimpleName().equals("Trap")){
            board[ToX][ToY]=((Trap)board[FromX][FromY]).getAnimal();
        }
        else board[ToX][ToY]=board[FromX][FromY];
        if ((FromX==2&&FromY==8)||(FromX==4&&FromY==8)||(FromX==3&&FromY==7)){
            //board[FromX][FromY]=new Trap(player2);
            ((Trap)board[FromX][FromY]).removeAnimal();
        }
        else if ((FromX==2&&FromY==0)||(FromX==4&&FromY==0)||(FromX==3&&FromY==1)){
            //board[FromX][FromY]=new Trap(player1);
            ((Trap)board[FromX][FromY]).removeAnimal();
        }
        else if ((FromX==1&&FromY==3)||(FromX==2&&FromY==3)||(FromX==4&&FromY==3)||(FromX==5&&FromY==3)||
                (FromX==1&&FromY==4)||(FromX==2&&FromY==4)||(FromX==4&&FromY==4)||(FromX==5&&FromY==4)||
                (FromX==1&&FromY==5)||(FromX==2&&FromY==5)||(FromX==4&&FromY==5)||(FromX==5&&FromY==5)){
            board[FromX][FromY]=new River();
        }
        else board[FromX][FromY]=new Land();
        if (haswon){
            System.out.println("Player "+currentPlayer+" "+" has won!\n\n");
            System.exit(1);
        }
        if (currentPlayer==1){
            currentPlayer=2;
        }
        else currentPlayer=1;
        alert = new Text("Current Player: "+currentPlayer+"\n\n\n\n");
        return alert;
    }
}



