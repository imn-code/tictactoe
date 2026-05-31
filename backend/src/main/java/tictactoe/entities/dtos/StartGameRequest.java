package tictactoe.entities.dtos;

import tictactoe.entities.Symbol;

public class StartGameRequest {
     private Symbol playerSymbol;

    public Symbol getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(Symbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

}
