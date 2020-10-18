package comp1110.exam;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;
import comp1110.exam.Q1FamilyTree.Individual;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q1FamilyTreeTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(500);

    @Test
    public void testNoAncestors() {
        test(new Individual("Son Goku", null), "Son Goku", "Son Goku", "Son Goku");
        test(ra(), "Ra", "Ra", RA_TREE);
        test(chaos(), "Chaos", "Chaos", CHAOS_TREE);

    }

    @Test
    public void testOneAncestor() {
        test(chaos(), "Tartarus", "Tartarus born of Chaos", CHAOS_TREE);
        test(osiris(), "Horus", "Horus born of Osiris", OSIRIS_TREE);
        test(rickardStark(), "Benjen", "Benjen born of Rickard", STARK_TREE);
        test(stephanieForrester(), "Angela", "Angela born of Stephanie", OSIRIS_TREE);
    }

    @Test
    public void testManyAncestorsNoDescendants() {
        test(chaos(), "Zeus", "Zeus born of Cronus born of Uranus born of Gaia born of Chaos", CHAOS_TREE);
        test(chaos(), "Prometheus", "Prometheus born of Iapetus born of Uranus born of Gaia born of Chaos", CHAOS_TREE);
        test(ra(), "Anubis", "Anubis born of Osiris born of Nut born of Shu born of Ra", RA_TREE);
        test(stephanieForrester(), "Kelly", "Kelly born of Steffy born of Ridge born of Stephanie", FORRESTER_TREE);
        test(stephanieForrester(), "Zende", "Zende born of Kristen born of Stephanie", FORRESTER_TREE);
        test(stephanieForrester(), "Dominick", "Dominick born of Felicia born of Stephanie", FORRESTER_TREE);
        test(rickardStark(), "Sansa", "Sansa born of Eddard born of Rickard", STARK_TREE);
    }

    @Test
    public void testBothAncestorsAndDescendants() {
        test(chaos(), "Uranus", "Uranus born of Gaia born of Chaos", CHAOS_TREE);
        test(ra(), "Seth", "Seth born of Nut born of Shu born of Ra", RA_TREE);
        test(stephanieForrester(), "Thorne", "Thorne born of Stephanie", FORRESTER_TREE);
        test(rickardStark(), "Eddard", "Eddard born of Rickard", STARK_TREE);
    }

    @Test
    public void testNotADescendent() {
        test(chaos(), "Thor", null, CHAOS_TREE);
        test(ra(), "Buddha", null, RA_TREE);
        test(stephanieForrester(), "Caroline", null, FORRESTER_TREE);
        test(rickardStark(), "Jaime", null, STARK_TREE);

    }

    private void test(Individual root, String targetName, String expectedResult, String tree) {
        String result = Q1FamilyTree.getAncestry(root, targetName);
        if (expectedResult == null) {
            assertTrue("Q1FamilyTree.getAncestry(" + root.name.toLowerCase() + ", " + targetName + ") returned \"" + result + "\" but expected null, tree = \n" + tree, result == null);
        } else {
            assertTrue("Q1FamilyTree.getAncestry(" + root.name.toLowerCase() + ", " + targetName + ") returned \"" + result + "\" but expected \"" + expectedResult + "\", tree = \n" + tree, expectedResult.equals(result));
        }
    }

    // the following methods construct family trees
    private Individual osiris() {
        Individual horus = new Individual("Horus", null);
        Individual anubis = new Individual("Anubis", null);
        return new Individual("Osiris", new Individual[]{horus, anubis});
    }

    private static final String OSIRIS_TREE = "Osiris__Horus\n      |_Anubis";

    private Individual ra() {
        Individual seth = new Individual("Seth", null);
        Individual nut = new Individual("Nut", new Individual[]{osiris(), seth});
        Individual shu = new Individual("Shu", new Individual[]{nut});
        return new Individual("Ra", new Individual[]{shu});
    }

    private static final String RA_TREE
            = "Ra__Shu__Nut__Osiris__Horus\n" +
            "            |       |_Anubis\n" +
            "            |_Seth";

    private Individual stephanieForrester() {
        Individual douglas = new Individual("Douglas", null);
        Individual thomas = new Individual("Thomas", new Individual[]{douglas});
        Individual kelly = new Individual("Kelly", null);
        Individual steffy = new Individual("Steffy", new Individual[]{kelly});
        Individual phoebe = new Individual("Phoebe", null);
        Individual rj = new Individual("R.J.", null);
        Individual ridge = new Individual("Ridge", new Individual[]{thomas, steffy, phoebe, rj});
        Individual zende = new Individual("Zende", null);
        Individual kristen = new Individual("Kristen", new Individual[]{zende});
        Individual angela = new Individual("Angela", null);
        Individual alexandria = new Individual("Alexandria", null);
        Individual thorne = new Individual("Thorne", new Individual[]{alexandria});
        Individual dominick = new Individual("Dominick", null);
        Individual felicia = new Individual("Felicia", new Individual[]{dominick});
        return new Individual("Stephanie", new Individual[]{ridge, kristen, angela, thorne, alexandria, felicia});
    }

    private static final String FORRESTER_TREE
            = "Stephanie__Ridge__Thomas__Douglas\n" +
            "         |      |_Steffy__Kelly\n" +
            "         |      |_Phoebe\n" +
            "         |      |_R.J.\n" +
            "         |_Kristen__Zende\n" +
            "         |_Angela\n" +
            "         |_Thorne__Alexandria\n" +
            "         |_Alexandria\n" +
            "         |_Felicia__Dominick";

    private Individual chaos() {
        Individual zeus = new Individual("Zeus", null);
        Individual cronus = new Individual("Cronus", new Individual[]{zeus});
        Individual prometheus = new Individual("Prometheus", null);
        Individual iapetus = new Individual("Iapetus", new Individual[]{prometheus});
        Individual uranus = new Individual("Uranus", new Individual[]{cronus, iapetus});
        Individual gaia = new Individual("Gaia", new Individual[]{uranus});
        Individual tartarus = new Individual("Tartarus", null);
        return new Individual("Chaos", new Individual[]{tartarus, gaia});
    }

    private static final String CHAOS_TREE
            = "Chaos__Tartarus\n" +
            "     |_Gaia__Uranus__Cronus__Zeus\n" +
            "                   |_Iapetus__Prometheus";

    private Individual rickardStark() {
        Individual jon = new Individual("Jon Snow", null);
        Individual rickon = new Individual("Rickon", null);
        Individual brandon2 = new Individual("Brandon", null);
        Individual arya = new Individual("Arya", null);
        Individual sansa = new Individual("Sansa", null);
        Individual robb = new Individual("Robb", null);
        Individual benjen = new Individual("Benjen", null);
        Individual eddard = new Individual("Eddard", new Individual[]{robb, sansa, arya, brandon2, rickon, jon});
        Individual brandon = new Individual("Brandon", null);
        return new Individual("Rickard", new Individual[]{brandon, eddard, benjen});
    }

    private static final String STARK_TREE
            = "Rickard__Brandon\n" +
            "       |_Eddard__Robb\n" +
            "       |       |_Sansa\n" +
            "       |       |_Arya\n" +
            "       |       |_Brandon\n" +
            "       |       |_Rickon\n" +
            "       |       |_Jon Snow\n" +
            "       |_Benjen";
}
