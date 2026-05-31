package tictactoe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tictactoe.entities.Game;
import tictactoe.entities.GameStatus;
import tictactoe.entities.Symbol;
import tictactoe.service.GameService;

public class GameServiceTests {


    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService();
    }

    @Test
    void shouldStartGameWithPlayerSymbolX() {
        Game game = gameService.startGame(Symbol.X);

        assertNotNull(game);
        assertEquals(3, game.getSize());
        assertEquals(3, game.getWinLength());
        assertEquals(Symbol.X, game.getPlayerSymbol());
        assertEquals(Symbol.O, game.getComputerSymbol());
        assertEquals(GameStatus.IN_PROGRESS, game.getStatus());
    }

    @Test
    void shouldStartGameWithPlayerSymbolO() {
        Game game = gameService.startGame(Symbol.O);

        assertEquals(Symbol.O, game.getPlayerSymbol());
        assertEquals(Symbol.X, game.getComputerSymbol());
    }

    @Test
    void shouldThrowExceptionWhenPlayingWithoutStartingGame() {
        assertThrows(IllegalStateException.class, () -> {
            gameService.playMove(0, 0);
        });
    }

    @Test
    void shouldPlayPlayerMoveAndComputerMove() {
        Game game = gameService.startGame(Symbol.X);

        gameService.playMove(0, 0);

        assertEquals(Symbol.X, game.getBoard()[0][0]);

        int occupiedCells = countOccupiedCells(game);
        assertEquals(2, occupiedCells);
    }

    @Test
    void shouldThrowExceptionWhenMoveIsOutsideBoard() {
        gameService.startGame(Symbol.X);

        assertThrows(IllegalArgumentException.class, () -> {
            gameService.playMove(5, 5);
        });
    }

    @Test
    void shouldThrowExceptionWhenCellIsAlreadyOccupied() {
        gameService.startGame(Symbol.X);

        gameService.playMove(0, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            gameService.playMove(0, 0);
        });
    }

    @Test
    void shouldRestartGameWithSamePlayerSymbol() {
        gameService.startGame(Symbol.O);
        gameService.playMove(0, 0);

        Game restartedGame = gameService.restartGame();

        assertEquals(Symbol.O, restartedGame.getPlayerSymbol());
        assertEquals(Symbol.X, restartedGame.getComputerSymbol());
        assertEquals(GameStatus.IN_PROGRESS, restartedGame.getStatus());
        assertEquals(0, countOccupiedCells(restartedGame));
    }

    private int countOccupiedCells(Game game) {
        int count = 0;

        for (int row = 0; row < game.getSize(); row++) {
            for (int col = 0; col < game.getSize(); col++) {
                if (game.getBoard()[row][col] != Symbol.EMPTY) {
                    count++;
                }
            }
        }

        return count;
    }
}
