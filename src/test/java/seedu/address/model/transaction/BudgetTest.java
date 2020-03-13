package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalWallet.BUDGET_APRIL_2020;
import static seedu.address.testutil.TypicalWallet.BUDGET_JAN_2010;
import static seedu.address.testutil.TypicalWallet.VALID_BUDGET_AMOUNT_JAN_2010;
import static seedu.address.testutil.TypicalWallet.VALID_BUDGET_MONTH_JAN_2010;
import static seedu.address.testutil.TypicalWallet.VALID_BUDGET_YEAR_JAN_2010;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BudgetBuilder;

public class BudgetTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Budget(null, null, null));
    }

    @Test
    public void equals_sameObjects_returnsTrue() {
        Budget budget =
                new BudgetBuilder().withAmount(VALID_BUDGET_AMOUNT_JAN_2010)
                        .withMonth(VALID_BUDGET_MONTH_JAN_2010)
                        .withYear(VALID_BUDGET_YEAR_JAN_2010).buildBudget();

        assertEquals(budget, BUDGET_JAN_2010);
    }

    @Test
    public void equals_differentObjects_returnsFalse() {
        assertNotEquals(BUDGET_JAN_2010, BUDGET_APRIL_2020);
    }

    @Test
    public void dateEquals_sameDate_returnsTrue() {
        Budget budgetA = new BudgetBuilder().withAmount("3000").withMonth("01").withYear("2020").buildBudget();
        Budget budgetB = new BudgetBuilder().withAmount("6969").withMonth("01").withYear("2020").buildBudget();

        assertTrue(budgetA.dateEquals(budgetB));
    }

    @Test
    public void dateEquals_differentDate_returnsFalse() {
        Budget budgetA = new BudgetBuilder().withAmount("3000").withMonth("02").withYear("2020").buildBudget();
        Budget budgetB = new BudgetBuilder().withAmount("6969").withMonth("01").withYear("2021").buildBudget();

        assertFalse(budgetA.dateEquals(budgetB));
    }
}
