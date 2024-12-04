import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class DayTwo {
    private int safe = 0;

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
            List<List<Integer>> unsafeLevels = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] levels = line.split(" ");
                List<Integer> levelsList = new ArrayList<>();

                for (String level : levels) {
                    levelsList.add(Integer.parseInt(level));
                }

                if (isSortedAndValid(levelsList)) {
                    safe++;
                } else {
                    unsafeLevels.add(levelsList);
                }
            }

            for (List<Integer> unsafeLevel : unsafeLevels) {
                if (canBeSafeWithOneRemoval(unsafeLevel)) {
                    safe++;
                }
            }

            System.out.println("Safe reports: " + safe);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isSortedAndValid(List<Integer> levelsList) {
        return isSortedAsc(levelsList) || isSortedDesc(levelsList);
    }

    private boolean isSortedAsc(List<Integer> levelsList) {
        for (int i = 1; i < levelsList.size(); i++) {
            int diff = levelsList.get(i) - levelsList.get(i - 1);
            if (diff < 1 || diff > 3) return false; // Invalid difference
            if (levelsList.get(i) < levelsList.get(i - 1)) return false; // Not increasing
        }
        return true;
    }

    private boolean isSortedDesc(List<Integer> levelsList) {
        for (int i = 1; i < levelsList.size(); i++) {
            int diff = levelsList.get(i - 1) - levelsList.get(i);
            if (diff < 1 || diff > 3) return false; // Invalid difference
            if (levelsList.get(i) > levelsList.get(i - 1)) return false; // Not decreasing
        }
        return true;
    }

    private boolean canBeSafeWithOneRemoval(List<Integer> levelsList) {
        for (int i = 0; i < levelsList.size(); i++) {
            List<Integer> modifiedList = new ArrayList<>(levelsList);
            modifiedList.remove(i); // Remove one element
            if (isSortedAndValid(modifiedList)) {
                return true; // Found a valid list
            }
        }
        return false; // No valid list found
    }
}
