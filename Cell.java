public class Cell {
    private boolean isMine;
    private boolean isUncovered;
    private int neighboringMineCount;

    public Cell() {
        this.isMine = false;
        this.isUncovered = false;
        this.neighboringMineCount = 0;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine() {
        isMine = true;
    }

    public boolean isUncovered() {
        return isUncovered;
    }

    public void uncover() {
        isUncovered = true;
    }

    public int getNeighboringMineCount() {
        return neighboringMineCount;
    }

    public void incrementNeighboringMineCount() {
        neighboringMineCount++;
    }
}
