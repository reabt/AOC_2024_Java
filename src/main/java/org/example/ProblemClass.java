package org.example;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProblemClass implements Problem {
    Class<? extends ProblemClass> clazz;

    @Override
    public void setup(int inDay) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ClassLoader classLoader = getClass().getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(String.format("input%s.txt", inDay));

        Method method = clazz.getDeclaredMethod("readFile", InputStream.class);
        method.setAccessible(true);
        method.invoke(this, inputStream);

    }

    @Override
    public void part1() {

    }

    @Override
    public void part2() {

    }
}
