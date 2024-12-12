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
            long totalCalibration = 0;

            while((line = br.readLine()) != null && !line.isEmpty()) {
                String[] parts = line.split(":");
                long targetValue = Long.parseLong(parts[0].trim());
                String[] numStrings = parts[1].trim().split(" ");
                int[] nums = Arrays.stream(numStrings).mapToInt(Integer::parseInt).toArray();

                if(canAchieveTarget(nums, targetValue)) {
                    totalCalibration += targetValue;
                }
            }
            br.close();
            System.out.println("total calibration value: " + totalCalibration);
        } 
        catch (IOException e) {
            System.err.println("Could not read file: " + e.getMessage());
        }
    }

    private static boolean canAchieveTarget(int[] numbers, long targetValue) {
        if(numbers.length == 1) {
            return numbers[0] == targetValue;
        }
        return evaluate(numbers, 0, numbers[0], targetValue);
    }

    private static boolean evaluate(int[] numbers, int index, long currentValue, long targetValue) {
        if(index == numbers.length - 1) {
            return currentValue == targetValue;
        }

        int nextNumber = numbers[index + 1];
        return evaluate(numbers, index + 1, currentValue + nextNumber, targetValue) ||
            evaluate(numbers, index + 1, currentValue * nextNumber, targetValue);
    }
}
