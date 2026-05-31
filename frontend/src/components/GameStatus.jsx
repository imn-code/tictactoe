



function GameStatus({ status }) {
    const getMessage=()=>{
    switch (status) {
        case "IN_PROGRESS":
            return "Game in progress";
        case "PLAYER_WON":
            return "You won!";
        case "COMPUTER_WON":
            return "Computer won!";
        case "DRAW":
            return "Draw!";
        default:
            return "";
    }
};

    return (
        <h2>
            {getMessage()}
        </h2>
    );
}
export default GameStatus;