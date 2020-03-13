package seedu.address.logic.commands.wallet;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalWallet.DUCK_RICE;
import static seedu.address.testutil.TypicalWallet.MRT_CONCESSION;
import static seedu.address.testutil.TypicalWallet.VALID_AMOUNT_DUCK;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Budget;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Expense;
import seedu.address.testutil.ModelStub;

public class WalletExpenseCommandTest {

    @Test
    public void constructor_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new WalletExpenseCommand(null));
    }

    @Test
    public void execute_expenseAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();

        CommandResult commandResult = new WalletExpenseCommand(DUCK_RICE).execute(modelStub);

        assertEquals(String.format(WalletExpenseCommand.MESSAGE_SUCCESS,
                DUCK_RICE,
                DUCK_RICE.getDate().getMonth() + " " + DUCK_RICE.getDate().getYear(),
                "$" + VALID_AMOUNT_DUCK,
                "$0.00"),
                commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(DUCK_RICE), modelStub.expensesAdded);
    }

    @Test
    public void equals() {
        WalletExpenseCommand duckCommand = new WalletExpenseCommand(DUCK_RICE);
        WalletExpenseCommand mrtCommand = new WalletExpenseCommand(MRT_CONCESSION);

        // same object -> returns true
        assertEquals(duckCommand, duckCommand);

        // same values -> returns true
        WalletExpenseCommand duckCommandCopy = new WalletExpenseCommand(DUCK_RICE);
        assertTrue(duckCommand.equals(duckCommandCopy));

        // different types -> returns false
        assertFalse(duckCommand.equals(1));

        // null -> returns false
        assertFalse(duckCommand.equals(null));

        // different expense -> returns false
        assertFalse(duckCommand.equals(mrtCommand));
    }

    /**
     * A Model stub that always accept the expense being added.
     */
    private class ModelStubAcceptingExpenseAdded extends ModelStub {
        final ArrayList<Expense> expensesAdded = new ArrayList<>();

        @Override
        public void addExpense(Expense expense) {
            requireNonNull(expense);
            expensesAdded.add(expense);
        }

        @Override
        public Amount getTotalExpenditureInMonth(Date date) {
            double amount = 0.0;
            for (Expense e : expensesAdded) {
                amount += e.getAmount().amount;
            }
            return new Amount(amount);
        }

        @Override
        public Budget getBudget(Month month, Year year) {
            return Budget.getDefault();
        }
    }

}
