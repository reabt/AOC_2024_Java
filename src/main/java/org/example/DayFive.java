package org.example;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class DayFive extends ProblemClass implements Problem {

    public DayFive() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        this.clazz = this.getClass();
        setup(DAY);
    }
    int total = 0;
    private static final int DAY = 5;

    private final List<Pair<Integer>> rules = new ArrayList<>();
    private final List<RowToPrint> toPrint = new ArrayList<>();


    public void solve() {
        for (RowToPrint line : toPrint) {
            for (Pair<Integer> rule : rules) { // for each rule
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

        addMiddles(toPrint);
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

    public void parse(String line) {
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

    public void getAns() {
        System.out.println(total);
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


        @Override
        public boolean equals (Object obj) {
            if (this == obj) return true;

            if (!(obj instanceof Pair)) return false;Pair<?> other = (Pair<?>) obj;
            return Objects.equals(p1, other.p1) && Objects.equals(p2, other.p2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(p1, p2);
        }
    }

    public static class RowToPrint { // todo
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
