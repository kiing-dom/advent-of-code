package aoc2024.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;


public class PageOrdering {
    

    public static void main(String[] args) {
        String filePath = "aoc2024/day5/page_numbers.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            List<String> rules = new ArrayList<>();
            List<String> updates = new ArrayList<>();

            String line;
            // read the rules section
            while((line = br.readLine()) != null && !line.isBlank()) {
                rules.add(line);
            }

            // read the updates section
            while((line = br.readLine()) != null) {
                updates.add(line);
            }

            br.close();
            // process them + print out the result
            int result = calculateMiddlePageSum(rules, updates);
            System.out.println("Sum of middle page numbers: " + result);

        } catch (IOException e) {
            System.err.println("Error reading file" + e.getMessage());
        }
    }

    // calculate sum of middle page numbers
    private static int calculateMiddlePageSum(List<String> rules, List<String> updates) {
        Map<Integer, Set<Integer>> precedence = new HashMap<>();
        for (String rule : rules) {
            String[] parts = rule.split("\\|");
            int before = Integer.parseInt(parts[0]);
            int after = Integer.parseInt(parts[1]);

            precedence.computeIfAbsent(before, k -> new HashSet<>()).add(after);
        }

        int sum = 0;

        for (String update : updates) {
            List<Integer> pages = new ArrayList<>();
            for (String page: update.split(",")) {
                pages.add(Integer.parseInt(page.trim()));
            }

            if (isCorrectlyOrdered(pages, precedence)) {
                int middle = pages.size() / 2;
                sum += pages.get(middle);
            }
        }

        return sum;
    }

    // check if correctly ordered
    private static boolean isCorrectlyOrdered(List<Integer> pages, Map<Integer, Set<Integer>> precedence) {
        Map<Integer, Integer> position = new HashMap<>();
        for(int i = 0; i < pages.size(); i++) {
            position.put(pages.get(i), i);
        }

        for(Map.Entry<Integer, Set<Integer>> entry : precedence.entrySet()) {
            int before = entry.getKey();
            for(int after: entry.getValue()) {
                if(position.containsKey(before) && position.containsKey(after) && position.get(before) > position.get(after)) {
                    return false;
                }
            }
        }

        return true;
    }
}
