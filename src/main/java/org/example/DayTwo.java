package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class DayTwo {
    int safe;

    List<List<Integer>> unsafeLevels = new ArrayList<>();

    public DayTwo() {
        setup();
    }

    private void setup() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input2.txt");
        readFile(inputStream);

    }

    private void readFile(InputStream inputStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] levels = line.split(" ");

                List<Integer> levelsList = new ArrayList<>();

                for (String level : levels) { // one line of input data
                    levelsList.add(Integer.parseInt(level));
                }

                if (testIsSortedAsc_A(levelsList) || testIsSortedDesc_A(levelsList)) {
                    safe = safe + 1;
                } else {
                    if (!unsafeLevels.contains(levelsList)) {
                        unsafeLevels.add(levelsList);
                    }
                }
            }


            for (List<Integer> unsafeLevel : unsafeLevels) {
                    // run a copy of unsafeLevel through both tests, with 1 element removed each time
                List<Integer> copy = new ArrayList<>(unsafeLevel); // make a fresh copy to remove elements from

                for (int i = 0; i < unsafeLevel.size(); i++) { // for each unsafe level

                    if (copy.size() < unsafeLevel.size()) { // if smth has been removed
                        if (i > 0) {
                            copy.add(i-1, unsafeLevel.get(i-1)); // add it back to recreate the original list
                        }
                    }

                    copy.remove(i);
                    testIsSortedAsc(copy);
                    testIsSortedDesc(copy);
                }

//                levelsList.clear();
            }
            System.out.println(safe);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private boolean testIsSortedAsc_A(List<Integer> levelsList) {

        Iterator<Integer> iter = levelsList.listIterator();
        Integer current, prev = iter.next();

        while (iter.hasNext()) {
            current = iter.next();

            if (prev.compareTo(current) > 0) {
                return false;
            }

            if (current - prev > 3 || current - prev < 1) {
                return false;
            }

            prev = current;
        }
        return true;
    }

    private boolean testIsSortedDesc_A(List<Integer> levelsList) {

        Iterator<Integer> iter = levelsList.listIterator();
        Integer current, prev = iter.next();

        while (iter.hasNext()) {
            current = iter.next();

            if (current.compareTo(prev) > 0) {
                return false;
            }

            if (prev - current > 3 || prev - current < 1) {
                return false;
            }

            prev = current;
        }
        return true;
    }


    private void testIsSortedAsc(List<Integer> levelsList) {

        Iterator<Integer> iter = levelsList.listIterator();
        Integer current, prev = iter.next();

        while (iter.hasNext()) {
            current = iter.next();

            if (prev.compareTo(current) > 0) {
                return;
            }

            if (current - prev > 3 || current - prev < 1) {
                return;
            }

            prev = current;
        }
        safe = safe + 1;
    }

    private void testIsSortedDesc(List<Integer> levelsList) {

        Iterator<Integer> iter = levelsList.listIterator();
        Integer current, prev = iter.next();

        while (iter.hasNext()) {
            current = iter.next();

            if (current.compareTo(prev) > 0) {
                return;
            }

            if (prev - current > 3 || prev - current < 1) {
                return;
            }

            prev = current;
        }
        safe = safe+1;
    }
}
