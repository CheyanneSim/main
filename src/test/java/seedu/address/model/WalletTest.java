package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalWallet.BUDGET_JAN_2010;
import static seedu.address.testutil.TypicalWallet.DUCK_RICE;
import static seedu.address.testutil.TypicalWallet.MRT_CONCESSION;
import static seedu.address.testutil.TypicalWallet.TA_JOB;
import static seedu.address.testutil.TypicalWallet.VALID_MONTH_DUCK;
import static seedu.address.testutil.TypicalWallet.VALID_YEAR_DUCK;
import static seedu.address.testutil.TypicalWallet.getTypicalWallet;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.transaction.Budget;
import seedu.address.testutil.BudgetBuilder;


public class WalletTest {

    private final Wallet wallet = new Wallet();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), wallet.getTransactionList());
        assertEquals(Collections.emptyList(), wallet.getIncomeList());
        assertEquals(Collections.emptyList(), wallet.getExpenseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wallet.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWallet_replacesData() {
        Wallet newData = getTypicalWallet();
        wallet.resetData(newData);
        assertEquals(newData, wallet);
    }

    @Test
    public void hasExpense_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wallet.hasExpense(null));
    }

    @Test
    public void hasExpense_expenseNotInWallet_returnsFalse() {
        assertFalse(wallet.hasExpense(DUCK_RICE));
    }

    @Test
    public void hasExpense_expenseInWallet_returnsTrue() {
        wallet.addExpense(DUCK_RICE);
        assertTrue(wallet.hasExpense(DUCK_RICE));
    }

    @Test
    public void hasIncome_nullIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wallet.hasIncome(null));
    }

    @Test
    public void hasIncome_incomeNotInWallet_returnsFalse() {
        assertFalse(wallet.hasIncome(TA_JOB));
    }

    @Test
    public void hasIncome_incomeInWallet_returnsTrue() {
        wallet.addIncome(TA_JOB);
        assertTrue(wallet.hasIncome(TA_JOB));
    }

    @Test
    public void setBudget_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wallet.setBudget(null));
    }

    @Test
    public void setDefaultBudget_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wallet.setDefaultBudget(null));
    }

    @Test
    public void hasExceededBudget_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wallet.hasExceededBudget(null, null));
    }

    @Test
    public void getBudget_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wallet.getBudget(null, null));
    }

    @Test
    public void hasExceededBudget_typicalWallet_returnsTrue() {
        // only default budget, budget not exceeded -> returns true
        Wallet typicalWalletOnlyDefaultBudget = new Wallet();

        typicalWalletOnlyDefaultBudget.addExpense(DUCK_RICE); // 3.50
        typicalWalletOnlyDefaultBudget.addExpense(MRT_CONCESSION); // 45

        Budget defaultBudget = new BudgetBuilder().withAmount("35").setAsDefault().buildBudget();
        typicalWalletOnlyDefaultBudget.setDefaultBudget(defaultBudget);

        assertTrue(typicalWalletOnlyDefaultBudget.hasExceededBudget(DUCK_RICE.getDate().getMonth(),
                DUCK_RICE.getDate().getYear()));

        // only budget for the selected month, budget not exceeded -> returns true
        Wallet typicalWalletOnlySelectedMonthBudget = new Wallet();
        typicalWalletOnlySelectedMonthBudget.addExpense(DUCK_RICE);
        typicalWalletOnlySelectedMonthBudget.addExpense(MRT_CONCESSION); // both are in Jan 2010
        typicalWalletOnlySelectedMonthBudget.setBudget(BUDGET_JAN_2010);

        assertTrue(typicalWalletOnlySelectedMonthBudget.hasExceededBudget(DUCK_RICE.getDate().getMonth(),
                DUCK_RICE.getDate().getYear()));

        // both budgets (default and selected month), budget not exceeded -> returns true
        Wallet typicalWalletWithBothBudgets = new Wallet();
        Budget defaultBudgetWillNotExceed = new BudgetBuilder().withAmount("1000").setAsDefault().buildBudget();
        Budget monthBudget =
                new BudgetBuilder()
                        .withAmount("35")
                        .withMonth(VALID_MONTH_DUCK)
                        .withYear(VALID_YEAR_DUCK)
                        .buildBudget();

        typicalWalletWithBothBudgets.addExpense(DUCK_RICE);
        typicalWalletWithBothBudgets.addExpense(MRT_CONCESSION);
        typicalWalletWithBothBudgets.setDefaultBudget(defaultBudgetWillNotExceed);
        typicalWalletWithBothBudgets.setBudget(monthBudget);

        assertTrue(typicalWalletWithBothBudgets.hasExceededBudget(DUCK_RICE.getDate().getMonth(),
                DUCK_RICE.getDate().getYear()));
    }

    @Test
    public void hasExceededBudget_typicalWallet_returnsFalse() {
        // only default budget, not exceeded -> returns false
        Wallet typicalWalletOnlyDefaultBudget = new Wallet();

        typicalWalletOnlyDefaultBudget.addExpense(DUCK_RICE); // 3.50
        typicalWalletOnlyDefaultBudget.addExpense(MRT_CONCESSION); // 45

        Budget defaultBudget = new BudgetBuilder().withAmount("100").setAsDefault().buildBudget();
        typicalWalletOnlyDefaultBudget.setDefaultBudget(defaultBudget);

        assertFalse(typicalWalletOnlyDefaultBudget.hasExceededBudget(DUCK_RICE.getDate().getMonth(),
                DUCK_RICE.getDate().getYear()));

        // only budget for the selected month, budget not exceeded -> returns false
        Wallet typicalWalletOnlySelectedMonthBudget = new Wallet();
        Budget selectedMonthBudget = new BudgetBuilder()
                .withAmount("100")
                .withMonth(VALID_MONTH_DUCK)
                .withYear(VALID_YEAR_DUCK)
                .buildBudget();

        typicalWalletOnlySelectedMonthBudget.addExpense(DUCK_RICE);
        typicalWalletOnlySelectedMonthBudget.addExpense(MRT_CONCESSION); // both are in Jan 2010
        typicalWalletOnlySelectedMonthBudget.setBudget(selectedMonthBudget);

        assertFalse(typicalWalletOnlySelectedMonthBudget.hasExceededBudget(DUCK_RICE.getDate().getMonth(),
                DUCK_RICE.getDate().getYear()));

        // both budgets (default and selected month), budget not exceeded -> returns false
        Wallet typicalWalletWithBothBudgets = new Wallet();
        Budget defaultBudgetWillNotExceed = new BudgetBuilder().withAmount("35").setAsDefault().buildBudget();
        Budget monthBudget = new BudgetBuilder()
                .withAmount("1000")
                .withMonth(VALID_MONTH_DUCK)
                .withYear(VALID_YEAR_DUCK)
                .buildBudget();

        typicalWalletWithBothBudgets.addExpense(DUCK_RICE);
        typicalWalletWithBothBudgets.addExpense(MRT_CONCESSION);
        typicalWalletWithBothBudgets.setDefaultBudget(defaultBudgetWillNotExceed);
        typicalWalletWithBothBudgets.setBudget(monthBudget);

        assertFalse(typicalWalletWithBothBudgets.hasExceededBudget(DUCK_RICE.getDate().getMonth(),
                DUCK_RICE.getDate().getYear()));
    }

    @Test
    public void getIncomeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> wallet.getIncomeList().remove(0));
    }

    @Test
    public void getExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> wallet.getExpenseList().remove(0));
    }

    @Test
    public void getTransactionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> wallet.getTransactionList().remove(0));
    }

}
