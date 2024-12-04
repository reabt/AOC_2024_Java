package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree {
    List<Map<Integer, Integer>> pairList = new ArrayList<>();
    int total = 0;

    Pattern muls = Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))");
    Pattern dontDo = Pattern.compile("(don't\\(\\)(.|\\n)*?do\\(\\)*)");
    Pattern doSection = Pattern.compile("(do\\(\\).*?don't\\(\\)*)");
    Pattern doSectionInclLineBr = Pattern.compile("(do\\(\\)(.|\\n)*?don't\\(\\)*)");
    public DayThree() {
        setup();
    }

    private void setup() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input3.txt");
//        readFile(inputStream);
        readAdvanced(inputStream);

    }

    public void readAdvanced(InputStream inputStream) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] pairArray = doSectionInclLineBr
                        .matcher(line)
                        .results()
                        .map(MatchResult::group)
                        .toArray(String[]::new);

//                System.out.println(Arrays.toString(pairArray));

                for (String bigPart : pairArray) {
//                    System.out.println(bigPart);

                    String[] smallPart = muls
                            .matcher(bigPart)
                            .results()
                            .map(MatchResult::group)
                            .toArray(String[]::new);

//                    System.out.println(Arrays.toString(smallPart));

                    for (String part : smallPart) { // parse into numbers:

                        Matcher m1 = Pattern.compile("\\d{1,3},\\d{1,3}").matcher(part); // pick out NUM,NUM

                        while (m1.find()) {

                            String[] res = m1.group().split(","); // split at ,
                            int i1 = Integer.parseInt(res[0]);
                            int i2 = Integer.parseInt(res[1]);

                            total = total + (i1 * i2);
                        }
                    }
                }
            }

            System.out.println(total);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void matchAndAddToList(String s) {
        String[] pairs = muls.matcher(s)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new);

        for (String part : pairs) { // parse into numbers:

            Matcher m1 = Pattern.compile("\\d{1,3},\\d{1,3}").matcher(part); // pick out NUM,NUM

            while (m1.find()) {

                String[] res = m1.group().split(","); // split at ,
                int i1 = Integer.parseInt(res[0]);
                int i2 = Integer.parseInt(res[1]);
                Map<Integer, Integer> temp = new HashMap<>();
                temp.put(i1, i2);

                pairList.add(temp);
            }
        }
    }


        private void readFile(InputStream inputStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] pairArray = Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))") // find relevant sections
                        .matcher(line)
                        .results()
                        .map(MatchResult::group)
                        .toArray(String[]::new);

                for (String part : pairArray) { // parse into numbers:

                    Matcher m1 = Pattern.compile("\\d{1,3},\\d{1,3}").matcher(part); // pick out NUM,NUM

                    while (m1.find()) {

                        String[] res = m1.group().split(","); // split at ,
                        int i1 = Integer.parseInt(res[0]);
                        int i2 = Integer.parseInt(res[1]);

                        total = total + (i1 * i2);
                    }
                }
            }

            System.out.println(total);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
