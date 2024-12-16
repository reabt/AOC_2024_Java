package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProblemClass implements Problem {
    Class<? extends ProblemClass> clazz;

    @Override
    public void setup(int inDay) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ClassLoader classLoader = getClass().getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(String.format("input%s.txt", inDay));


        try {
            assert inputStream != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;

                while ((line = br.readLine()) != null) {

                    Method method = clazz.getDeclaredMethod("parse", String.class);
                    method.setAccessible(true);
                    method.invoke(this, line);

                }

                Method method = clazz.getDeclaredMethod("solve");
                method.setAccessible(true);
                method.invoke(this);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void solve() {

    }
}

