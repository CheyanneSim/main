package seedu.address.logic.commands.wallet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalWallet.BUDGET_APRIL_2020;
import static seedu.address.testutil.TypicalWallet.BUDGET_JAN_2010;
import static seedu.address.testutil.TypicalWallet.DEFAULT_BUDGET;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.transaction.Budget;
import seedu.address.testutil.ModelStub;

public class WalletBudgetCommandTest {

    @Test
    public void constructor_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new WalletBudgetCommand(null));
    }

    @Test
    public void execute_defaultBudgetAcceptedByModel_successful() throws Exception {
        ModelStubAcceptingBudgetSet modelStub = new ModelStubAcceptingBudgetSet();

        CommandResult commandResult = new WalletBudgetCommand(DEFAULT_BUDGET).execute(modelStub);
        assertEquals(String.format(WalletBudgetCommand.MESSAGE_SUCCESS_DEFAULT, DEFAULT_BUDGET.getAmount()),
                commandResult.getFeedbackToUser());
        assertEquals(DEFAULT_BUDGET, modelStub.defaultBudget);
    }

    @Test
    public void execute_budgetAcceptedByModel_setSuccessful() throws Exception {
        ModelStubAcceptingBudgetSet modelStub = new ModelStubAcceptingBudgetSet();

        CommandResult commandResult = new WalletBudgetCommand(BUDGET_JAN_2010).execute(modelStub);
        assertEquals(String.format(WalletBudgetCommand.MESSAGE_SUCCESS, BUDGET_JAN_2010.getAmount(),
                BUDGET_JAN_2010.getMonth(), BUDGET_JAN_2010.getYear()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        WalletBudgetCommand defaultBudgetCommand = new WalletBudgetCommand(DEFAULT_BUDGET);
        WalletBudgetCommand typicalBudgetCommand = new WalletBudgetCommand(BUDGET_JAN_2010);

        // same object -> returns true
        assertTrue(defaultBudgetCommand.equals(defaultBudgetCommand));
        assertTrue(typicalBudgetCommand.equals(typicalBudgetCommand));

        // same values -> returns true
        WalletBudgetCommand defaultBudgetCommandCopy = new WalletBudgetCommand(DEFAULT_BUDGET);
        assertTrue(defaultBudgetCommand.equals(defaultBudgetCommandCopy));
        WalletBudgetCommand typicalBudgetCommandCopy = new WalletBudgetCommand(BUDGET_JAN_2010);
        assertTrue(typicalBudgetCommand.equals(typicalBudgetCommandCopy));

        // different types -> returns false
        assertFalse(defaultBudgetCommand.equals(1));
        assertFalse(typicalBudgetCommand.equals(1));

        // null -> returns false
        assertFalse(defaultBudgetCommand.equals(null));
        assertFalse(typicalBudgetCommand.equals(null));

        // different budgets -> returns false
        WalletBudgetCommand aprilBudgetCommand = new WalletBudgetCommand(BUDGET_APRIL_2020);
        assertFalse(defaultBudgetCommand.equals(aprilBudgetCommand));
        assertFalse(typicalBudgetCommand.equals(aprilBudgetCommand));
        assertFalse(defaultBudgetCommand.equals(typicalBudgetCommand));
    }

    private class ModelStubAcceptingBudgetSet extends ModelStub {
        final List<Budget> budgetList = new ArrayList<Budget>();
        private Budget defaultBudget = Budget.getDefault();

        @Override
        public void setBudget(Budget budget) {
            budgetList.add(budget);
        }

        @Override
        public void setDefaultBudget(Budget budget) {
            defaultBudget = budget;
        }
    }

}
