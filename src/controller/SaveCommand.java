package controller;

import model.JungleGame;

import java.io.InputStream;
import java.io.PrintStream;

public class SaveCommand implements Command {
    private  JungleGame jungleGame;

    public SaveCommand(JungleGame jungleGame){
        this.jungleGame = jungleGame;
    }

    @Override
    public JungleGame execute(InputStream in){
        //TODO
        return jungleGame;
    }

}
