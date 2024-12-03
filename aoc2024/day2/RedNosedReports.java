package aoc2024.day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RedNosedReports {

    public String isSafe(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return "Safe";
        }

        boolean increasing = numbers.get(1) > numbers.get(0);
        boolean decreasing = numbers.get(1) < numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            int diff = numbers.get(i) - numbers.get(i - 1);
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return "Unsafe";
            }

            if((increasing && diff < 0) || (decreasing && diff > 0)) {
                return "Unsafe";
            }
        }

        return "Safe";
    }

    public static void main(String[] args) {
        String filePath = "aoc2024/day2/levels.txt";
        RedNosedReports rnr = new RedNosedReports();
        String output = "Output: ";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            int safeCount = 0;
            while ((line = br.readLine()) != null) {
                String[] numbersToString = line.trim().split("\\s+");
                List<Integer> numbers = new ArrayList<>();

                for (String numsStr : numbersToString) {
                    try {
                        numbers.add(Integer.parseInt(numsStr));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number: " + e.getMessage());
                    }
                }

                if (rnr.isSafe(numbers).equals("Safe")) {
                    safeCount++;
                }
            }
            System.out.println(safeCount);

        } catch (FileNotFoundException e) {
            System.err.println("File not found" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file" + e.getMessage());
        }
    }
}
