package comp1110.exam;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q5PackageSpecificationTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    public static final int HASH_ITERATIONS = 100;

    static Q5PackageSpecification[] packages = new Q5PackageSpecification[200 * HASH_ITERATIONS];

    @Test
    public void testHashCodeDeterministic() {
        checkNotObjectHashCode();
        Random r = new Random(0);
        for (Q5PackageSpecification pack : packages) {
            int hash = pack.hashCode();
            for (int i = 0; i < 10; i++) {
                int hash2 = pack.hashCode();
                assertTrue("package " + pack + "  returned different values for hashCode(): " + hash + ", " + hash2, hash == hash2);
            }
        }
    }

    @Test
    public void testAllFields() {
        checkNotObjectHashCode();
        Q5PackageSpecification pack1 = new Q5PackageSpecification("curl", 6, 2);
        Q5PackageSpecification pack2 = new Q5PackageSpecification("curl", 6, 3);
        testDifferent(pack1, pack2);
        testDifferent(pack1, pack2);
        pack2 = new Q5PackageSpecification("curl", 2, 6);
        testDifferent(pack1, pack2);
        testDifferent(pack1, pack2);
        pack2 = new Q5PackageSpecification("curl", 7, 2);
        testDifferent(pack1, pack2);
        testDifferent(pack1, pack2);
        pack2 = new Q5PackageSpecification("reap", 6, 2);
        testDifferent(pack1, pack2);
        testDifferent(pack1, pack2);
        pack2 = new Q5PackageSpecification("lurc", 6, 2);
        testDifferent(pack1, pack2);
        testDifferent(pack1, pack2);
    }

    @Test
    public void testEquals() {
        Q5PackageSpecification pack1 = new Q5PackageSpecification("curl", 6, 2);
        assertTrue("pack1.equals(pack1) returned false", pack1.equals(pack1));
        Q5PackageSpecification pack2 = new Q5PackageSpecification("curl", 6, 3);
        assertFalse("packages " + pack1 + " and " + pack2 + " are not equal, but pack1.equals(pack2) returned true", pack1.equals(pack2));
        pack2.minorVersionNumber = 2;
        pack2.majorVersionNumber = 7;
        assertFalse("packages " + pack1 + " and " + pack2 + " are not equal, but pack1.equals(pack2) returned true", pack1.equals(pack2));
        pack2.majorVersionNumber = 6;
        pack2.name = "crul";
        assertFalse("packages " + pack1 + " and " + pack2 + " are not equal, but pack1.equals(pack2) returned true", pack1.equals(pack2));
        pack2.name = null;
        assertFalse("packages " + pack1 + " and " + pack2 + " are not equal, but pack1.equals(pack2) returned true", pack1.equals(pack2));
        pack1.name = null;
        assertTrue("packages " + pack1 + " and " + pack2 + " are equal, but pack1.equals(pack2) returned false", pack1.equals(pack2));
        pack2.name = "curl";
        assertFalse("packages " + pack1 + " and " + pack2 + " are not equal, but pack1.equals(pack2) returned true", pack1.equals(pack2));
        pack1.name = "curl";
        assertTrue("packages " + pack1 + " and " + pack2 + " are equal, but pack1.equals(pack2) returned false", pack1.equals(pack2));
    }

    @Test
    public void testUniformA() {
        checkNotObjectHashCode();
        // chiSquared critical value (DOF=10-1, prob=0.999) ~= 27.88
        testUniformity(10, 27.88);
    }

    @Test
    public void testUniformB() {
        checkNotObjectHashCode();
        // chiSquared critical value (DOF=50-1, prob=0.999) ~= 85.35
        testUniformity(50, 85.35);
    }

    private void testUniformity(int buckets, double chiSqCriticalValue) {
        Random r = new Random();
        int[] count = new int[buckets];
        int samples = buckets * HASH_ITERATIONS;
        for (int i = 0; i < samples; i++) {
            Q5PackageSpecification pack = packages[r.nextInt(packages.length)];
            int h = Math.abs(pack.hashCode()) % buckets;
            count[h]++;
        }

        double chiSq = chiSquaredUniform(samples, count);
        assertTrue("Distribution of hash function doesn't appear to be uniform over " + buckets + " buckets (chi squared value of " + chiSq + ").\nExpected " + samples / buckets + " elements per bucket, but got " + Arrays.toString(count), chiSq < chiSqCriticalValue);
    }

    private void checkNotObjectHashCode() {
        Random r = new Random();
        int range = 39;
        Set<Q5PackageSpecification> myBuckets[] = new Set[range];
        Set<Q5PackageSpecification> defaultBuckets[] = new Set[range];
        for (int i = 0; i < range; i++) {
            myBuckets[i] = new HashSet<>();
            defaultBuckets[i] = new HashSet<>();
        }

        for (int i = 0; i < 98 * HASH_ITERATIONS; i++) {
            Q5PackageSpecification pack = packages[r.nextInt(packages.length)];
            int m = Math.abs(pack.hashCode()) % range;
            myBuckets[m].add(pack);
            int n = Math.abs(pack.passThroughHash()) % range;
            defaultBuckets[n].add(pack);
        }
        for (Set<Q5PackageSpecification> myBucket : myBuckets) {
            for (Set<Q5PackageSpecification> defaultBucket : defaultBuckets) {
                assertFalse("It looks like you're using Object.hashCode()!", myBucket.equals(defaultBucket));
            }
        }
    }


    private void testDifferent(Q5PackageSpecification pack1, Q5PackageSpecification pack2) {
        int hash1 = pack1.hashCode();
        int hash2 = pack2.hashCode();
        assertTrue("packages " + pack1 + " and " + pack2 + " returned same hashCode(): " + hash1 + ", " + hash2, hash1 != hash2);
    }

    @BeforeClass
    public static void generatePackages() {
        Random r = new Random(0);
        for (int i = 0; i < packages.length; i++) {
            char[] randomName = new char[r.nextInt(5) + 3];
            for (int c = 0; c < randomName.length; c++) {
                if (c > 0 && r.nextInt(10) < 1) {
                    randomName[c] = (char) (r.nextInt(123 - 40) + '-');
                } else {
                    randomName[c] = (char) (r.nextInt(123 - 97) + 'a');
                }
            }

            packages[i] = new Q5PackageSpecification(String.valueOf(randomName), r.nextInt(10), r.nextInt(100));
            System.out.println(packages[i]);
        }
    }

    private static double chiSquaredUniform(int samples, int[] counts) {
        double uniformProb = 1.0 / counts.length;
        double total = 0;
        for (int i = 0; i < counts.length; i++) {
            double mi = ((double) samples) * uniformProb;
            total += ((double) counts[i] - mi) * ((double) counts[i] - mi) / mi;
        }
        return total;
    }


}
