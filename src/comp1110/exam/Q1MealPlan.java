package comp1110.exam;

import java.util.List;
import java.util.Set;

/**
 * COMP1110 Final Exam, Question 1iii
 */
public class Q1MealPlan {
    /**
     * Each food is assigned to one of four groups.
     */
    public enum Meal {
        BREAKFAST, LUNCH, DINNER, DESSERT
    }

    public static class Food {
        String dish;
        Meal meal;

        public Food(String dish, Meal meal) {
            this.dish = dish;
            this.meal = meal;
        }

        @Override
        public String toString() {
            return dish + " (" + meal.name().toLowerCase() + ")";
        }
    }

    /**
     * Get all meal plans that can be composed from the provided
     * set of foods for the given number of meals.
     * A meal plan is a sequence of foods that are eaten sequentially.
     * One food is eaten per meal, and any food may only be eaten at most once.
     * Foods must be eaten in order of their Meal type according to the following rules:
     * - a BREAKFAST may only be followed by a LUNCH food;
     * - a LUNCH may only be followed by a DINNER food;
     * - an DINNER may only be followed by a DESSERT food; and
     * - a DESSERT may only be followed by a BREAKFAST food.
     * <p>
     * For example, the call
     * getAllMealPlans([Cake (dessert), Risotto (dinner)], 2)
     * returns a set containing a single plan:
     * - [Risotto (dinner), Cake (dessert)]
     * because a DINNER meal may only be followed by a DESSERT meal.
     * <p>
     * The call
     * getAllMealPlans([Cake (dessert), Vegetable Soup (lunch), Waffles (breakfast), Potato Gratin (dinner)], 4)
     * returns a set containing four plans:
     * - [ Waffles (breakfast), Vegetable Soup (lunch), Potato Gratin (dinner), Cake (dessert)]
     * - [Vegetable Soup (lunch), Potato Gratin (dinner), Cake (dessert), Waffles (breakfast)]
     * - [Potato Gratin (dinner), Cake (dessert), Waffles (breakfast), Vegetable Soup (lunch)]
     * - [Cake (dessert), Waffles (breakfast), Vegetable Soup (lunch), Potato Gratin (dinner)]
     * <p>
     * If no valid meal plan can be found, an empty list is returned.
     * For example, the call:
     * getAllMealPlans([Apple Pie (dessert), Sushi (lunch)], 2)
     * returns an empty set, because a DESSERT food cannot be followed by
     * a LUNCH food, and a LUNCH food cannot be followed by a DESSERT food.
     *
     * @param foods    the set of foods from which to construct a meal plan
     * @param numMeals the number of meals
     * @return the set of all possible meal plans of the provided foods for the
     * given number of meals
     */
    public static Set<List<Food>> getAllMealPlans(Set<Food> foods, int numMeals) {
        // FIXME complete this method
        return null;
    }
}
