package controller;

import model.JungleGame;

import java.io.InputStream;
import java.io.PrintStream;

public class LoadCommand implements Command {
    private JungleGame jungleGame;

    public LoadCommand(JungleGame jungleGame){
        this.jungleGame = jungleGame;
    }

    @Override
    public JungleGame execute(InputStream in) {
        //TODO
        return jungleGame;
    }
}
