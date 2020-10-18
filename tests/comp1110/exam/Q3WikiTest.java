package comp1110.exam;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.Set;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q3WikiTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(500);

    Integer[] articleIds = new Integer[]{31353, 8091, 50223, 72758, 159979, 159973, 25781, 142540};
    String[] names = new String[]{
            "The Hitchhikers Guide to the Galaxy",
            "Douglas Adams",
            "Dirk Gently's Holistic Detective Agency",
            "BBC Radio 4",
            "Arthur Dent",
            "Marvin the Paranoid Android",
            "Robot",
            "Mos Def"};
    String[] categories = new String[]{
            "Book",
            "Person",
            "Book",
            "Radio Station",
            "Fictional Character",
            "Fictional Character",
            "Technology",
            "Person"};
    String[][] editors = new String[][]{
            new String[]{"Almy", "MarkH21", "Monkbot", "Bender the Bot"},
            new String[]{"Bender the Bot", "Internet Archive Bot"},
            new String[]{"Whovian99", "DoctorWho42", "Monkbot"},
            new String[]{"Stereorock", "Monkbot"},
            new String[]{"Bender the Bot", "Feminist"},
            new String[]{"Perfect Orange Sphere", "Narky Blert", "Bender the Bot"},
            new String[]{"Jku456", "Internet Archive Bot", "Monkbot"},
            new String[]{"Bagumba", "Monkbot", "Internet Archive Bot"}};
    Integer[][] links = new Integer[][]{new Integer[]{8091, 72758, 159979, 159973, 142540}, new Integer[]{31353, 50223}, new Integer[]{8091}, new Integer[]{}, new Integer[]{31353, 8091}, new Integer[]{31353, 25781, 8091, 159979}, new Integer[]{}, new Integer[]{31353}};

    Q3Wiki wiki;

    @Before
    public void setup() {
        wiki = new Q3Wiki();
        addInitialArticles();
    }

    private void addInitialArticles() {
        for (int i = 0; i < articleIds.length; i++) {
            wiki.addArticle(articleIds[i], names[i], categories[i], Set.of(editors[i]));
        }

        for (int i = 0; i < articleIds.length; i++) {
            for (Integer target : links[i]) {
                wiki.addLink(articleIds[i], target);
            }
        }
    }

    private void addMoreArticles() {
        wiki.addArticle(56163, "Starship", "Technology", Set.of(new String[]{"Soumya-8974", "Monkbot", "Lyndaship"}));
        wiki.addLink(159973, 56163);
        wiki.addArticle(79904, "Ford Prefect", "Fictional Character", Set.of(new String[]{"Monkbot", "BrownHairedGirl", "Internet Archive Bot"}));
        wiki.addLink(79904, 31353);
        wiki.addLink(79904, 8091);
        wiki.addLink(79904, 159979);
    }

    @Test
    public void testGetArticleCount() {
        Q3Wiki empty = new Q3Wiki();
        assertEquals("getArticleCount() returned incorrect number of articles", 0, empty.getArticleCount());
        assertEquals("getArticleCount() returned incorrect number of articles", 8, wiki.getArticleCount());
        addMoreArticles();
        assertEquals("getArticleCount() returned incorrect number of articles", 10, wiki.getArticleCount());
    }

    @Test
    public void testGetArticleCountDuplicate() {
        addInitialArticles();
        assertEquals("getArticleCount() returned incorrect number of articles", 8, wiki.getArticleCount());
        addMoreArticles();
        addMoreArticles();
        assertEquals("getArticleCount() returned incorrect number of articles", 10, wiki.getArticleCount());
    }

    @Test
    public void testGetArticleCountDelete() {
        Q3Wiki empty = new Q3Wiki();
        final int NON_EXISTENT_ARTICLE = -1;
        wiki.deleteArticle(NON_EXISTENT_ARTICLE);
        assertEquals("getArticleCount() returned incorrect number of articles", 0, empty.getArticleCount());
        wiki.deleteArticle(articleIds[0]);
        wiki.deleteArticle(articleIds[0]);
        assertEquals("getArticleCount() returned incorrect number of articles", 7, wiki.getArticleCount());
    }

    @Test
    public void testGetArticlesForCategoryEmpty() {
        assertNotNull("getArticlesForCategory(\"Games\") returned null, expected empty set", wiki.getArticlesForCategory("Games"));
        assertTrue("getArticlesForCategory(\"Games\") returned non-empty set, expected empty", wiki.getArticlesForCategory("Games").isEmpty());
        assertFalse("getArticlesForCategory(\"Person\") returned empty set, expected non-empty", wiki.getArticlesForCategory("Person").isEmpty());
    }

    @Test
    public void testGetArticlesForCategory() {
        checkCategory("Person", Set.of(8091, 142540));
        checkCategory("Book", Set.of(31353, 50223));
        checkCategory("Technology", Set.of(25781));
        checkCategory("Fictional Character", Set.of(159979, 159973));
        addMoreArticles();
        checkCategory("Technology", Set.of(25781, 56163));
        checkCategory("Fictional Character", Set.of(159979, 159973, 79904));
    }

    @Test
    public void testGetArticlesForCategoryDuplicate() {
        addInitialArticles();
        testGetArticlesForCategory();
    }

    @Test
    public void testGetArticlesForCategoryDelete() {
        wiki.deleteArticle(159973);
        wiki.deleteArticle(50223);
        checkCategory("Book", Set.of(31353));
        checkCategory("Fictional Character", Set.of(159979));
    }

    private void checkCategory(String category, Set<Integer> expected) {
        assertEquals("getArticlesForCategory(\"" + category + "\") returned incorrect set", expected, wiki.getArticlesForCategory(category));
    }

    @Test
    public void testGetArticlesEditedByEmpty() {
        assertNotNull("getArticlesEditedBy(\"No one\") returned null, expected empty set", wiki.getArticlesEditedBy("No one"));
        assertTrue("getArticlesEditedBy(\"No one\") returned non-empty set, expected empty", wiki.getArticlesEditedBy("No one").isEmpty());
        assertFalse("getArticlesEditedBy(\"Bender the Bot\") returned empty set, expected non-empty", wiki.getArticlesEditedBy("Bender the Bot").isEmpty());
    }

    @Test
    public void testGetArticlesEditedBy() {
        checkEditor("Bender the Bot", Set.of(159973, 31353, 159979, 8091));
        checkEditor("Internet Archive Bot", Set.of(8091, 25781, 142540));
        addMoreArticles();
        checkEditor("Bender the Bot", Set.of(159973, 31353, 159979, 8091));
        checkEditor("Internet Archive Bot", Set.of(8091, 25781, 142540, 79904));
    }

    @Test
    public void testGetArticlesEditedByDuplicate() {
        addMoreArticles();
        addInitialArticles();
        addMoreArticles();
        checkEditor("Bender the Bot", Set.of(159973, 31353, 159979, 8091));
        checkEditor("Internet Archive Bot", Set.of(8091, 25781, 142540, 79904));
    }

    @Test
    public void testGetArticlesEditedByDelete() {
        wiki.deleteArticle(articleIds[1]);
        checkEditor("Bender the Bot", Set.of(159973, 31353, 159979));
        checkEditor("No one", Set.of());
        checkEditor("Internet Archive Bot", Set.of(25781, 142540));
    }

    private void checkEditor(String editor, Set<Integer> expected) {
        assertEquals("getArticlesEditedBy(\"" + editor + "\") returned incorrect set", expected, wiki.getArticlesEditedBy(editor));
    }

    @Test
    public void testGetMaxIncomingLinks() {
        Q3Wiki empty = new Q3Wiki();
        assertEquals("getMaxIncomingLinks() returned incorrect value", 0, empty.getMaxIncomingLinks());
        assertEquals("getMaxIncomingLinks() returned incorrect value", 4, wiki.getMaxIncomingLinks());
    }

    @Test
    public void testGetMaxIncomingLinksDuplicate() {
        addInitialArticles();
        assertEquals("getMaxIncomingLinks() returned incorrect value", 4, wiki.getMaxIncomingLinks());
    }

    @Test
    public void testGetMaxIncomingLinksDelete() {
        wiki.deleteArticle(31353);
        assertEquals("getMaxIncomingLinks() returned incorrect value", 3, wiki.getMaxIncomingLinks());
        wiki.deleteArticle(8091);
        assertEquals("getMaxIncomingLinks() returned incorrect value", 1, wiki.getMaxIncomingLinks());
    }

    @Test
    public void testGetNumCrossCategoryLinks() {
        assertEquals("getNumCrossCategoryLinks() returned incorrect value", 14, wiki.getNumCrossCategoryLinks());
        addMoreArticles();
        assertEquals("getNumCrossCategoryLinks() returned incorrect value", 17, wiki.getNumCrossCategoryLinks());
    }

    @Test
    public void testGetNumCrossCategoryLinksDuplicate() {
        addInitialArticles();
        assertEquals("getNumCrossCategoryLinks() returned incorrect value", 14, wiki.getNumCrossCategoryLinks());
        addMoreArticles();
        addMoreArticles();
        assertEquals("getNumCrossCategoryLinks() returned incorrect value", 17, wiki.getNumCrossCategoryLinks());
    }

    @Test
    public void testGetNumCrossCategoryLinksDelete() {
        wiki.deleteArticle(31353);
        assertEquals("getNumCrossCategoryLinks() returned incorrect value", 5, wiki.getNumCrossCategoryLinks());
        wiki.deleteArticle(159973);
        assertEquals("getNumCrossCategoryLinks() returned incorrect value", 3, wiki.getNumCrossCategoryLinks());
    }

    @Test
    public void testGetNumCategoriesEdited() {
        assertEquals("getNumCategoriesEdited() returned incorrect value", 1, wiki.getNumCategoriesEdited("Bagumba"));
        assertEquals("getNumCategoriesEdited() returned incorrect value", 3, wiki.getNumCategoriesEdited("Bender the Bot"));
        assertEquals("getNumCategoriesEdited() returned incorrect value", 4, wiki.getNumCategoriesEdited("Monkbot"));
    }

    @Test
    public void testGetNumCategoriesEditedDuplicate() {
        addInitialArticles();
        assertEquals("getNumCategoriesEdited() returned incorrect value", 1, wiki.getNumCategoriesEdited("DoctorWho42"));
        assertEquals("getNumCategoriesEdited() returned incorrect value", 2, wiki.getNumCategoriesEdited("Internet Archive Bot"));
        assertEquals("getNumCategoriesEdited() returned incorrect value", 4, wiki.getNumCategoriesEdited("Monkbot"));
    }

    @Test
    public void testGetNumCategoriesEditedDelete() {
        wiki.deleteArticle(142540);
        assertEquals("getNumCategoriesEdited() returned incorrect value", 0, wiki.getNumCategoriesEdited("Bagumba"));
        assertEquals("getNumCategoriesEdited() returned incorrect value", 3, wiki.getNumCategoriesEdited("Bender the Bot"));
        assertEquals("getNumCategoriesEdited() returned incorrect value", 3, wiki.getNumCategoriesEdited("Monkbot"));
    }
}
