import java.util.Scanner;

public class MineSweeperGame {
    private final Grid grid;
    private final Scanner scanner;

    public MineSweeperGame(int gridSize, int mineCount, Scanner scanner) {
        this.grid = new Grid(gridSize, mineCount);
        this.scanner = scanner;
    }

    public void play() {
        System.out.println("Welcome to Minesweeper!");

        boolean win = false;

        while (!grid.isGameOver()) {
            win = handleUserInput();
        }

        if (win) {
            System.out.println("Congratulations, you have won the game!");
        } else {
            System.out.println("Game over! You hit a mine.");
        }
    }
    public static int getGridSizeInput(Scanner scanner) {
        int gridSize = 0;
        while (true) {
            System.out.print("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
            if (scanner.hasNextInt()) {
                gridSize = scanner.nextInt();
                if (gridSize < 2 || gridSize > 10) {
                    System.out.println("Grid size must be between 2 and 10.");
                } else {
                    break;
                }
            } else {
                System.out.println("Incorrect input.");
                scanner.nextLine();
            }
        }
        return gridSize;
    }

    public static int getMineCountInput(Scanner scanner, int maxMineCount) {
        int mineCount = 0;
        while (true) {
            System.out.print("Enter the number of mines to place on the grid (maximum is " + maxMineCount + "): ");
            if (scanner.hasNextInt()) {
                mineCount = scanner.nextInt();
                if (mineCount < 1 || mineCount > maxMineCount) {
                    System.out.println("Mine count must be between 1 and " + maxMineCount + ".");
                } else {
                    break;
                }
            } else {
                System.out.println("Incorrect input.");
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
        return mineCount;
    }

    public boolean handleUserInput() {
        System.out.println();
        System.out.print("Select a square to reveal (e.g. A1): ");
        String input = scanner.next().trim().toUpperCase();

        if (input.length() < 2) {
            System.out.println("Invalid input format. Try again.");
            return false;
        }

        char rowChar = input.charAt(0);
        int col = Integer.parseInt(input.substring(1)) - 1; // Convert column to 0-based index

        if (rowChar >= 'A' && rowChar < 'A' + grid.getSize() && col >= 0 && col < grid.getSize()) {
            int row = rowChar - 'A';

            if (grid.uncoverCell(row, col)) {
                grid.printGrid(true); // Print the grid with all cells revealed
                System.out.println("Oh no, you detonated a mine! Game over.");
                System.out.println("Press any key to play again...");
                scanner.nextLine(); // Consume the newline character
                scanner.nextLine(); // Wait for user input
                grid.reset(grid.getSize(), grid.getMineCount()); // Reset the game and play again
                return true;
            } else {
                Cell cell = grid.getCells()[row][col];
                System.out.println("This square contains " + cell.getNeighboringMineCount() + " adjacent mines.");
                System.out.println();
                System.out.println("Here is your updated minefield:");
                grid.printGrid(false); // Print the grid with updated cell
                return false;
            }
        } else {
            System.out.println("Invalid input. Try again.");
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Minesweeper!");
        Scanner scanner = new Scanner(System.in);

        int gridSize = getGridSizeInput(scanner);
        int maxMineCount = (int) (gridSize * gridSize * 0.35);
        int mineCount = getMineCountInput(scanner, maxMineCount);

        System.out.println("Here is your minefield:");
        Grid initialGrid = new Grid(gridSize, mineCount);
        initialGrid.printGrid(false);

        MineSweeperGame game = new MineSweeperGame(gridSize, mineCount, scanner);

        while (true) {
            game.play();

            System.out.print("Press 'Y' to play again, or any other key to exit: ");
            String replay = scanner.next().trim().toLowerCase();
            if (!replay.equals("y")) {
                System.out.println("Thanks for playing Minesweeper!");
                scanner.close();
                System.exit(0);
            }
        }
    }
}
