package controller;

import model.JungleGame;

import java.io.InputStream;
import java.io.PrintStream;

public interface Command {
    public JungleGame execute(InputStream in);
}
