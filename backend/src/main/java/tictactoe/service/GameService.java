package tictactoe.service;

import tictactoe.entities.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    private static final int DEFAULT_SIZE = 3;
    private static final int DEFAULT_WIN_LENGTH = 3;

    private Game currentGame;
    private final Random random = new Random();

    

    public Game startGame(Symbol playerSymbol) {
        currentGame = new Game(DEFAULT_SIZE, DEFAULT_WIN_LENGTH, playerSymbol);
        return currentGame;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public Game playMove(int row, int col) {
        if (currentGame == null) {
            throw new IllegalStateException("No game started");
        }

        if (currentGame.getStatus() != GameStatus.IN_PROGRESS) {
            return currentGame;
        }

        if (!isValidMove(row, col)) {
            throw new IllegalArgumentException("Invalid move");
        }

        currentGame.getBoard()[row][col] = currentGame.getPlayerSymbol();

        updateGameStatus(currentGame.getPlayerSymbol(), GameStatus.PLAYER_WON);

        if (currentGame.getStatus() == GameStatus.IN_PROGRESS) {
            playComputerMove();
        }

        return currentGame;
    }

    private void playComputerMove() {
        List<int[]> emptyCells = getEmptyCells();

        if (emptyCells.isEmpty()) {
            currentGame.setStatus(GameStatus.DRAW);
            return;
        }

        int[] move = emptyCells.get(random.nextInt(emptyCells.size()));
        currentGame.getBoard()[move[0]][move[1]] = currentGame.getComputerSymbol();

        updateGameStatus(currentGame.getComputerSymbol(), GameStatus.COMPUTER_WON);
    }

    private boolean isValidMove(int row, int col) {
        int size = currentGame.getSize();

        return row >= 0
                && row < size
                && col >= 0
                && col < size
                && currentGame.getBoard()[row][col] == Symbol.EMPTY;
    }

    private List<int[]> getEmptyCells() {
        List<int[]> emptyCells = new ArrayList<>();

        for (int row = 0; row < currentGame.getSize(); row++) {
            for (int col = 0; col < currentGame.getSize(); col++) {
                if (currentGame.getBoard()[row][col] == Symbol.EMPTY) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }

        return emptyCells;
    }

    private void updateGameStatus(Symbol symbol, GameStatus winnerStatus) {
        if (hasWinner(symbol)) {
            currentGame.setStatus(winnerStatus);
        } else if (getEmptyCells().isEmpty()) {
            currentGame.setStatus(GameStatus.DRAW);
        }
    }

    private boolean hasWinner(Symbol symbol) {
        int size = currentGame.getSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (currentGame.getBoard()[row][col] == symbol) {
                    if (checkDirections(row, col, symbol)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean checkDirections(int row, int col, Symbol symbol) {
        return countSymbols(row, col, 0, 1, symbol) >= currentGame.getWinLength()   // horizontal
                || countSymbols(row, col, 1, 0, symbol) >= currentGame.getWinLength() // vertical
                || countSymbols(row, col, 1, 1, symbol) >= currentGame.getWinLength() // diagonale
                || countSymbols(row, col, 1, -1, symbol) >= currentGame.getWinLength(); // diagonale inverse
    }

    private int countSymbols(int row, int col, int rowDirection, int colDirection, Symbol symbol) {
        int count = 0;
        int size = currentGame.getSize();

        while (
                row >= 0 && row < size
                        && col >= 0 && col < size
                        && currentGame.getBoard()[row][col] == symbol
        ) {
            count++;
            row += rowDirection;
            col += colDirection;
        }

        return count;
    }

    public Game restartGame() {
        if (currentGame == null) {
            return startGame(Symbol.X);
        }

        return startGame(currentGame.getPlayerSymbol());
    }
}


