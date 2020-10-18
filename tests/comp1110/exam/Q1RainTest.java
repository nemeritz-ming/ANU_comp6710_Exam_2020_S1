package comp1110.exam;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertTrue;

public class Q1RainTest {
  @Rule
  public Timeout globalTimeout = Timeout.millis(500);

  public static int ITERATIONS = 10;
  Random r = new Random();

  @Test
  public void testEmpty() {
    test(0, false, false, false);
  }

  @Test
  public void testOne() {
    test(1, false, false, false);
  }

  @Test
  public void testTwo() {
    for (int i = 0; i < ITERATIONS; i++)
      test(2, r.nextBoolean(), r.nextBoolean(), r.nextBoolean());
  }

  @Test
  public void testFour() {
    for (int i = 0; i < ITERATIONS; i++)
    test(4, r.nextBoolean(), r.nextBoolean(), r.nextBoolean());
  }

  @Test
  public void testManyNegativesLarge() {
    test(20, r.nextBoolean(), r.nextBoolean(), r.nextBoolean());
  }

  void test(int values, boolean negatives, boolean extra, boolean large) {
    List<Integer> vals = new ArrayList<>();
    int sum = 0;
    for (int i = 0; i < values; i++) {
      int range = Q1Rain.MAGIC;
      if (large) range *= 2;
      int v = r.nextInt(range);
      if (v == Q1Rain.MAGIC) v -= 1;
      vals.add(v);
      sum += v;
    }
    int avg = (values > 0) ? sum/values : -1;

    List<Integer> in = new ArrayList<>();

    for (int v : vals) {
      if (negatives && r.nextBoolean())
        in.add(-r.nextInt(Q1Rain.MAGIC));
      in.add(v);
    }

    in.add(Q1Rain.MAGIC);

    if (extra) {
      for (int i = 0; i < values; i++) {
        in.add(r.nextInt(Q1Rain.MAGIC));
      }
    }

    int[] input = new int[in.size()];
    String str = "";
    for (int i = 0; i < in.size(); i++) {
      input[i] = in.get(i);
      if (i != 0)
        str += ", ";
      str += input[i];
    }

    int result = Q1Rain.avg(input);
    assertTrue("Incorrect result.  Got '"+result+"' when expecting '"+avg+"' for input "+str, result == avg);
  }
}
