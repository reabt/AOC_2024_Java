package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class DayOne {
    List<Integer> c1 = new ArrayList<>();
    List<Integer> c2 = new ArrayList<>();
    int solution;


    public DayOne() {
        setup();
    }

    private void setup() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input1.txt");
        readFile(inputStream);

//        calculate1();
        calculate2();
    }

    private void calculate2() {

        for (Integer i : c1) {
            solution = solution + (i * (Collections.frequency(c2, i)));
        }

        System.out.println(solution);
    }

    private void calculate1() {
        Collections.sort(c1);
        Collections.sort(c2);

        for (int i = 0; i < c1.size(); i++) {
            int min = Math.min(c1.get(i), c2.get(i));
            int max = Math.max(c1.get(i), c2.get(i));

            solution = solution + (max - min);
        }

        System.out.println(solution);
    }


    private void readFile(InputStream inputStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] pair = line.split("   ");
                int left = Integer.parseInt(pair[0]);
                c1.add(left);

                int right = Integer.parseInt(pair[1]);
                c2.add(right);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
