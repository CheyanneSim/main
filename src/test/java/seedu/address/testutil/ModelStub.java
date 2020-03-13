package seedu.address.testutil;

import java.nio.file.Path;
import java.time.Month;
import java.time.Year;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserData;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyWallet;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Budget;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Expense;
import seedu.address.model.transaction.Income;
import seedu.address.model.transaction.Transaction;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getUserDataFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setUserDataFilePath(Path userDataFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError(
                "This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError(
                "This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError(
                "This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyWallet getWallet() {
        throw new AssertionError("This method should not be called.");
    }

    public void setUserData(ReadOnlyUserData userData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserData getUserData() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isUserDataNull() {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public boolean hasIncome(Income income) {
        throw new AssertionError(
                "This method should not be called.");
    }

    @Override
    public void addIncome(Income income) {
        throw new AssertionError(
                "This method should not be called.");
    }

    @Override
    public void deleteIncome(Income target) {
        throw new AssertionError(
                "This method should not be called.");
    }

    @Override
    public void setIncome(Income target, Income editedIncome) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasExpense(Expense expense) {
        throw new AssertionError(
                "This method should not be called.");
    }

    @Override
    public void addExpense(Expense expense) {
        throw new AssertionError(
                "This method should not be called.");
    }

    @Override
    public void deleteExpense(Expense target) {
        throw new AssertionError(
                "This method should not be called.");
    }

    @Override
    public void setExpense(Expense target, Expense editedExpense) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Amount getTotalExpenditureInMonth(Date date) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setBudget(Budget budget) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTransaction(Transaction target) {
        throw new AssertionError(
                "This method should not be called.");
    }


    @Override
    public ObservableList<Transaction> getFilteredTransactionList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setDefaultBudget(Budget budget) {
        throw new AssertionError("This method should not be called.");
    }

    public void updateFilteredTransactionList(Predicate<Transaction> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasExceededBudget(Month month, Year year) {
        throw new AssertionError("This method should not be called.");

    }

    public ObservableList<Expense> getFilteredExpenseList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Budget getBudget(Month month, Year year) {
        throw new AssertionError("This method should not be called.");
    }

    public ObservableList<Income> getFilteredIncomeList() {
        throw new AssertionError("This method should not be called.");
    }
}
