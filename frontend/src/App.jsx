import { useState } from "react";
import "./App.css";
import Symbol from "./components/Symbol";
import Board from "./components/Board";
import GameStatus from "./components/GameStatus";

function App() {
    const [game, setGame] = useState(null);

    const [score, setScore] = useState({
        player: 0,
        computer: 0,
        draw: 0
    });

    const updateGame = (updatedGame) => {
        setGame(updatedGame);

        if (updatedGame.status === "PLAYER_WON") {
            setScore((previousScore) => ({
                ...previousScore,
                player: previousScore.player + 1
            }));
        }

        if (updatedGame.status === "COMPUTER_WON") {
            setScore((previousScore) => ({
                ...previousScore,
                computer: previousScore.computer + 1
            }));
        }

        if (updatedGame.status === "DRAW") {
            setScore((previousScore) => ({
                ...previousScore,
                draw: previousScore.draw + 1
            }));
        }
    };

    const handleRestart = () => {
        setGame(null);
    };

    return (
        <main>
            <h1>Tic Tac Toe</h1>

            <div>
                <p>Player: {score.player}</p>
                <p>Computer: {score.computer}</p>
                <p>Draw: {score.draw}</p>
            </div>

            {!game && (
                <Symbol onGameStarted={setGame} />
            )}

            {game && (
                <>
                    <GameStatus status={game.status} />

                    <Board
                        game={game}
                        onGameUpdated={updateGame}
                    />

                    {game.status !== "IN_PROGRESS" && (
                        <button onClick={handleRestart}>
                            Play again
                        </button>
                    )}
                </>
            )}
        </main>
    );
}

export default App;