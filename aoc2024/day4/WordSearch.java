package aoc2024.day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordSearch {

    private static final int[][] DIRECTIONS = {
            { 0, 1 },
            { 0, -1 },
            { 1, 0 },
            { -1, 0 },
            { 1, 1 },
            { -1, -1 },
            { 1, -1 },
            { -1, 1 }
    };

    public static void main(String[] args) {
        String filePath = "aoc2024/day4/word_search.txt";
        String word = "XMAS";

        try {
            char[][] grid = readGridFromFile(filePath);

            // count occurrences
            int count = countWordOccurrences(grid, word);
            System.out.println("Total count of " + word + ": " + count);
            //print out occurrences
        } catch (IOException e){
            System.err.println("Error reading file" + e.getMessage());
        }
    }

    // read characters from file and create grid
    private static char[][] readGridFromFile(String filePath) throws IOException {
        List<char[]> gridList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                gridList.add(line.toCharArray());
            }

        }
        return gridList.toArray(new char[0][]);
    }
    // count the occurrences of the word (XMAS)
    private static int countWordOccurrences(char[][] grid, String word) {
        int rows = grid.length;
        int cols = grid[0].length;

        int count = 0;

        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                for(int[] direction : DIRECTIONS) {
                    if(matchesWord(grid, r, c, direction, word)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    // check if matches in all directions (also must stay in bounds)
    private static boolean matchesWord(char[][] grid, int startX, int startY, int[] direction, String word) {
        int x = startX, y = startY;

        for(char c: word.toCharArray()) {
            if(!isInBounds(grid, x, y) || grid[x][y] != c) {
                return false;
            }

            x += direction[0];
            y += direction[1];
        }

        return true;
    }

    private static boolean isInBounds(char[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}
