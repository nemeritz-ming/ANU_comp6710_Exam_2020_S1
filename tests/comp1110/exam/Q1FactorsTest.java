package comp1110.exam;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q1FactorsTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(500);

    @Test
    public void testNoFactors() {
        test(0, new int[0]);
        test(1, new int[0]);
        test(-4, new int[0]);
    }

    @Test
    public void testPrimes() {
        test(2, new int[]{2});
        test(3, new int[]{3});
        test(5, new int[]{5});
        test(7, new int[]{7});
        test(11, new int[]{11});
        test(1299709, new int[]{1299709});
    }

    @Test
    public void testUniqueFactors() {
        test(6, new int[]{2, 3});
        test(10, new int[]{2, 5});
        test(14, new int[]{2, 7});
        test(2759, new int[]{31, 89});
    }

    @Test
    public void testDuplicateFactors() {
        test(4, new int[]{2, 2});
        test(8, new int[]{2, 2, 2});
        test(9, new int[]{3, 3});
        test(12, new int[]{2, 2, 3});
        test(18, new int[]{2, 3, 3});
        test(20, new int[]{2, 2, 5});
        test(49, new int[]{7, 7});
    }

    @Test
    public void testManyFactors() {
        test(24679, new int[]{23, 29, 37});
        test(670096, new int[]{2, 2, 2, 2, 7, 31, 193});
        test(10236985, new int[]{5, 11, 373, 499});
        test(223092870, new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23});
        test(797743310, new int[]{2, 5, 7, 13, 19, 29, 37, 43});
        test(1127650597, new int[]{1021, 1039, 1063});
    }

    private void test(int n, int[] expected) {
        int[] result = Q1Factors.factors(n);
        assertTrue("Q1Factors.factors returned incorrect result, expected: " + Arrays.toString(expected) + " but got " + Arrays.toString(result), Arrays.equals(expected, result));
    }

}
