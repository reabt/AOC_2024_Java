package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class DayFive {

    public DayFive() {
        setup();
    }
    int total = 0;

    private final List<Pair<Integer>> rules = new ArrayList<>();
    private final List<RowToPrint> toPrint = new ArrayList<>();

    private void setup() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input5.txt");
        readFile(inputStream);

        solvep1(rules, toPrint);

        addMiddles(toPrint);
        System.out.println(total);

    }

    private void solvep1(List<Pair<Integer>> inRules, List<RowToPrint> inToPrint) {

        for (RowToPrint line : inToPrint) {
            for (Pair<Integer> rule : inRules) { // for each rule
             // for each line of pages to print

                if (line.getNums().contains(rule.getp1()) && line.getNums().contains(rule.getp2())) { // find all toPrint sections that contain BOTH parts of rule

                    int i1 = line.getNums().indexOf(rule.getp1()); // check order is correct: find index of r1 and r2, if r1<r2, add to safe list
                    int i2 = line.getNums().indexOf(rule.getp2());

                    if (i1 > i2) {
                        line.setIsSafe(false);
                        break;
                    }
                }
            }
        }
    }

    private void addMiddles(List<RowToPrint> inSafeRows) {

        for (RowToPrint row : inSafeRows) {
            if (row.getIsSafe()) {
                int middleIndex = ((row.getNums().size()) /2);
                int val = row.getNums().get(middleIndex);

                total += val;
            }
        }
    }

    private void readFile(InputStream inputStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = br.readLine()) != null) {

                if (line.contains("|")) { // parse printing rules

                    String[] ruleNums = line.split("\\|");
                    int r1 = Integer.parseInt(ruleNums[0].strip());
                    int r2 = Integer.parseInt(ruleNums[1].strip());
                    Pair<Integer> pair = new Pair<>(r1, r2);

                    rules.add(pair);

                } else if (!line.isBlank()) { // parse lines of page numbers

                    String[] nums = line.split(",");
                    List<Integer> inPages = new ArrayList<>();

                    for (String num : nums) {
                        inPages.add(Integer.parseInt(num));
                    }
                    toPrint.add(new RowToPrint(inPages, true));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Pair<Integer> {

        private final Integer p1;
        private final Integer p2;

        public Pair(Integer inP1, Integer inP2) {
            this.p1 = inP1;
            this.p2 = inP2;
        }

        public Integer getp1() {
            return this.p1;
        }

        public Integer getp2() {
            return this.p2;
        }

    }

    public class RowToPrint { // todo
        private final List<Integer> nums;
        Boolean isSafe;

        public RowToPrint(List<Integer> inNums, Boolean inIsSafe) {
            this.nums = inNums;
            this.isSafe = inIsSafe;
        }
        public Boolean getIsSafe() {
            return this.isSafe;
        }
        public void setIsSafe(Boolean val) {
            this.isSafe = val;
        }

        public List<Integer> getNums() {
            return this.nums;
        }
    }
}
