package comp1110.exam;

import comp1110.exam.Q1CropRotation.Vegetable;
import comp1110.exam.Q1CropRotation.Group;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.*;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q1CropRotationTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    final static Vegetable BOK_CHOY = new Vegetable("Bok Choy", Group.BRASSICA);
    final static Vegetable CAPSICUM = new Vegetable("Capsicum", Group.FRUITING);
    final static Vegetable EGGPLANT = new Vegetable("Eggplant", Group.FRUITING);
    final static Vegetable GAI_LAN = new Vegetable("Gai Lan", Group.BRASSICA);
    final static Vegetable KALE = new Vegetable("Kale", Group.BRASSICA);
    final static Vegetable LEEK = new Vegetable("Leek", Group.ALLIUM);
    final static Vegetable ONION = new Vegetable("Onion", Group.ALLIUM);
    final static Vegetable PEA = new Vegetable("Pea", Group.LEGUME);
    final static Vegetable RUNNER_BEAN = new Vegetable("Runner Bean", Group.LEGUME);
    final static Vegetable TOMATO = new Vegetable("Tomato", Group.FRUITING);

    @Test
    public void testTrivial() {
        checkAllRotations("", 1); // no veges
        checkAllRotations("L", 0); // zero seasons
        checkAllRotations("L", 1, "L"); // one crop, one season
        checkAllRotations("L", 2); // one crop, multiple seasons
    }

    @Test
    public void testTwoSeasons() {
        checkAllRotations("TG", 2); // no valid succession order
        checkAllRotations("OB", 2, "BO");
        checkAllRotations("KR", 2, "RK");
        checkAllRotations("RK", 2, "RK");
        checkAllRotations("GR", 2, "RG");
        checkAllRotations("TP", 2, "TP");
    }

    @Test
    public void testFourSeasons() {
        checkAllRotations("GCRK", 4); // only three groups
        checkAllRotations("KOTP", 4, shuffleComplete(4, new String[]{"KOTP"}));
        checkAllRotations("LBCP", 4, shuffleComplete(4, new String[]{"PBLC"}));
        checkAllRotations("GCRO", 4, shuffleComplete(4, new String[]{"RGOC"}));
    }

    @Test
    public void testFiveSeasons() {
        checkAllRotations("CKOTE", 5); // only three groups
        checkAllRotations("KOTPR", 5, "RKOTP", "PKOTR");
        checkAllRotations("ECRGL", 5, "CRGLE", "ERGLC");
        checkAllRotations("OCRGL", 5, "OCRGL", "LCRGO");
        checkAllRotations("ROTGP", 5, "RGOTP", "PGOTR");
    }

    @Test
    public void testEightSeasons() {
        checkAllRotations("EKLCPRGO", 8, shuffleComplete(8, new String[]{"CRKOEPGL", "CRGOEPKL", "EPGOCRKL", "CPGOERKL", "CPGLERKO", "EPKLCRGO", "EPKOCRGL", "CPKLERGO", "CPKOERGL"}));
        checkAllRotations("LCRPTGOB", 8, shuffleComplete(8, new String[]{"CRBOTPGL", "CRGOTPBL", "TPGOCRBL", "CPGOTRBL", "CPGLTRBO", "TPBLCRGO", "TPBOCRGL", "CPBLTRGO", "CPBOTRGL"}));
    }

    private void checkAllRotations(String vegeString, int seasons, String... expectedRotations) {
        Set<Vegetable> veges = new HashSet<>();
        veges.addAll(vegFromString(vegeString));
        Set<List<Vegetable>> expected = rotationFromStrings(expectedRotations);
        Set<List<Vegetable>> result = Q1CropRotation.getAllRotations(veges, seasons);

        String expectedString;
        if (expected.isEmpty()) {
            expectedString = "empty set";
        } else {
            StringBuilder sb = new StringBuilder();
            for (List<Vegetable> r : expected) {
                sb.append("- ").append(r).append("\n");
            }
            expectedString = sb.toString();
        }

        assertNotNull("A null value was returned from Q1CropRotation.getAllRotations(" + veges + ", " + seasons + ")\nexpected:\n" + expectedString, result);

        String resultString;
        if (result.isEmpty()) {
            resultString = "empty set";
        } else {
            StringBuilder sb2 = new StringBuilder();
            for (List<Vegetable> v : result) {
                sb2.append("- ").append(v).append("\n");
            }
            resultString = sb2.toString();
        }

        nextExpected:
        for (List<Vegetable> e : expected) {
            for (List<Vegetable> r : result) {
                if (e.equals(r)) {
                    continue nextExpected;
                }
            }

            fail("An expected valid rotation " + e + " \nwas not returned from Q1CropRotation.getAllRotations(" + veges + ", " + seasons + ")\nexpected:\n" + expectedString + "but got:\n" + resultString);
        }

        nextResult:
        for (List<Vegetable> r : result) {
            for (List<Vegetable> e : expected) {
                if (e.equals(r)) {
                    continue nextResult;
                }
            }

            fail("Unexpected rotation " + r + "\nwas returned from Q1CropRotation.getAllRotations(" + veges + ", " + seasons + ")\nexpected:\n" + expectedString);
        }
    }

    private List<Vegetable> vegFromString(String v) {
        List<Vegetable> result = new ArrayList<>();
        int i = 0;
        for (char c : v.toCharArray()) {
            switch (c) {
                case 'B':
                    result.add(BOK_CHOY);
                    break;
                case 'C':
                    result.add(CAPSICUM);
                    break;
                case 'E':
                    result.add(EGGPLANT);
                    break;
                case 'G':
                    result.add(GAI_LAN);
                    break;
                case 'K':
                    result.add(KALE);
                    break;
                case 'L':
                    result.add(LEEK);
                    break;
                case 'O':
                    result.add(ONION);
                    break;
                case 'P':
                    result.add(PEA);
                    break;
                case 'R':
                    result.add(RUNNER_BEAN);
                    break;
                case 'T':
                    result.add(TOMATO);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown vegetable character: " + c);
            }
        }
        return result;
    }

    private Set<List<Vegetable>> rotationFromStrings(String... rotations) {
        Set<List<Vegetable>> result = new HashSet<>();

        int i = 0;
        for (String s : rotations) {
            result.add(vegFromString(s));
        }
        return result;
    }

    private String[] shuffleComplete(int seasons, String[] source) {
        String[] result = new String[source.length * seasons];
        int i = 0;
        for (String s : source) {
            for (int j = 0; j < s.length(); j++) {
                result[i++] = s.substring(j) + s.substring(0, j);
            }
        }
        return result;
    }

}


