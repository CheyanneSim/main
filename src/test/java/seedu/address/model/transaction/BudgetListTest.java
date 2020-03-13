package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalWallet.BUDGET_APRIL_2020;
import static seedu.address.testutil.TypicalWallet.BUDGET_JAN_2010;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.transaction.exceptions.BudgetNotFoundException;
import seedu.address.testutil.BudgetBuilder;

public class BudgetListTest {

    private BudgetList<Budget> budgetList = new BudgetList<>();

    @Test
    public void getBudget_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.get(null, null));
    }

    @Test
    public void getBudget_doesNotHaveBudget_returnsDefaultBudget() {
        BudgetList<Budget> noDefaultBudgetList = new BudgetList<>();
        Budget defaultBudget = noDefaultBudgetList.getDefaultBudget();
        assertEquals(defaultBudget, noDefaultBudgetList.get(BUDGET_JAN_2010.getMonth(), BUDGET_JAN_2010.getYear()));
    }

    @Test
    public void getBudget_hasBudget_returnsBudget() {
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        tempBudgetList.add(BUDGET_JAN_2010);
        assertEquals(BUDGET_JAN_2010, tempBudgetList.get(BUDGET_JAN_2010.getMonth(), BUDGET_JAN_2010.getYear()));
    }

    @Test
    public void getBudget_hasMultipleBudgets_returnsCorrectBudget() {
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        tempBudgetList.add(BUDGET_JAN_2010);
        tempBudgetList.add(BUDGET_APRIL_2020);
        assertEquals(BUDGET_JAN_2010, tempBudgetList.get(BUDGET_JAN_2010.getMonth(), BUDGET_JAN_2010.getYear()));
    }

    @Test
    public void getBudget_hasMultipleBudgets_returnsDefaultBudget() {
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        tempBudgetList.add(BUDGET_JAN_2010);
        tempBudgetList.add(BUDGET_APRIL_2020);

        Budget nonExistentInListBudget =
                new BudgetBuilder().withAmount("3000").withMonth("09").withYear("2069").buildBudget();
        Budget defaultBudget = tempBudgetList.getDefaultBudget();
        assertEquals(defaultBudget, tempBudgetList.get(nonExistentInListBudget.getMonth(),
                nonExistentInListBudget.getYear()));
    }

    @Test
    public void setDefaultBudget_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.setDefaultBudget(null));
    }

    @Test
    public void containsBudget_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.contains(null));
    }

    @Test
    public void containsBudget_existingBudget_returnsTrue() {
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        tempBudgetList.add(BUDGET_JAN_2010);
        tempBudgetList.add(BUDGET_APRIL_2020);

        assertTrue(tempBudgetList.contains(BUDGET_JAN_2010));
        assertTrue(tempBudgetList.contains(BUDGET_APRIL_2020));
    }

    @Test
    public void containsBudget_nonExistentBudget_returnsFalse() {
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        Budget nonExistentBudget =
                new BudgetBuilder().withAmount("3000").withMonth("09").withYear("2069").buildBudget();

        tempBudgetList.add(BUDGET_JAN_2010);
        tempBudgetList.add(BUDGET_APRIL_2020);

        assertFalse(tempBudgetList.contains(nonExistentBudget));
    }

    @Test
    public void setBudget_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.setBudget(null, null));
        assertThrows(NullPointerException.class, () -> budgetList.setBudget(BUDGET_APRIL_2020, null));
        assertThrows(NullPointerException.class, () -> budgetList.setBudget(null, BUDGET_APRIL_2020));
    }

    @Test
    public void setBudget_budgetNotInList_throwsBudgetNotFoundException() {
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        assertThrows(BudgetNotFoundException.class, () -> tempBudgetList.setBudget(BUDGET_JAN_2010, BUDGET_APRIL_2020));
    }

    @Test
    public void setBudget_budgetInList_success() {
        BudgetList<Budget> expectedBudgetList = new BudgetList<>();
        expectedBudgetList.add(BUDGET_APRIL_2020);

        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        tempBudgetList.add(BUDGET_JAN_2010);
        tempBudgetList.setBudget(BUDGET_JAN_2010, BUDGET_APRIL_2020);
        assertEquals(expectedBudgetList, tempBudgetList);
    }

    @Test
    public void addBudget_normalBudget_budgetAddedToList() {
        List<Budget> expectedBudgetList = List.of(BUDGET_JAN_2010);
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        tempBudgetList.add(BUDGET_JAN_2010);

        assertEquals(expectedBudgetList, tempBudgetList.internalList);
    }

    @Test
    public void containsBudgetOf_nullArguments_throwsNullPointerExcetption() {
        Budget tempBudget = Budget.getDefault();

        assertThrows(NullPointerException.class, () -> budgetList.containsBudgetOf(null, null));
        assertThrows(NullPointerException.class, () -> budgetList.containsBudgetOf(tempBudget.getMonth(), null));
        assertThrows(NullPointerException.class, () -> budgetList.containsBudgetOf(null, tempBudget.getYear()));
    }

    @Test
    public void containsBudgetOf_budgetNotInList_returnsFalse() {
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        tempBudgetList.add(BUDGET_APRIL_2020);

        assertFalse(tempBudgetList.containsBudgetOf(BUDGET_JAN_2010.getMonth(), BUDGET_JAN_2010.getYear()));
    }

    @Test
    public void containsBudgetOf_budgetInList_returnsTrue() {
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        tempBudgetList.add(BUDGET_JAN_2010);
        tempBudgetList.add(BUDGET_APRIL_2020);

        assertTrue(tempBudgetList.containsBudgetOf(BUDGET_JAN_2010.getMonth(), BUDGET_JAN_2010.getYear()));
        assertTrue(tempBudgetList.containsBudgetOf(BUDGET_APRIL_2020.getMonth(), BUDGET_APRIL_2020.getYear()));
    }

    @Test
    public void equals_sameLists_returnsTrue() {
        BudgetList<Budget> tempBudgetListA = new BudgetList<>();
        tempBudgetListA.add(BUDGET_JAN_2010);

        BudgetList<Budget> tempBudgetListB = new BudgetList<>();
        tempBudgetListB.add(BUDGET_JAN_2010);

        assertEquals(tempBudgetListA, tempBudgetListB);
    }

    @Test
    public void equals_differentLists_returnsFalse() {
        BudgetList<Budget> tempBudgetListA = new BudgetList<>();
        tempBudgetListA.add(BUDGET_JAN_2010);

        BudgetList<Budget> tempBudgetListB = new BudgetList<>();
        tempBudgetListA.add(BUDGET_JAN_2010);
        tempBudgetListB.add(BUDGET_APRIL_2020);

        assertNotEquals(tempBudgetListA, tempBudgetListB);
    }
}
