package tictactoe.entities;

public class Game {


    
        private int size;
        private int winLength;
        private Symbol[][] board;
        private Symbol playerSymbol;
        private Symbol computerSymbol;
        private GameStatus status;
    
        public Game(int size, int winLength, Symbol playerSymbol) {
            this.size = size;
            this.winLength = winLength;
            this.playerSymbol = playerSymbol;
            this.computerSymbol = playerSymbol == Symbol.X ? Symbol.O : Symbol.X;
            this.status = GameStatus.IN_PROGRESS;
            this.board = new Symbol[size][size];
    
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    board[row][col] = Symbol.EMPTY;
                }
            }
        }
    
        public int getSize() { return size; }
        public int getWinLength() { return winLength; }
        public Symbol[][] getBoard() { return board; }
        public Symbol getPlayerSymbol() { return playerSymbol; }
        public Symbol getComputerSymbol() { return computerSymbol; }
        public GameStatus getStatus() { return status; }
    
        public void setStatus(GameStatus status) {
            this.status = status;
        }
    
    
    
}

