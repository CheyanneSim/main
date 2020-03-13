package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Budget;
import seedu.address.model.transaction.BudgetList;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Expense;
import seedu.address.model.transaction.Income;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionList;


/**
 * Wraps all data at the wallet level
 */
public class Wallet implements ReadOnlyWallet {

    private final TransactionList<Income> incomes = new TransactionList<>();
    private final TransactionList<Expense> expenses = new TransactionList<>();
    private BudgetList<Budget> budgetList = new BudgetList<>();

    public Wallet() {
        setDefaultBudget(Budget.getDefault());
    }

    /**
     * Creates an Wallet using the Transactions in the {@code toBeCopied}
     */
    public Wallet(ReadOnlyWallet toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // =========== List Overwrite Operations =============================================================

    /**
     * Replaces the contents of the income list with {@code incomes}.
     */
    public void setIncomes(List<Income> incomes) {
        this.incomes.setTransactions(incomes);
    }

    /**
     * Replaces the contents of the expense list with {@code expenses}.
     */
    public void setExpenses(List<Expense> expenses) {
        this.expenses.setTransactions(expenses);
    }

    /**
     * Resets the existing data of this {@code Wallet} with {@code newData}.
     */
    public void resetData(ReadOnlyWallet newData) {
        requireNonNull(newData);
        setIncomes(newData.getIncomeList());
        setExpenses(newData.getExpenseList());
    }

    // =========== Income-related Operations =============================================================

    /**
     * Returns true if an identical income exists in the Wallet
     */
    public boolean hasIncome(Income income) {
        requireNonNull(income);
        return incomes.contains(income);
    }

    /**
     * Adds an income to the Wallet.
     */
    public void addIncome(Income income) {
        incomes.add(income);
    }

    /**
     * Replaces the given income {@code target} in the list with {@code editedIncome}.
     * {@code target} must exist in the Wallet
     */
    public void setIncome(Income target, Income editedIncome) {
        requireNonNull(editedIncome);
        incomes.setTransaction(target, editedIncome);
    }

    /**
     * Removes {@code key} from this {@code Wallet}.
     * {@code key} must exist in the wallet.
     */
    public void deleteIncome(Income key) {
        incomes.remove(key);
    }

    // =========== Expense-related Operations =============================================================

    /**
     * Returns true if an identical expense exists in the Wallet
     */
    public boolean hasExpense(Expense expense) {
        requireNonNull(expense);
        return expenses.contains(expense);
    }

    /**
     * Adds an expense to the Wallet.
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    /**
     * Replaces the given expense {@code target} in the list with {@code editedExpense}.
     * {@code target} must exist in the Wallet
     */
    public void setExpense(Expense target, Expense editedExpense) {
        requireNonNull(editedExpense);
        expenses.setTransaction(target, editedExpense);
    }

    /**
     * Removes {@code key} from this {@code Wallet}.
     * {@code key} must exist in the wallet.
     */
    public void deleteExpense(Expense key) {
        expenses.remove(key);
    }

    public Amount getTotalExpenditureInMonth(Date date) {
        requireNonNull(date);
        TransactionList<Expense> filteredExpenseList = expenses.getTransactionsInMonth(date.getMonth(), date.getYear());

        return filteredExpenseList.getTotal();
    }

    // =========== Budget-related Operations =============================================================

    /**
     * Replaces the budget in the Wallet with {@code budget}. If a budget was previously set, it overrides that
     * budget and sets this new value.
     */
    public void setBudget(Budget budget) {
        requireNonNull(budget);

        if (budgetList.containsBudgetOf(budget.getMonth(), budget.getYear())) {
            Budget existingBudget = budgetList.get(budget.getMonth(), budget.getYear());
            budgetList.setBudget(existingBudget, budget);
        } else {
            budgetList.add(budget);
        }
    }

    /**
     * Replaces the default budget in the Wallet with {@code budget}.
     */
    public void setDefaultBudget(Budget budget) {
        requireNonNull(budget);
        budgetList.setDefaultBudget(budget);
    }

    /**
     * Checks if the budget has been exceeded in the month and year selected.
     */
    public boolean hasExceededBudget(Month month, Year year) {
        requireAllNonNull(month, year);
        TransactionList<Expense> filteredExpenseList = expenses.getTransactionsInMonth(month, year);

        Budget budgetToCompare;
        if (budgetList.containsBudgetOf(month, year)) {
            budgetToCompare = budgetList.get(month, year);
        } else {
            budgetToCompare = budgetList.getDefaultBudget();
        }

        return filteredExpenseList.getTotal().amount > budgetToCompare.getAmount().amount;
    }

    public Budget getBudget(Month month, Year year) {
        return budgetList.get(month, year);
    }

    // =========== Util methods =============================================================

    @Override
    public ObservableList<Transaction> getTransactionList() {
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        transactions.addAll(getIncomeList());
        transactions.addAll(getExpenseList());
        return FXCollections.unmodifiableObservableList(transactions);
    }

    @Override
    public ObservableList<Income> getIncomeList() {
        return incomes.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Expense> getExpenseList() {
        return expenses.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Wallet // instanceof handles nulls
                && incomes.equals(((Wallet) other).incomes)
                && expenses.equals(((Wallet) other).expenses));
    }

    @Override
    public int hashCode() {
        return Objects.hash(incomes, expenses);
    }

}
