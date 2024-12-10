package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DaySix {

    private int day = 6;
    private int x, y;
    private final List<DayFive.Pair<Integer>> path = new ArrayList<>();
    private char direction = 'U';
    boolean moving = true;

    List<List<String>> puzzle = new ArrayList<>();


    public DaySix() {
        setup();
    }

    public void move() {
        while (moving) {

            if (direction == 'U') {
                if (Objects.equals(puzzle.get(y - 1).get(x), "#")) {
                    direction = 'R';
                } else {
                    y--;
                    path.add(new DayFive.Pair<>(x, y));
                }
            }

            if (direction == 'R') {
                if (Objects.equals(puzzle.get(y).get(x + 1), "#")) {
                    direction = 'D';
                } else {
                    x++;
                    path.add(new DayFive.Pair<>(x, y));
                }
            }

            if (direction == 'D') {
                if (Objects.equals(puzzle.get(y + 1).get(x), "#")) {
                    direction = 'L';
                } else {
                    y++;
                    path.add(new DayFive.Pair<>(x, y));
                }
            }

            if (direction == 'L') {
                if (Objects.equals(puzzle.get(y).get(x - 1), "#")) {
                    direction = 'U';
                } else {
                    x--;
                    path.add(new DayFive.Pair<>(x, y));
                }
            }

            checkIfAtExit();
            move();
        }
    }


    private void checkIfAtExit() {
        if  (y < 0 || y > puzzle.size() || x < 0 || x > puzzle.getLast().size()) {
            moving = false;
        }
    }

    private void calculateTotal(List<DayFive.Pair<Integer>> path) { // calculate how many unique pairs of ints there are in path
        List<DayFive.Pair<Integer>> pathCopy = new ArrayList<>(path);

        int startIndex = 1;

        for (DayFive.Pair<Integer> pair : path) {

            int first = pair.getp1();
            int second = pair.getp2();

            for (int i = startIndex; i < path.size() -1; i++) {

                startIndex = path.indexOf(pair) + 1;

                int path1 = path.get(startIndex).getp1();
                int path2 = path.get(startIndex).getp2();

                if ((first == path1) & (second == path2)) {
                    pathCopy.remove(path.get(i));
                }
            }
        }
        System.out.println(pathCopy.size() + 2);
    }


    private void setup() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input6.txt");
        readFile(inputStream);
    }

    private void readFile(InputStream inputStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] currentRowArr = line.split("");
                List<String> currentRow = new ArrayList<>();

                for (int i = 0; i < currentRowArr.length; i++) {

                    if (Objects.equals(currentRowArr[i], "^")) {
                        x = i;
                        y = puzzle.size();
                    }

                    currentRow.add(currentRowArr[i]);
                }

                puzzle.add(currentRow);

            }

            // solve
            move();
            calculateTotal(path);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
