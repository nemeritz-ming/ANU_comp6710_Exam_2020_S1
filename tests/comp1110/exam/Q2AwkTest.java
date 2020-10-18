package comp1110.exam;

import comp1110.exam.Q2Awk;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.*;

public class Q2AwkTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    static final String INPUT_FILENAME = "assets/Q2disc.csv";
    static final String OUTPUT_FILENAME = "assets/output.csv";

    @Before
    public void setup() {
        try {
            Files.deleteIfExists(Paths.get(OUTPUT_FILENAME));
            Files.deleteIfExists(Paths.get("assets/chart.csv"));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Test
    public void testJavadocExample() {
        final String inputFilename = "assets/Q2kate.csv";
        final String outputFilename = "assets/chart.csv";
        try {
            Q2Awk.extractColumnCSV(inputFilename, 2, outputFilename, true);
            assertTrue("expected extractColumnCSV to create file \"" + outputFilename + "\", but no file was found", Files.exists(Paths.get(outputFilename)));
            String expectedLineSep = getExpected(new Integer[]{1, 2, 39, 6}, false);
            String expectedNewLine = getExpected(new Integer[]{1, 2, 39, 6}, true);
            String actual = Files.readString(Paths.get(outputFilename));
            assertTrue("incorrect result returned from extractColumnCSV(\"" + inputFilename + ", 2, " + outputFilename + "\", " + true + ")\nexpected " + expectedNewLine + "\nbut got " + actual, expectedLineSep.equals(actual) || expectedNewLine.equals(actual));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test() {
        checkColumn(1, false, new String[]{"The Colour of Magic", "The Light Fantastic", "Equal Rites", "Mort", "Sourcery", "Wyrd Sisters", "Pyramids", "Guards! Guards!", "Eric", "Moving Pictures", "Reaper Man", "Witches Abroad", "Small Gods", "Lords and Ladies", "Men at Arms", "Soul Music", "Interesting Times", "Maskerade", "Feet of Clay", "Hogfather", "Jingo", "The Last Continent", "Carpe Jugulum", "The Fifth Elephant", "The Truth", "Thief of Time", "The Last Hero", "The Amazing Maurice and His Educated Rodents", "Night Watch", "The Wee Free Men", "Monstrous Regiment", "A Hat Full of Sky", "Going Postal", "Thud!", "Wintersmith", "Making Money", "Unseen Academicals", "I Shall Wear Midnight", "Snuff", "Raising Steam", "The Shepherd's Crown"});
        checkColumn(2, false, new Integer[]{1983, 1986, 1987, 1987, 1988, 1988, 1989, 1989, 1990, 1990, 1991, 1991, 1992, 1992, 1993, 1994, 1994, 1995, 1996, 1996, 1997, 1998, 1998, 1999, 2000, 2001, 2001, 2001, 2002, 2003, 2003, 2004, 2004, 2005, 2006, 2007, 2009, 2010, 2011, 2013, 2015});
    }

    @Test
    public void testMissing() {
        checkColumn(5, false, new Integer[]{});
        checkColumn(3, false, new String[]{"Rincewind", "Rincewind", "Witches", "Death", "Rincewind", "Witches", "City Watch", "Rincewind", "Industrial Revolution", "Death", "Witches", "Witches", "City Watch", "Death", "Rincewind", "Witches", "City Watch", "Death", "City Watch", "Rincewind", "Witches", "City Watch", "Industrial Revolution", "Death", "Rincewind", "City Watch", "Tiffany Aching", "Industrial Revolution", "Tiffany Aching", "Moist von Lipwig", "City Watch", "Tiffany Aching", "Moist von Lipwig", "Rincewind", "Tiffany Aching", "City Watch", "Moist von Lipwig", "Tiffany Aching"});
    }

    @Test
    public void testSorted() {
        checkColumn(5, true, new Integer[]{});
        checkColumn(2, true, new Integer[]{1983, 1986, 1987, 1987, 1988, 1988, 1989, 1989, 1990, 1990, 1991, 1991, 1992, 1992, 1993, 1994, 1994, 1995, 1996, 1996, 1997, 1998, 1998, 1999, 2000, 2001, 2001, 2001, 2002, 2003, 2003, 2004, 2004, 2005, 2006, 2007, 2009, 2010, 2011, 2013, 2015});
        checkColumn(3, true, new String[]{"City Watch", "City Watch", "City Watch", "City Watch", "City Watch", "City Watch", "City Watch", "City Watch", "Death", "Death", "Death", "Death", "Death", "Industrial Revolution", "Industrial Revolution", "Industrial Revolution", "Moist von Lipwig", "Moist von Lipwig", "Moist von Lipwig", "Rincewind", "Rincewind", "Rincewind", "Rincewind", "Rincewind", "Rincewind", "Rincewind", "Rincewind", "Tiffany Aching", "Tiffany Aching", "Tiffany Aching", "Tiffany Aching", "Tiffany Aching", "Witches", "Witches", "Witches", "Witches", "Witches", "Witches"});
    }

    @Test
    public void testFailures() {
        boolean expectedCaught = false;
        try {
            Q2Awk.extractColumnCSV("unknown.csv", 2, OUTPUT_FILENAME, false);
        } catch (NoSuchFileException e) {
            expectedCaught = true;
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.toString());
        }
        if (!expectedCaught) {
            fail("expected extractColumnCSV(\"unknown.csv\", ...) to throw NoSuchFileException, but no exception was thrown!");
        }
        expectedCaught = false;
        try {
            Q2Awk.extractColumnCSV(INPUT_FILENAME, 1, "no/such/path.csv", false);
        } catch (NoSuchFileException e) {
            expectedCaught = true;
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.toString());
        }
        if (!expectedCaught) {
            fail("expected extractColumnCSV(..., \"no/such/path.csv\") to throw NoSuchFileException, but no exception was thrown!");
        }
    }


    private <T> void checkColumn(int column, boolean sort, T[] rows) {
        try {
            Q2Awk.extractColumnCSV(INPUT_FILENAME, column, OUTPUT_FILENAME, sort);
            assertTrue("expected extractColumnCSV to create file \"" + OUTPUT_FILENAME + "\", but no file was found", Files.exists(Paths.get(OUTPUT_FILENAME)));
            String actual = Files.readString(Paths.get(OUTPUT_FILENAME));
            String expectedLineSep = getExpected(rows, false);
            String expectedNewLine = getExpected(rows, true);
            assertTrue("incorrect result returned from extractColumnCSV(\"" + INPUT_FILENAME + "\", " + column + ", \"" + OUTPUT_FILENAME + "\", " + sort + "),\nexpected " + expectedNewLine + "\n but got " + actual, expectedNewLine.equals(actual) || expectedLineSep.equals(actual));
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.toString());
        }
    }

    <T> String getExpected(T[] rows, boolean newline) {
        StringBuilder sb = new StringBuilder();
        if (newline) {
            Arrays.stream(rows).forEach(s -> sb.append(s).append("\n"));
        } else {
            Arrays.stream(rows).forEach(s -> sb.append(s).append(System.lineSeparator()));
        }
        return sb.toString();
    }

}
