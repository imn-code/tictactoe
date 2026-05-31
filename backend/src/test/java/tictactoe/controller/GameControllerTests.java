package tictactoe.controller;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import tictactoe.entities.Game;
import tictactoe.entities.Symbol;
import tictactoe.entities.dtos.MoveRequest;
import tictactoe.entities.dtos.StartGameRequest;
import tictactoe.service.GameService;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameController.class)
public class GameControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private GameService gameService;

    @Test
    void shouldStartGame() throws Exception {
        Game game = new Game(3, 3, Symbol.X);

        StartGameRequest request = new StartGameRequest();
        request.setPlayerSymbol(Symbol.X);

        when(gameService.startGame(Symbol.X)).thenReturn(game);

        mockMvc.perform(post("/api/game/start")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(3))
                .andExpect(jsonPath("$.winLength").value(3))
                .andExpect(jsonPath("$.playerSymbol").value("X"))
                .andExpect(jsonPath("$.computerSymbol").value("O"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    void shouldPlayMove() throws Exception {
        Game game = new Game(3, 3, Symbol.X);
        game.getBoard()[0][0] = Symbol.X;

        MoveRequest request = new MoveRequest();
        request.setRow(0);
        request.setCol(0);

        when(gameService.playMove(0, 0)).thenReturn(game);

        mockMvc.perform(post("/api/game/move")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.board[0][0]").value("X"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    void shouldGetCurrentGame() throws Exception {
        Game game = new Game(3, 3, Symbol.O);

        when(gameService.getCurrentGame()).thenReturn(game);

        mockMvc.perform(get("/api/game"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerSymbol").value("O"))
                .andExpect(jsonPath("$.computerSymbol").value("X"));
    }

    @Test
    void shouldRestartGame() throws Exception {
        Game game = new Game(3, 3, Symbol.X);

        when(gameService.restartGame()).thenReturn(game);

        mockMvc.perform(post("/api/game/restart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerSymbol").value("X"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }
}
