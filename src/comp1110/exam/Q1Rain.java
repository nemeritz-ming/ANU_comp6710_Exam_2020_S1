package comp1110.exam;

/**
 * COMP1110 Exam, Question 1.2
 */
public class Q1Rain {

    static final int MAGIC = 999;

    /**
     * This function takes an array of integers and returns an integer
     * that is the average of the 'included' integers in the array,
     * or -1 if there are no 'included' integers in the array.
     * <p>
     * An array element is 'included' if:
     * a) it is greater than or equal to zero, and
     * b) it appears earlier in the array than the MAGIC number (999)
     * <p>
     * Because the return value is an integer, the average should be
     * calculated using integer division.
     * <p>
     * Some examples:
     * <p>
     * [ 1, 999] -> 1
     * [ 1, 3, 999] -> 2
     * [ 1, 6, 999, 4] -> 3
     * [ 1, -3, 5, 999] -> 3
     *
     * @param in An array of integers
     * @return The average of the 'included' values in the input array, or
     * -1 if there are no included values in the input array.
     */
    public static int avg(int[] in) {
        // FIXME complete this method
        return 0;
    }
}
