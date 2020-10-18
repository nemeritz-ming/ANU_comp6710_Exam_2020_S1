package comp1110.exam;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q1SudokuTest {

    @Test
    public void testOne() {
        Random r = new Random();
        int correct[] = {3, 1, 6, 5, 7, 8, 4, 9, 2, 5, 2, 9, 1, 3, 4, 7, 6, 8, 4, 8, 7, 6, 2, 9, 5, 3, 1, 2, 6, 3, 4, 1, 5, 9, 8, 7, 9, 7, 4, 8, 6, 3, 1, 2, 5, 8, 5, 1, 7, 9, 2, 6, 4, 3, 1, 3, 8, 9, 4, 7, 2, 5, 6, 6, 9, 2, 3, 5, 1, 8, 7, 4, 7, 4, 5, 2, 8, 6, 3, 1, 9};
        int setup[] = correct.clone();
        setup[r.nextInt(setup.length)] = 0;
        checkSolution(setup, correct);
    }

    @Test
    public void testTwo() {
        Random r = new Random();
        int correct[] = {4, 3, 5, 2, 6, 9, 7, 8, 1, 6, 8, 2, 5, 7, 1, 4, 9, 3, 1, 9, 7, 8, 3, 4, 5, 6, 2, 8, 2, 6, 1, 9, 5, 3, 4, 7, 3, 7, 4, 6, 8, 2, 9, 1, 5, 9, 5, 1, 7, 4, 3, 6, 2, 8, 5, 1, 9, 3, 2, 6, 8, 7, 4, 2, 4, 8, 9, 5, 7, 1, 3, 6, 7, 6, 3, 4, 1, 8, 2, 5, 9};
        int setup[] = correct.clone();
        setup[r.nextInt(setup.length)] = 0;
        setup[r.nextInt(setup.length)] = 0;
        checkSolution(setup, correct);
    }

    @Rule
    public Timeout globalTimeout = Timeout.millis(500);

    @Test
    public void testEasy() {
        int setup[] = {0, 0, 0, 2, 6, 0, 7, 0, 1, 6, 8, 0, 0, 7, 0, 0, 9, 0, 1, 9, 0, 0, 0, 4, 5, 0, 0, 8, 2, 0, 1, 0, 0, 0, 4, 0, 0, 0, 4, 6, 0, 2, 9, 0, 0, 0, 5, 0, 0, 0, 3, 0, 2, 8, 0, 0, 9, 3, 0, 0, 0, 7, 4, 0, 4, 0, 0, 5, 0, 0, 3, 6, 7, 0, 3, 0, 1, 8, 0, 0, 0};
        int correct[] = {4, 3, 5, 2, 6, 9, 7, 8, 1, 6, 8, 2, 5, 7, 1, 4, 9, 3, 1, 9, 7, 8, 3, 4, 5, 6, 2, 8, 2, 6, 1, 9, 5, 3, 4, 7, 3, 7, 4, 6, 8, 2, 9, 1, 5, 9, 5, 1, 7, 4, 3, 6, 2, 8, 5, 1, 9, 3, 2, 6, 8, 7, 4, 2, 4, 8, 9, 5, 7, 1, 3, 6, 7, 6, 3, 4, 1, 8, 2, 5, 9};
        checkSolution(setup, correct);
    }

    @Test
    public void testMedium() {
        int setup[] = {0, 2, 0, 6, 0, 8, 0, 0, 0, 5, 8, 0, 0, 0, 9, 7, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 3, 7, 0, 0, 0, 0, 5, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 8, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 9, 8, 0, 0, 0, 3, 6, 0, 0, 0, 3, 0, 6, 0, 9, 0};
        int correct[] = {1, 2, 3, 6, 7, 8, 9, 4, 5, 5, 8, 4, 2, 3, 9, 7, 6, 1, 9, 6, 7, 1, 4, 5, 3, 2, 8, 3, 7, 2, 4, 6, 1, 5, 8, 9, 6, 9, 1, 5, 8, 3, 2, 7, 4, 4, 5, 8, 7, 9, 2, 6, 1, 3, 8, 3, 6, 9, 2, 4, 1, 5, 7, 2, 1, 9, 8, 5, 7, 4, 3, 6, 7, 4, 5, 3, 1, 6, 8, 9, 2};
        checkSolution(setup, correct);
    }

    @Test
    public void testDifficult() {
        int setup[] = {0, 0, 0, 6, 0, 0, 4, 0, 0, 7, 0, 0, 0, 0, 3, 6, 0, 0, 0, 0, 0, 0, 9, 1, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 1, 8, 0, 0, 0, 3, 0, 0, 0, 3, 0, 6, 0, 4, 5, 0, 4, 0, 2, 0, 0, 0, 6, 0, 9, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0};
        int correct[] = {5, 8, 1, 6, 7, 2, 4, 3, 9, 7, 9, 2, 8, 4, 3, 6, 5, 1, 3, 6, 4, 5, 9, 1, 7, 8, 2, 4, 3, 8, 9, 5, 7, 2, 1, 6, 2, 5, 6, 1, 8, 4, 9, 7, 3, 1, 7, 9, 3, 2, 6, 8, 4, 5, 8, 4, 5, 2, 1, 9, 3, 6, 7, 9, 1, 3, 7, 6, 8, 5, 2, 4, 6, 2, 7, 4, 3, 5, 1, 9, 8};
        checkSolution(setup, correct);
    }

    private void checkSolution(int[] setup, int[] correct) {
        assertArrayEquals("Q1Sudoku.solve returned incorrect solution for setup:\n" + setupString(setup), split(correct), Q1Sudoku.solve(split(setup)));
    }

    private int[][] split(int[] s) {
        int x[][] = new int[9][];
        for (int i = 0; i < 9; i++) {
            x[i] = new int[9];
            for (int j = 0; j < 9; j++) {
                x[i][j] = s[i * 9 + j];
            }
        }
        return x;
    }

    private String setupString(int[] s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(s[i * 9 + j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
