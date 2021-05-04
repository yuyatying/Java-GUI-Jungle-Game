package model;

@SuppressWarnings("JavaDoc")
public class Land implements BoardObject {

    @Override
    public String[] toString(String[] printMessage){
        printMessage[0]+="_______";
        printMessage[1]+="|     |";
        printMessage[2]+="|     |";
        printMessage[3]+="|     |";
        printMessage[4]+="_______";
        return printMessage;
    }
}