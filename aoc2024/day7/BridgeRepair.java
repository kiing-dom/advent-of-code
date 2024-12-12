package aoc2024.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class BridgeRepair {
    

    public static void main(String[] args) {
        String filePath = "aoc2024/day7/calibrations.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line;
            int totalCalibration = 0;

            while((line = br.readLine()) != null && !line.isEmpty()) {
                String[] parts = line.split(":");
                int targetValue = Integer.parseInt(parts[0].trim());
                String[] numStrings = parts[1].trim().split(" ");
                int[] nums = Arrays.stream(numStrings).mapToInt(Integer::parseInt).toArray();
            }
        } 
        catch (IOException e) {
            System.err.println("Could not read file: " + e.getMessage());
        }
    }

    
}
