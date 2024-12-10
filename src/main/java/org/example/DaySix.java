package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class DaySix extends ProblemClass {
    private int x, y;
    private static final int DAY = 6;
    private final Set<DayFive.Pair<Integer>> path = new HashSet<>();
    private char direction = 'U';
    boolean moving = true;
    private int ANSWER;

    List<List<String>> puzzle = new ArrayList<>();


    public DaySix() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.clazz = this.getClass();

        setup(DAY);
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

    private void readFile(InputStream inputStream) { // needs some more logic extracting to remove duplicated code
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = br.readLine()) != null) { // extract to here

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
            ANSWER = path.size();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAns() {
        System.out.println(this.ANSWER);
    }

}
