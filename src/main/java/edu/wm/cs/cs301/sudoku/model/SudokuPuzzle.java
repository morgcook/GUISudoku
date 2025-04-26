package edu.wm.cs.cs301.sudoku.model;

import java.awt.*;
import java.util.*;

public class SudokuPuzzle {
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 9;
    private final int ATTEMPTS = 1;

    // the original version of the puzzle excluding valid player moves
    private int[][] original = new int[NUM_ROWS][NUM_COLS];
    // the current version of the puzzle including valid player moves
    private int[][] current = new int[NUM_ROWS][NUM_COLS];
    // the solution to the current puzzle
    private int[][] solution = new int[NUM_ROWS][NUM_COLS];

    // the response 2D array to visualize the grid
    private SudokuResponse[][] grid = new SudokuResponse[NUM_ROWS][NUM_COLS];

    private int counter = 0;
    private int attempts = ATTEMPTS;

    public SudokuPuzzle() {
        initialize();
    }

    public int[][] getOriginal() {
        return original;
    }

    public int[][] getCurrent() {
        return current;
    }

    public int[][] getSolution() {
        return solution;
    }

    public SudokuResponse[][] getGrid() { return grid; }

    public void initialize() {
        solution = new int[NUM_ROWS][NUM_COLS];
        grid = new SudokuResponse[NUM_ROWS][NUM_COLS];
        attempts = ATTEMPTS;

        fillGrid(solution);
        deepCopy2D(solution, original);
        createPuzzle();
        deepCopy2D(original, current);
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (original[i][j] != 0) {
                    grid[i][j] = new SudokuResponse(original[i][j], Color.LIGHT_GRAY);
                }
            }
        }
    }

    // prints the 2d array of the inputted board, with the option of colored user-inputs
    public void printPuzzle(int[][] board, boolean color) {
        char[] rows = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        String hline = "  +-----------------------------+";
        String vline = "  |         |         |         |";

        // adds column labels
        System.out.println("    A  B  C   D  E  F   G  H  I");


        for (int i = 0; i < 9; i++) {
            // separates every third item with a horizontal line
            if (i % 3 == 0) {
                System.out.println(hline);
                System.out.println(vline);
            }

            // adds row labels
            System.out.print(rows[i]);
            for (int j = 0; j < 9; j++) {
                // separates every third column with a vertical line
                if (j % 3 == 0) {
                    System.out.print(" | ");
                } else {
                    System.out.print("  ");
                }
                if (board[i][j] == 0) {
                    System.out.print(" ");
                } else {
                    // if color print mode is true then user moves are highlighted
                    if (board[i][j] == original[i][j] || !color) {
                        System.out.print(board[i][j]);
                    } else {
                        System.out.print("\u001B[47m\u001B[30m" + board[i][j] + "\u001B[0m");
                    }
                }
            }
            System.out.println(" |");
            System.out.println(vline);
        }

        System.out.println(hline);
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
