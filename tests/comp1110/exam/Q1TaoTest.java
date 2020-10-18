package comp1110.exam;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Q1TaoTest extends StdIOTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    @Test
    public void testTrivial() {
        checkTao(false, 0);
        outContent.reset();
        checkTao(false, -1);
        outContent.reset();
        checkTao(false, 1, 1);
        outContent.reset();
        checkTao(true, 13120);
    }

    @Test
    public void testPowerOfTwo() {
        outContent.reset();
        checkTao(false, 2, 2, 1);
        outContent.reset();
        checkTao(false, 4, 4, 2, 1);
        outContent.reset();
        checkTao(false, 8, 8, 4, 2, 1);
        outContent.reset();
        checkTao(false, 1024, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1);
    }

    @Test
    public void testOdd() {
        outContent.reset();
        checkTao(false, 3, 3, 10, 5, 16, 8, 4, 2, 1);
        outContent.reset();
        checkTao(false, 19, 19, 58, 29, 88, 44, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1);
    }

    @Test
    public void testStateless() {
        checkTao(false, 3, 3, 10, 5, 16, 8, 4, 2, 1);
        outContent.reset();
        checkTao(false, 3, 3, 10, 5, 16, 8, 4, 2, 1);
    }

    @Test
    public void testTao() {
        checkTao(true, 17492, 17492, 8746, 4373);
        outContent.reset();
        checkTao(true, 52480, 52480, 26240);
        outContent.reset();
        checkTao(true, 255, 255, 766, 383, 1150, 575, 1726, 863, 2590, 1295, 3886, 1943, 5830, 2915, 8746, 4373);
    }

    private void checkTao(boolean tao, int start, int... sequence) {
        String expected = getExpected(tao, sequence);
        Q1Tao.tao(start);
        assertEquals("incorrect output printed from call to tao(" + start + ")", expected, outContent.toString());
    }

    private String getExpected(boolean tao, int... sequence) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(sequence).forEach(s -> sb.append(s).append(System.lineSeparator()));
        if (tao) sb.append("Tao!").append(System.lineSeparator());
        return sb.toString();
    }

}
