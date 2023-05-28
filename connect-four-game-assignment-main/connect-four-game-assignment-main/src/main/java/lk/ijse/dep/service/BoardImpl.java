package lk.ijse.dep.service;

public class BoardImpl implements Board {

    private final Piece[][] pieces;
    private final BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];


        for (int i = 0; i < NUM_OF_COLS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {

                pieces[i][j] = Piece.EMPTY;

            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;
    }


    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if (pieces[col][i] == Piece.EMPTY) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        return findNextAvailableSpot(col) > -1;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i = 0; i < NUM_OF_COLS; i++) {
            if (findNextAvailableSpot(i) > -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col,Piece move) {
        int nextAvailableSpot = findNextAvailableSpot(col);
        if (nextAvailableSpot != -1) {
            pieces[col][nextAvailableSpot] = move;
        }
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row]=move;
    }

    @Override
    public Winner findWinner() {


// Check horizontally
        for (int row = 0; row < NUM_OF_ROWS; row++) {
            for (int col = 0; col < NUM_OF_COLS - 3; col++) {
                Piece current = pieces[col][row];
                if (current != Piece.EMPTY && current == pieces[col + 1][row] && current == pieces[col + 2][row] && current == pieces[col + 3][row]) {
                    return new Winner(current, col, row, col + 3, row);
                }
            }
        }

// Check vertically
        for (int col = 0; col < NUM_OF_COLS; col++) {
            for (int row = 0; row < NUM_OF_ROWS - 3; row++) {
                Piece current = pieces[col][row];
                if (current != Piece.EMPTY && current == pieces[col][row + 1] && current == pieces[col][row + 2] && current == pieces[col][row + 3]) {
                    return new Winner(current, col, row, col, row + 3);
                }
            }
        }

// No winner found
        return new Winner(Piece.EMPTY);
    }


}
