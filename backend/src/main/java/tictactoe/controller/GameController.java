package tictactoe.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tictactoe.entities.Game;
import tictactoe.entities.dtos.MoveRequest;
import tictactoe.entities.dtos.StartGameRequest;
import tictactoe.service.GameService;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "http://localhost:5173")
public class GameController {

    private final GameService service ; 

    public GameController(GameService gameService) {
        this.service = gameService;
    }


    @PostMapping("/start")
    public Game startGame(@RequestBody StartGameRequest request) {
        return service.startGame(request.getPlayerSymbol());
    }

    @PostMapping("/move")
    public Game playMove(@RequestBody MoveRequest request) {
        return service.playMove(
                request.getRow(),
                request.getCol()
        );
    }
}
