package aoc2024.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CorruptedMemoryProcessor {
    
    public int calculateProduct(String line) {
        if(line == null || line.isEmpty()) {
            return 0;
        }

        String regex = "mul\\((\\d+),(\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        int sum = 0;

        while(matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));

            sum += x * y;
        }

        return sum;
    }

    public static void main(String[] args) {
        String filePath = "aoc2024/day3/corrupted_memory.txt";
        CorruptedMemoryProcessor cmp = new CorruptedMemoryProcessor();
        int totalSum = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                totalSum += cmp.calculateProduct(line);
            }

            System.out.println("Total Sum: " + totalSum);
        } catch (IOException e) {
            System.err.println("Couldn't read file" + e.getMessage());
        }
    } 
}
