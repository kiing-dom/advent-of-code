package aoc2024.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResonantCollinearity {
    
    public static void main(String[] args) {
        String filePath = "aoc2024/day8/antennas.txt";

        try {
            List<String> grid = readGrid(filePath);
            int rows = grid.size();
            int cols = grid.get(0).length();

            Map<Character, List<int[]>> antennas = parseGrid(grid);

            Set<String> uniqueAntinodes = findAntinodes(antennas, rows, cols);

            System.out.println("Number of unique antinode locations: " + uniqueAntinodes.size());
        } 
        
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    private static List<String> readGrid(String filePath) throws IOException {
        List<String> grid = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;

            while((line = br.readLine()) != null) {
                if(!line.isEmpty()) {
                    grid.add(line);

                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return grid;
    }

    private static Map<Character, List<int[]>> parseGrid(List<String> grid) {
        Map<Character, List<int[]>> antennas = new HashMap<>();
        for(int j = 0; j < grid.size(); j++) {
            String row = grid.get(j);
            for(int i = 0; i < row.length(); i++) {
                char ch = row.charAt(i);
                if(ch != '.') {
                    antennas.computeIfAbsent(ch, k -> new ArrayList<>()).add(new int[]{i, j});
                }
            }
        }

        return antennas;
    }

    private static Set<String> findAntinodes(Map<Character, List<int[]>> antennas, int rows, int cols) {
        Set<String> antinodes = new HashSet<>();

        for(Map.Entry<Character, List<int[]>> entry : antennas.entrySet()) {
            List<int[]> positions = entry.getValue();

            for(int i = 0; i < positions.size(); i++) {
                for(int j = i + 1; j < positions.size(); j++) {
                    int[] p1 = positions.get(i);
                    int[] p2 = positions.get(j);

                    int dx = p2[0] - p1[0];
                    int dy = p2[1] - p1[1];

                    int[] antinode1 = {p1[0] - dx, p1[1] - dy};
                    int[] antinode2 = {p2[0] + dx, p2[1] + dy};

                    if(isInBounds(antinode1, rows, cols)) {
                        antinodes.add(antinode1[0] + "," + antinode1[1]);
                    }

                    if(isInBounds(antinode2, rows, cols)) {
                        antinodes.add(antinode2[0] + "," + antinode2[1]);
                    }
                }
            }
        }

        return antinodes;
    }

    private static boolean isInBounds(int[] position, int rows, int cols) {
        return (position[0] >= 0 && position[0] < rows && position[1] >= 0 && position[1] < cols);
    }
}
