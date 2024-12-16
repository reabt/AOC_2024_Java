package org.example;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        DayOne dayOne = new DayOne();
//        DayTwo dayTwo = new DayTwo();
//        DayThree dayThree = new DayThree();
//        DayFour dayFour = new DayFour();
        DayFive dayFive = new DayFive();
//        DaySix daySix = new DaySix();


        dayFive.getAns(); // way to instantiate this as an implementation of the interface to avoid having to call
        // getAns() on a different class type each day??

    }

//    public static void readFile(InputStream inputStream) {
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//
//                String[] levels = line.split(" ");
//
//                List<Integer> levelsList = new ArrayList<>();
//
//                for (String level : levels) {
//                    levelsList.add(Integer.parseInt(level));
//                }
//
//                testIsSortedAsc(levelsList);
//                testIsSortedDesc(levelsList);
//
//                levelsList.clear();
//            }
//            System.out.println(safe);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


}