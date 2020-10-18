package comp1110.exam;

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
public class Q5StringHashTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    String[] names = {"Bee", "Harding", "Nicholson", "Norrish", "Zheng", "Ignetik", "Kirk", "Goncharov", "Parmenter", "Hynes", "Kumar", "Lei", "Zhao", "Harrison", "Sang", "Lawton", "Tai", "Herring", "Aparekka Liyanagamag", "Qin", "Gan", "Kennedy", "Rankine", "Vos", "Benter", "Monro", "Hancock", "Whitehall", "Watson", "Lee", "Chen", "Chen", "Gong", "Markovic", "Bedwell-Wilson", "Rippon", "Tomlin", "Rogalewicz", "Sambridge", "Kar", "Mao", "Cicchini", "Hwang", "Ye", "Ren", "Chen", "Li", "Jia", "Shen", "Li", "Xu", "Liu", "Tan", "Chawda", "Wang", "Pan", "Maclaren", "Leaney", "Wang", "Nagpal", "Mallia", "Chen", "Zhu", "Zhao", "Shi", "Shao", "Song", "Rice", "Xu", "Rowett", "De Jongh", "Wisdom", "Galvin", "Zhang", "Parker", "Coulter", "Wu", "Murray", "Tang", "Li", "Ricardo", "Lim", "Magrath", "Miller", "Van Wyk", "Porteous", "Cartwright", "Smith", "Williams", "Wu", "Li", "Chee", "Ji", "Wu", "Yu", "Rodrigo", "Ye", "Zhao", "Yang", "Gupta", "Huang", "Lin", "Chen", "Schuetze", "Qin", "Narankhuu", "Hou", "Gong", "Na", "Tong", "Deng", "Su", "Cao", "Wang", "Song", "Lu", "Li", "Qian", "Zhou", "Yao", "Wu", "Kumar", "Liu", "Yang", "Xie", "Lu", "Li", "Zhao", "Feng", "Wen", "Chen", "Zhang", "Bai", "Yuan", "Qian", "Zhao", "Li", "Zhang", "He", "Ma", "Zhang", "Zhang", "Wang", "Xiong", "Guo", "Liu", "Gu", "Li", "Khan", "Lee", "Mohania", "Lu", "Zhang", "Zhuo", "Gajmer", "Tshering", "He", "Cui", "Sari", "Alsultan", "Lim", "Yu", "Pi", "Huang", "Ishak", "Yoo", "Snowdon", "Gatti", "Collings", "Rikmanis", "Newman", "Brieger", "Eckersley", "Gellel", "Jang", "Moore", "Whitcombe", "Brown", "Stanbury", "Horvat", "Larkins", "George", "Uszynski", "Gill", "Le", "Shan", "Kavanagh", "Sekoranja", "Sawers", "Craig", "Groves", "Towler Navarro", "Buckley", "Sergeev", "Richards", "Bui-Nguyen", "Cross", "Singh", "Jimenez", "Wong", "Flores-Condezo", "Storer", "Lu", "Sharma", "Koh", "Knill", "Shaik", "Liow", "Ochieng", "McNiven", "Hummer", "Wagener", "Wade", "Quarel", "Poetter", "Vella", "Zakopaylo", "Wu", "Rapson", "Zhang", "Ra", "Popiel", "Johnstone", "Meltzer", "Schacher", "Mailey", "Feng", "Rahman", "Qiu", "Kawatra", "Peng", "Gao", "Wang", "Millett", "Read Allan", "Mackay", "Walters", "Howes", "Hoque", "Callaway", "Hamer", "Bradshaw", "Port", "Watt", "Kerr", "Yu", "Shen", "Armstrong", "Huang", "Reynders", "Luo", "Trivedi", "Chen", "Chen", "Palaparthi", "Chen", "Gardiyawasam Punchihewa", "Saravanan", "Wu", "Du", "Lin", "Wei", "Wang", "Lu", "Ghangas", "John", "Phoochai", "Zhang"};

    public static final int HASH_ITERATIONS = 1000;

    @Test
    public void testInRange() {
        checkNotStringHash();
        Random r = new Random(0);
        for (int i = 0; i < HASH_ITERATIONS; i++) {
            int range = 1 + r.nextInt(100);
            String value = names[r.nextInt(names.length)];
            int h = Q5StringHash.hash(value, range);
            assertTrue("hash(" + value + ", " + range + ") returned a value out of range: " + h, h >= 0 && h < range);
        }
    }

    @Test
    public void testDeterministic() {
        checkNotStringHash();
        Random r = new Random(1);
        for (int i = 0; i < HASH_ITERATIONS; i++) {
            int range = 1 + r.nextInt(100);
            String value = names[r.nextInt(names.length)];
            int h = Q5StringHash.hash(value, range);
            for (int j = 0; j < 20; j++) {
                int h2 = Q5StringHash.hash(value, range);
                assertTrue("hash(" + value + ", " + range + ") returned a value out of range: " + h, h >= 0 && h < range);
                assertTrue("hash(" + value + ", " + range + ") returned two different values: " + h + ", " + h2, h2 == h);
            }
        }
    }

    @Test
    public void testUniformA() {
        checkNotStringHash();
        Random r = new Random(2);
        int range = 10;
        int[] count = new int[range];
        int expected = 0;
        for (int i = 0; i < 100 * HASH_ITERATIONS; i++) {
            String value = names[r.nextInt(names.length)];
            int h = Q5StringHash.hash(value, range);
            assertTrue("hash(" + value + ", " + range + ") returned a value out of range: " + h, h >= 0 && h < range);
            count[h]++;
            expected++;
        }
        expected /= range;
        for (int i = 0; i < range; i++) {
            assertTrue("hash returned an uneven distribution, index " + i + " received " + count[i] + ", when expected " + expected, count[i] > expected * 0.01 && count[i] < 0.99 * (expected * range));
        }
    }

    @Test
    public void testUniformB() {
        checkNotStringHash();
        Random r = new Random(3);
        int range = 20;
        int[] count = new int[range];
        int expected = 0;
        for (int i = 0; i < 100 * HASH_ITERATIONS; i++) {
            String value = names[r.nextInt(names.length)];
            int h = Q5StringHash.hash(value, range);
            assertTrue("hash(" + value + ", " + range + ") returned a value out of range: " + h, h >= 0 && h < range);
            count[h]++;
            expected++;
        }
        expected /= range;
        for (int i = 0; i < range; i++) {
            assertTrue("hash returned an uneven distribution, index " + i + " received " + count[i] + ", when expected " + expected, count[i] > expected * 0.1 && count[i] < 0.9 * (expected * range));
        }
    }

    @Test
    public void testUniformC() {
        checkNotStringHash();
        Random r = new Random(4);
        int range = 50;
        int[] count = new int[range];
        int expected = 0;
        for (int i = 0; i < 100 * HASH_ITERATIONS; i++) {
            String value = names[r.nextInt(names.length)];
            int h = Q5StringHash.hash(value, range);
            assertTrue("hash(" + value + ", " + range + ") returned a value out of range: " + h, h >= 0 && h < range);
            count[h]++;
            expected++;
        }
        expected /= range;
        for (int i = 0; i < range; i++) {
            assertTrue("hash returned an uneven distribution, index " + i + " received " + count[i] + ", when expected " + expected, count[i] > expected * 0.01 && count[i] < 0.99 * (expected * range));
        }
    }

    private void checkNotStringHash() {
        Random r = new Random();
        int range = 39;
        Set<String> myBuckets[] = new Set[range];
        Set<String> defaultBuckets[] = new Set[range];
        for (int i = 0; i < range; i++) {
            myBuckets[i] = new HashSet<>();
            defaultBuckets[i] = new HashSet<>();
        }

        for (int i = 0; i < 98 * HASH_ITERATIONS; i++) {
            String value = names[r.nextInt(names.length)];
            int m = Q5StringHash.hash(value, range);
            myBuckets[m].add(value);
            int n = Math.abs(value.hashCode()) % range;
            defaultBuckets[n].add(value);
        }
        for (Set<String> myBucket : myBuckets) {
            for (Set<String> defaultBucket : defaultBuckets) {
                assertFalse("It looks like you're using String.hashCode()!", myBucket.equals(defaultBucket));
            }
        }
    }

}
