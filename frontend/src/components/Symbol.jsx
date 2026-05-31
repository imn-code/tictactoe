import { startGame } from "../services/gameApi";



function Symbol({ onGameStarted }) {
    const handleSelect = async (symbol) => {


        try {
            const response = await startGame(symbol);
            onGameStarted(response.data);

        } catch (error) {
            console.error("Unable to start game", error);
        }
    };

    return (
        <div>
            <h2>Choose your symbol</h2>

            <button onClick={() => handleSelect("X")}>
                X
            </button>

            <button onClick={() => handleSelect("O")}>
                O
            </button>
        </div>
    );
}


export default Symbol;