import { playMove } from "../services/gameApi";


function Board({game , onGameUpdated}){
    const handleCellClick = async (row,col) => {
        try{
            const response = await playMove(row,col);
            onGameUpdated(response.data);


        }catch(error){
            console.error("Unable to play move", error);
        }
    };

    return (
        <div>
            {game.board.map((row, rowIndex) => (
                <div
                    key={rowIndex}
                    style={{
                        display: "flex",
                        justifyContent: "center"
                    }}
                >
                    {row.map((cell, colIndex) => (
                        <button
                            key={`${rowIndex}-${colIndex}`}
                            onClick={() => handleCellClick(rowIndex, colIndex)}
                            style={{
                                width: "80px",
                                height: "80px",
                                fontSize: "2rem"
                            }}
                        >
                            {cell === "EMPTY" ? "" : cell}
                        </button>
                    ))}
                </div>
            ))}
        </div>
    );

}

export default Board;