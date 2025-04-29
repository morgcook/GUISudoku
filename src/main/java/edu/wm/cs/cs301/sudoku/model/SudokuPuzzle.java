package edu.wm.cs.cs301.sudoku.model;

import java.awt.*;
import java.util.*;

/**
 *  This class holds all the functionality and logic for the puzzle itself,
 *      storing the puzzle in SudokuResponse objects to allow for simple
 *      interaction between the model and the view.
 */
public class SudokuPuzzle {
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 9;
    private final int ATTEMPTS = 1;
    private final Color BACKGROUND_COLOR = new Color(223, 223, 223);

    // the original version of the puzzle excluding valid player moves
    private final int[][] original = new int[NUM_ROWS][NUM_COLS];
    // the current version of the puzzle including valid player moves
    private final int[][] current = new int[NUM_ROWS][NUM_COLS];

    // the response 2D array to visualize the grid
    private SudokuResponse[][] grid = new SudokuResponse[NUM_ROWS][NUM_COLS];

    private int counter = 0;
    private int attempts = ATTEMPTS;

    public SudokuPuzzle() {
        initialize();
    }

    public SudokuResponse[][] getGrid() { return grid; }

    /**
     *  This method is used to get rid of the current puzzle and
     *      replace it with a completely new puzzle. Fundamentally
     *      a "reset" method.
     */
    public void initialize() {
        // the solution to the current puzzle
        int[][] solution = new int[NUM_ROWS][NUM_COLS];
        grid = new SudokuResponse[NUM_ROWS][NUM_COLS];
        attempts = ATTEMPTS;

        fillGrid(solution);
        deepCopy2D(solution, original);
        createPuzzle();
        deepCopy2D(original, current);
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (original[i][j] != 0) {
                    grid[i][j] = new SudokuResponse(original[i][j], BACKGROUND_COLOR);
                }
            }
        }
    }

    // following three functions based on given pseudocode
    private boolean fillGrid(int[][] board) {
        Integer[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if (board[row][col] == 0) {
                    Collections.shuffle(Arrays.asList(values));
                    for (int val : values) {
                        if (isSafe(board, row, col, val)) {
                            board[row][col] = val;
                            if (fillGrid(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean solveGrid(int[][] board) {
        Integer[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if (board[row][col] == 0) {
                    Collections.shuffle(Arrays.asList(values));
                    for (int val : values) {
                        if (isSafe(board, row, col, val)) {
                            board[row][col] = val;
                            if (solveGrid(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        counter++;
        return false;
    }

    private void createPuzzle() {
        int row, col;
        Random random = new Random();
        while (attempts > 0) {
            do {
                row = random.nextInt(NUM_ROWS);
                col = random.nextInt(NUM_COLS);
            } while (original[row][col] == 0);

            int backup = original[row][col];
            original[row][col] = 0;

            int[][] copyGrid = new int[NUM_ROWS][NUM_COLS];
            for (int i = 0; i < NUM_ROWS; i++) {
                System.arraycopy(original[i], 0, copyGrid[i], 0, original[0].length);
            }
            counter = 0;
            solveGrid(copyGrid);
            if (counter != 1) {
                original[row][col] = backup;
                attempts--;
            }
        }
    }

    // deep copies the contents of a 2d array to another
    private void deepCopy2D(int[][] source, int[][] destination) {
        for (int i = 0; i < NUM_ROWS; i++) {
            System.arraycopy(source[i], 0, destination[i], 0, source[0].length);
        }
    }

    // function for the execution of valid moves
    public boolean makeMove(int row, int col, int num) {
        // checks if the move is valid by sudoku rules and is not overwriting an original square
        //  and updates current board if it is a valid move
        if (original[row][col] == 0 && (num == 0 || isSafe(current, row, col, num))) {
            current[row][col] = num;
            grid[row][col] = num == 0 ? null : new SudokuResponse(num, Color.WHITE);
            return true;
        }
        return false;
    }

    // checks if move is valid by sudoku rules and by making sure inputs are within the correct bounds
    private static boolean isSafe(int[][] board, int row, int col, int num) {
        for (int x = 0; x < NUM_ROWS; x++) {
            if (row == -1 || col == -1 || num < 0 || num > 9 || board[row][x] == num || board[x][col] == num
                    || board[row - row % 3 + x / 3][col - col % 3 + x % 3] == num) {
                return false;
            }
        }
        return true;
    }

    // checks if board is full (completed)
    public boolean isFull() {
        for (int[] row : current) {
            for (int val : row) {
                if (val == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
