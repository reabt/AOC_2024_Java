package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class DaySix {
    private int x, y;
    private final Set<DayFive.Pair<Integer>> path = new HashSet<>();
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
        if  (y < 1 || y > puzzle.size() || x < 1 || x > puzzle.getLast().size()) {
            moving = false;
        }
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

            path.add(new DayFive.Pair<>(x, y));
            move();
            System.out.println(path.size());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
