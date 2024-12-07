package aoc2024.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuardPatrol {
    
    private static final char OBSTACLE = '#';
    private static final char UP = '^';
    private static final char RIGHT = '>';
    private static final char DOWN = 'v';
    private static final char LEFT = '<';

    private static final int[][] DIRECTIONS = {
        {-1, 0},
        {0, 1},
        {1, 0},
        {0, -1}
    };



    public static void main(String[] args) {
        String filePath = "aoc2024/day6/guard_map.txt";

        try {
            List<String> map = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line;
            while((line = br.readLine()) != null) {
                map.add(line);
            }
            br.close();

            int distinctPositions = calculateDistinctPositions(map);
            System.out.println("Distinct Positions: " + distinctPositions);
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static int calculateDistinctPositions(List<String> map) {
        int rows = map.size();
        int cols = map.get(0).length();

        int startX = 0, startY = 0, direction = 0;
        boolean found = false;

        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                char cell = map.get(i).charAt(j);
                if(cell == UP || cell == RIGHT || cell == DOWN || cell == LEFT) {
                    startX = i;
                    startY = j;
                    direction = switch (cell) {
                        case UP -> 0;
                        case RIGHT -> 1;
                        case DOWN -> 2;
                        case LEFT -> 3;
                        default -> 0;
                    };

                    found = true;
                    break;
                }
            }

            if (found) break;
        }

        Set<String> visited = new HashSet<>();
        visited.add(startX + "," + startY);

        int x = startX, y = startY;
        while (true) {
            int nextX = x + DIRECTIONS[direction][0];
            int nextY = y + DIRECTIONS[direction][1];

            if(nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols) {
                break;
            }

            char nextCell = map.get(nextX).charAt(nextY);
            if(nextCell == OBSTACLE) {
                direction = (direction + 1) % 4;
            } else {
                x = nextX;
                y = nextY;
                visited.add(x + "," + y);
            }
        }

        return visited.size();
    }
}
