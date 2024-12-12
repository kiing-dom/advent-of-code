package aoc2024.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResonantCollinearity {
    
    public static void main(String[] args) {
        String filePath = "aoc2024/day8/antennas.txt";

        try {
            List<String> grid = readGrid(filePath);
            int rows = grid.size();
            int cols = grid.get(0).length();
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
        }

        return grid;
    }
}
