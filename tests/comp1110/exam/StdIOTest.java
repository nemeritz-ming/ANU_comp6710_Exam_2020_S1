package comp1110.exam;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.Timeout;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by steveb on 27/05/2017.
 */
public class StdIOTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    private PrintStream realStdOut = System.out;
    private PrintStream readStdErr = System.err;

    public static final String KEY = "'#' represents newline, '_' represents space";

    /* get rid of OS-specific whitespace gorp */
    public String cleanupCRLF(String input) {
        return cleanupCRLF(input, false);
    }

    public String cleanupCRLF(String input, boolean replace) {
        input = input.replaceAll("\\n", "#");
        input = input.replaceAll("\\r", "");
        input = input.replaceAll("\\s*#", "#");
        if (replace) {
            input = input.replaceAll("\\s", "_");
          //  input = input.replaceAll("#", "#\n");
        } else {
            input = input.replaceAll("#", System.lineSeparator());
        }
        return input;
    }

    public final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @After
    public void cleanUpStreams() {
        System.setOut(realStdOut);
        System.setErr(readStdErr);
    }

}
