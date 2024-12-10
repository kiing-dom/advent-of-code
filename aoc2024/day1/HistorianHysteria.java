package aoc2024.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HistorianHysteria {

    public int findTotalDistance(List<Integer> list1, List<Integer> list2) {
        Collections.sort(list1);
        Collections.sort(list2);

        int totalDistance = 0;
        for (int i = 0; i < list1.size(); i++) {
            totalDistance += Math.abs(list1.get(i) - list2.get(i));
        }

        return totalDistance;
    }


    public static void main(String[] args) {

        HistorianHysteria hh = new HistorianHysteria();
        String output = "Output: ";

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        String filePath = "aoc2024/day1/lists.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if(parts.length == 2) {
                    list1.add(Integer.parseInt(parts[0]));
                    list2.add(Integer.parseInt(parts[1]));
                }
            }

            System.out.println(output + hh.findTotalDistance(list1, list2));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}