package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DayFour extends ProblemClass {
    public List<List<Character>> letters = new ArrayList<>();

    int found = 0;

    public DayFour() {
        setup();
    }


    private void setup() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input4.txt");
        readFile(inputStream);

        solvep2(letters);
    }

    private void solve(List<List<Character>> inLetters) {
        LtR(inLetters);
        RtL(inLetters);
        DtU(inLetters);
        UtD(inLetters);
        diags(inLetters);

        System.out.println(found);
    }

    public void solvep2(List<List<Character>> inLetters) {
        p2_fwd_both(inLetters);
        p2_bwd_both(inLetters);
        p2_fwd_bwd(inLetters);
        p2_bwd_fwd(inLetters);
        System.out.println(found);
    }

    private void p2_fwd_both(List<List<Character>> inp) {
        int rows = inp.size();
        int cols = inp.get(0).size();

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (inp.get(row).get(col) == 'A') {
                    if (inp.get(row-1).get(col-1) == 'M' &&
                    inp.get(row+1).get(col+1) == 'S' &&
                    inp.get(row-1).get(col+1) == 'M' &&
                    inp.get(row+1).get(col-1) == 'S') {
                        found++;
                    }
                }
            }
        }
    }

    private void p2_bwd_both(List<List<Character>> inp) {

        int rows = inp.size();
        int cols = inp.get(0).size();

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (inp.get(row).get(col) == 'A') {
                    if (inp.get(row-1).get(col-1) == 'S' &&
                            inp.get(row+1).get(col+1) == 'M' &&
                            inp.get(row-1).get(col+1) == 'S' &&
                            inp.get(row+1).get(col-1) == 'M') {
                        found++;
                    }
                }
            }
        }
    }

    private void p2_fwd_bwd(List<List<Character>> inp) {

        int rows = inp.size();
        int cols = inp.get(0).size();

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (inp.get(row).get(col) == 'A') {
                    if (inp.get(row-1).get(col-1) == 'M' &&
                            inp.get(row+1).get(col+1) == 'S' &&
                            inp.get(row-1).get(col+1) == 'S' &&
                            inp.get(row+1).get(col-1) == 'M') {
                        found++;
                    }
                }
            }
        }
    }

    private void p2_bwd_fwd(List<List<Character>> inp) {

        int rows = inp.size();
        int cols = inp.get(0).size();

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (inp.get(row).get(col) == 'A') {
                    if (inp.get(row-1).get(col-1) == 'S' &&
                            inp.get(row+1).get(col+1) == 'M' &&
                            inp.get(row-1).get(col+1) == 'M' &&
                            inp.get(row+1).get(col-1) == 'S') {
                        found++;
                    }
                }
            }
        }
    }



    public void diags(List<List<Character>> inp) {
        int rows = inp.size();
        int cols = rows > 0 ? inp.get(0).size() : 0;

        // TL-BR
        for (int row = 0; row <= rows - 4; row++) {
            for (int col = 0; col <= cols - 4; col++) {
                if (inp.get(row).get(col) == 'X' &&
                        inp.get(row + 1).get(col + 1) == 'M' &&
                        inp.get(row + 2).get(col + 2) == 'A' &&
                        inp.get(row + 3).get(col + 3) == 'S') {
                    found++;
                }
            }
        }

        // BR-TL
        for (int row = rows - 1; row >= 3; row--) {
            for (int col = cols - 1; col >= 3; col--) {
                if (inp.get(row).get(col) == 'X' &&
                        inp.get(row - 1).get(col - 1) == 'M' &&
                        inp.get(row - 2).get(col - 2) == 'A' &&
                        inp.get(row - 3).get(col - 3) == 'S') {
                    found++;
                }
            }
        }

        // TR-BL
        for (int row = 0; row <= rows - 4; row++) {
            for (int col = cols - 1; col >= 3; col--) {
                if (inp.get(row).get(col) == 'X' &&
                        inp.get(row + 1).get(col - 1) == 'M' &&
                        inp.get(row + 2).get(col - 2) == 'A' &&
                        inp.get(row + 3).get(col - 3) == 'S') {
                    found++;
                }
            }
        }

        // BL-TR
        for (int row = rows - 1; row >= 3; row--) {
            for (int col = 0; col <= cols - 4; col++) {
                if (inp.get(row).get(col) == 'X' &&
                        inp.get(row - 1).get(col + 1) == 'M' &&
                        inp.get(row - 2).get(col + 2) == 'A' &&
                        inp.get(row - 3).get(col + 3) == 'S') {
                    found++;
                }
            }
        }
    }

    public void LtR(List<List<Character>> inp) {
        for (List<Character> line : inp) {

            for (int i = 0; i <= line.size() - 4; i++) {
                if (line.get(i) == 'X' &&
                        line.get(i + 1) == 'M' &&
                        line.get(i + 2) == 'A' &&
                        line.get(i + 3) == 'S') {
                    found++;
                }
            }
        }
    }

    public void RtL(List<List<Character>> inp) {
        for (List<Character> line : inp) {

            for (int i = 0; i <= line.size() - 4; i++) {
                if (line.get(i) == 'S' &&
                        line.get(i + 1) == 'A' &&
                        line.get(i + 2) == 'M' &&
                        line.get(i + 3) == 'X') {
                    found++;
                }
            }
        }
    }

    public void UtD(List<List<Character>> inp) {
        for (int row = 0; row <= inp.size() - 4; row++) {
            List<Character> currentRow = inp.get(row);

            for (int col = 0; col < currentRow.size(); col ++) {
                if (currentRow.get(col) == 'X' &&
                        inp.get(row+1).get(col) == 'M' &&
                        inp.get(row+2).get(col) == 'A' &&
                        inp.get(row+3).get(col) == 'S')
                    found++;
            }
        }
    }


    public void DtU(List<List<Character>> inp) {
        for (int row = 0; row <= inp.size() - 4; row++) {
            List<Character> currentRow = inp.get(row);

            for (int col = 0; col < currentRow.size(); col ++) {
                if (currentRow.get(col) == 'S' &&
                        inp.get(row+1).get(col) == 'A' &&
                        inp.get(row+2).get(col) == 'M' &&
                        inp.get(row+3).get(col) == 'X')
                    found++;
            }
        }
    }

    private void readFile(InputStream inputStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = br.readLine()) != null) {
                List<Character> currentLine = new ArrayList<>();

                for (int i = 0; i < line.length(); i++) {
                    currentLine.add(line.charAt(i));
                }
                letters.add(currentLine);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

