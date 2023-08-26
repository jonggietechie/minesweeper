import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.*;

public class MineSweeperGameTest {


    //Testing Grid initialization and mine placement
    @Test
    public void testGridInitialization() {
        Grid grid = new Grid(5, 5);
        assertNotNull(grid.getCells());
        assertEquals(5, grid.getSize());
        assertEquals(5, grid.getMineCount());
    }

    @Test
    public void testMinePlacement() {
        Grid grid = new Grid(5, 5);
        int mineCount = 0;
        for (int row = 0; row < grid.getSize(); row++) {
            for (int col = 0; col < grid.getSize(); col++) {
                if (grid.getCells()[row][col].isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(5, mineCount);
    }
    // Testing Cell Uncovering
    @Test
    public void testUncoverNonMineCell() {
        Cell cell = new Cell();
        cell.uncover();
        assertFalse(cell.isMine());
        assertTrue(cell.isUncovered());
    }
    @Test
    public void testUncoverMineCell() {
        Cell cell = new Cell();
        cell.setMine();
        cell.uncover();
        assertTrue(cell.isMine());
        assertTrue(cell.isUncovered());
    }
    @Test
    public void testGetGridSizeInput() {
        String input = "5\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        int gridSize = MineSweeperGame.getGridSizeInput(scanner);

        assertEquals(5, gridSize);
    }

    @Test
    public void testGetMineCountInput() {
        String input = "3\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        int maxMineCount = 6;
        int mineCount = MineSweeperGame.getMineCountInput(scanner, maxMineCount);

        assertEquals(3, mineCount);
    }

    @Test
    public void testHandleUserInputInvalid() {
        String input = "X\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        MineSweeperGame game = new MineSweeperGame(5, 3, scanner);

        assertFalse(game.handleUserInput());
    }
}
