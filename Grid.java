import java.util.Random;

public class Grid {
    private int size;
    private int mineCount;
    private Cell[][] cells;

    public Grid(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
        this.cells = new Cell[size][size];
        initializeCells();
        placeMines();
    }

    public int getSize(){
        return size;
    }

    public int getMineCount(){
        return mineCount;
    }
    public Cell[][] getCells() {
        return cells;
    }

    private void initializeCells() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                cells[row][col] = new Cell();
            }
        }
    }
    public void reset(int newSize, int newMineCount) {
        size = newSize;
        mineCount = newMineCount;
        cells = new Cell[size][size];
        initializeCells();
        placeMines();
    }

    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;

        while (minesPlaced < mineCount) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);

            if (!cells[row][col].isMine()) {
                cells[row][col].setMine();
                updateNeighboringCells(row, col);
                minesPlaced++;
            }
        }
    }
    // Update neighboring cells' mine count
    private void updateNeighboringCells(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < size && j >= 0 && j < size) {
                    cells[i][j].incrementNeighboringMineCount();
                }
            }
        }
    }

    public boolean uncoverCell(int row, int col) {
        if (cells[row][col].isMine()) {
            cells[row][col].uncover(); // Uncover the mine cell
            return true; // Return true if mine was triggered
        } else {
            cells[row][col].uncover(); // Uncover the non-mine cell
            if (cells[row][col].getNeighboringMineCount() == 0) {
                // Uncover adjacent cells recursively
                uncoverAdjacentCells(row, col);
            }
            if (isGameOver()) {
                gameOver(true); // Game over, player won
            }
            return false; // Return false if no mine was triggered
        }
    }

    private void uncoverAdjacentCells(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < size && j >= 0 && j < size && !cells[i][j].isUncovered()) {
                    cells[i][j].uncover();
                    if (cells[i][j].getNeighboringMineCount() == 0) {
                        uncoverAdjacentCells(i, j);
                    }
                }
            }
        }
    }

    public void printGrid(boolean reveal) {
        System.out.print("  ");
        for (int col = 0; col < size; col++) {
            System.out.print((col + 1) + " ");
        }
        System.out.println();

        char rowLabel = 'A';
        for (int row = 0; row < size; row++) {
            System.out.print(rowLabel + " ");
            rowLabel++;

            for (int col = 0; col < size; col++) {
                Cell cell = cells[row][col];
                if (cell.isUncovered() || reveal) {
                    if (cell.isMine()) {
                        System.out.print("* ");
                    } else {
                        int neighboringMineCount = cell.getNeighboringMineCount();
                        System.out.print(neighboringMineCount + " ");
                    }
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }

    public boolean isGameOver() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Cell cell = cells[row][col];
                if (!cell.isMine() && !cell.isUncovered()) {
                    return false; // There are still non-mine cells to uncover
                }
                if (cell.isMine() && cell.isUncovered()) {
                    return true; // A mine cell was uncovered, game over
                }
            }
        }
        return true; // All non-mine cells are uncovered, game won
    }
    private boolean gameOver(boolean win) {
        printGrid(true);
        return win;
    }
}