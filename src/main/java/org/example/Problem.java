package org.example;

import java.lang.reflect.InvocationTargetException;

public interface Problem {

    public void setup(int inDay) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    public void part1();

    public void part2();


}
