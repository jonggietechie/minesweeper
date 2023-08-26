# Minesweeper Game

## Overview

This is a simple implementation of the classic Minesweeper game in Java. The game provides a text-based interface where players can reveal cells on a grid while avoiding hidden mines. The objective is to uncover all non-mine cells without triggering any mines.

## Features

- Random placement of mines on the grid.
- Text-based user interface with customizable grid size and mine count.
- Revealing cells and tracking adjacent mine counts.
- Game over scenarios for hitting a mine or winning the game.

## Design and Assumptions
- The game is implemented using Java's object-oriented features.
- The Grid class handles the logic for placing mines and tracking cell states.
- The MineSweeperGame class manages the game flow and user interactions.
- The game uses a text-based interface, so ensure your terminal/console is capable of displaying characters.
- Unit tests are included to verify the correctness of game logic.

## Unit Tests
The application includes unit tests for testing game logic. These tests ensure that the game mechanics are functioning as expected. You can find the unit tests in the MineSweeperGameTest class.

## Environment

- Java Development Kit (JDK) 8 or later.
- Compatible with Windows, macOS, and Linux.

## Instructions

1. Clone the repository or download the code.
2. Compile the source code:

   ```shell
   javac *.java
   
3. Run the Minesweeper game:
    ```shell
   java MineSweeperGame

4. Follow the prompts to enter the grid size and mine count.

5. Enter coordinates to reveal cells, such as A1, B3, etc.

6. If you reveal a mine, the game ends.

7. If you uncover all non-mine cells, you win the game.

## Notes
- The game uses a text-based interface, so ensure your terminal/console is capable of displaying characters.
- Adjust the grid size and mine count according to your preference.

## Sample Output
```
Welcome to Minesweeper!
Enter the size of the grid (e.g. 4 for a 4x4 grid): 5
Enter the number of mines to place on the grid (maximum is 8): 4
Here is your minefield:
  1 2 3 4 5 
A _ _ _ _ _ 
B _ _ _ _ _ 
C _ _ _ _ _ 
D _ _ _ _ _ 
E _ _ _ _ _ 

Select a square to reveal (e.g. A1): A1
This square contains 0 adjacent mines.

Here is your updated minefield:
  1 2 3 4 5 
A 0 0 0 0 0 
B 0 0 0 1 1 
C 1 1 2 2 _ 
D _ _ _ _ _ 
E _ _ _ _ _ 
```