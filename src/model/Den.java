package model;

public class Den implements BoardObject {
    private Player player;

    public Den(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String[] toString(String[] printMessage){
        int playerId = this.getPlayer().getPlayer_id();
        printMessage[0]+="DDDDDDD";
        printMessage[1]+="D     D";
        printMessage[2]+="D  " + playerId + "  D";
        printMessage[3]+="D     D";
        printMessage[4]+="DDDDDDD";
        return printMessage;
    }
}
