package comp1110.exam;

import comp1110.exam.Q1MealPlan.Food;
import comp1110.exam.Q1MealPlan.Meal;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class Q1MealPlanTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    final static Food WAFFLES = new Food("Waffles", Meal.BREAKFAST);
    final static Food APPLE_PIE = new Food("Apple Pie", Meal.DESSERT);
    final static Food POTATO_GRATIN = new Food("Potato Gratin", Meal.DINNER);
    final static Food SUSHI = new Food("Sushi", Meal.LUNCH);
    final static Food FRIED_RICE = new Food("Fried Rice", Meal.LUNCH);
    final static Food RISOTTO = new Food("Risotto", Meal.DINNER);
    final static Food VEGETABLE_SOUP = new Food("Vegetable Soup", Meal.LUNCH);
    final static Food MEUSLI = new Food("Meusli", Meal.BREAKFAST);
    final static Food CAKE = new Food("Cake", Meal.DESSERT);
    final static Food TARTE_TATIN = new Food("Tarte Tatin", Meal.DESSERT);


    @Test
    public void testTrivial() {
        checkAllRotations("", 1); // no foods
        checkAllRotations("T", 0); // no meals
        checkAllRotations("T", 1, "T"); // one food, one meal
        checkAllRotations("T", 2); // one food, multiple meals
    }

    @Test
    public void testTwoMeals() {
        checkAllRotations("AS", 2); // no valid succession order
        checkAllRotations("RC", 2, "RC");
        checkAllRotations("SP", 2, "SP");
        checkAllRotations("WT", 2, "TW");
        checkAllRotations("PF", 2, "FP");
        checkAllRotations("MV", 2, "MV");
    }

    @Test
    public void testFourMeals() {
        checkAllRotations("SVPA", 4); // only three meals
        checkAllRotations("CVPW", 4, shuffleComplete(4, new String[]{"WVPC"}));
        checkAllRotations("RMFA", 4, shuffleComplete(4, new String[]{"MFRA"}));
        checkAllRotations("STWP", 4, shuffleComplete(4, new String[]{"WSPT"}));
    }

    @Test
    public void testFiveMeals() {
        checkAllRotations("VPRAT", 5); // only three meals
        checkAllRotations("SPWAT", 5, "AWSPT", "TWSPA");
        checkAllRotations("FRPCM", 5, "PCMFR", "RCMFP");
        checkAllRotations("RTVMW", 5, "MVRTW", "WVRTM");
        checkAllRotations("SPAFW", 5, "SPAWF", "FPAWS");
    }

    @Test
    public void testTwoDays() {
        checkAllRotations("ATSVPRWM", 8, shuffleComplete(8, new String[]{"PAWSRTMV", "PAMSRTWV", "MVRTWSPA", "PAWVRTMS", "VRAWSPTM", "MSPTWVRA", "TMSRAWVP", "VPTWSRAM"}));
        checkAllRotations("TCVFRPWM", 8, shuffleComplete(8, new String[]{"TWFPCMVR", "MFPCWVRT", "FPTMVRCW", "TMFRCWVP", "MFRTWVPC", "CMFPTWVR", "TWFRCMVP", "FRTMVPCW"}));
    }

    private void checkAllRotations(String mealString, int numMeals, String... expectedRotations) {
        Set<Food> foods = new HashSet<>();
        foods.addAll(mealFromString(mealString));
        Set<List<Food>> expected = rotationFromStrings(expectedRotations);
        Set<List<Food>> result = Q1MealPlan.getAllMealPlans(foods, numMeals);

        String expectedString;
        if (expected.isEmpty()) {
            expectedString = "empty set";
        } else {
            StringBuilder sb = new StringBuilder();
            for (List<Food> r : expected) {
                sb.append("- ").append(r).append("\n");
            }
            expectedString = sb.toString();
        }

        assertNotNull("A null value was returned from Q1MealPlanning.getAllMealPlans(" + foods + ", " + numMeals + ")\nexpected:\n" + expectedString, result);

        String resultString;
        if (result.isEmpty()) {
            resultString = "empty set";
        } else {
            StringBuilder sb2 = new StringBuilder();
            for (List<Food> m : result) {
                sb2.append("- ").append(m).append("\n");
            }
            resultString = sb2.toString();
        }

        nextExpected:
        for (List<Food> e : expected) {
            for (List<Food> r : result) {
                if (e.equals(r)) {
                    continue nextExpected;
                }
            }

            fail("An expected valid plan " + e + " \nwas not returned from Q1MealPlanning.getAllMealPlans(" + foods + ", " + numMeals + ")\nexpected:\n" + expectedString + "but got:\n" + resultString);
        }

        nextResult:
        for (List<Food> r : result) {
            for (List<Food> e : expected) {
                if (e.equals(r)) {
                    continue nextResult;
                }
            }

            fail("Unexpected plan " + r + "\nwas returned from Q1MealPlanning.getAllMealPlans(" + foods + ", " + numMeals + ")\nexpected:\n" + expectedString);
        }
    }

    private List<Food> mealFromString(String m) {
        List<Food> result = new ArrayList<>();
        int i = 0;
        for (char c : m.toCharArray()) {
            switch (c) {
                case 'A':
                    result.add(APPLE_PIE);
                    break;
                case 'C':
                    result.add(CAKE);
                    break;
                case 'F':
                    result.add(FRIED_RICE);
                    break;
                case 'M':
                    result.add(MEUSLI);
                    break;
                case 'P':
                    result.add(POTATO_GRATIN);
                    break;
                case 'R':
                    result.add(RISOTTO);
                    break;
                case 'S':
                    result.add(SUSHI);
                    break;
                case 'T':
                    result.add(TARTE_TATIN);
                    break;
                case 'V':
                    result.add(VEGETABLE_SOUP);
                    break;
                case 'W':
                    result.add(WAFFLES);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown meal character: " + c);
            }
        }
        return result;
    }

    private Set<List<Food>> rotationFromStrings(String... rotations) {
        Set<List<Food>> result = new HashSet<>();

        int i = 0;
        for (String s : rotations) {
            result.add(mealFromString(s));
        }
        return result;
    }

    private String[] shuffleComplete(int numMeals, String[] source) {
        String[] result = new String[source.length * numMeals];
        int i = 0;
        for (String s : source) {
            for (int j = 0; j < s.length(); j++) {
                result[i++] = s.substring(j) + s.substring(0, j);
            }
        }
        return result;
    }

}



