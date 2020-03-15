package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.Month;
import java.time.Year;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Budget;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Expense;
import seedu.address.model.transaction.Income;
import seedu.address.model.transaction.Transaction;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserData userData;
    private final AddressBook addressBook;
    private final Wallet wallet;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    //wallet
    private final FilteredList<Expense> filteredExpenses;
    private final FilteredList<Income> filteredIncomes;
    private FilteredList<Transaction> filteredTransactions;

    /**
     * Initializes a ModelManager with the given addressBook, wallet and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyWallet wallet,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, wallet, userPrefs);


        logger.fine("Initializing with address book: " + addressBook + ", wallet: " + wallet + " and user "
                + "prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.wallet = new Wallet(wallet);
        this.userPrefs = new UserPrefs(userPrefs);
        this.userData = new UserData();
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());

        //wallet
        filteredExpenses = new FilteredList<Expense>(this.wallet.getExpenseList());
        filteredIncomes = new FilteredList<Income>(this.wallet.getIncomeList());
        filteredTransactions = new FilteredList<Transaction>(this.wallet.getTransactionList());

    }

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        this(addressBook, new Wallet(), userPrefs);
    }

    public ModelManager() {
        this(new AddressBook(), new Wallet(), new UserPrefs());
    }

    // =========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getUserDataFilePath() {
        return userPrefs.getUserDataFilePath();
    }

    @Override
    public void setUserDataFilePath(Path userDataFilePath) {
        requireNonNull(userDataFilePath);
        userPrefs.setUserDataFilePath(userDataFilePath);
    }

    // =========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    // =========== User Data ================================================================================

    @Override
    public void setUserData(ReadOnlyUserData userData) {
        this.userData.resetData(userData);
    }

    @Override
    public ReadOnlyUserData getUserData() {
        return userData;
    }

    @Override
    public boolean isUserDataNull() {
        return this.userData.isEmpty();
    }

    // =========== Wallet =====================================================================================

    @Override
    public ReadOnlyWallet getWallet() {
        return wallet;
    }

    //Income
    @Override
    public boolean hasIncome(Income income) {
        return wallet.hasIncome(income);
    }

    @Override
    public void addIncome(Income income) {
        wallet.addIncome(income);
        filteredTransactions = new FilteredList<Transaction>(this.wallet.getTransactionList());
    }

    @Override
    public void deleteIncome(Income target) {
        wallet.deleteIncome(target);
    }

    @Override
    public void setIncome(Income target, Income editedIncome) {
        requireAllNonNull(target, editedIncome);
        wallet.setIncome(target, editedIncome);
        filteredTransactions = new FilteredList<Transaction>(this.wallet.getTransactionList());
    }

    //Expense
    @Override
    public boolean hasExpense(Expense expense) {
        return wallet.hasExpense(expense);
    }

    @Override
    public void addExpense(Expense expense) {
        wallet.addExpense(expense);
        filteredTransactions = new FilteredList<Transaction>(this.wallet.getTransactionList());
    }

    @Override
    public void deleteExpense(Expense target) {
        wallet.deleteExpense(target);
    }

    @Override
    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);
        wallet.setExpense(target, editedExpense);
        filteredTransactions = new FilteredList<Transaction>(this.wallet.getTransactionList());
    }

    //Transactions

    @Override
    public void setTransaction(Transaction transactionToEdit, Transaction editedTransaction) {
        if (transactionToEdit instanceof Expense) {
            setExpense((Expense) transactionToEdit, (Expense) editedTransaction);
        } else {
            assert transactionToEdit instanceof Income
                    : "transactionToEdit should be either an Expense class or Income class";
            setIncome((Income) transactionToEdit, (Income) editedTransaction);
        }

        //update transaction list
        filteredTransactions = new FilteredList<Transaction>(this.wallet.getTransactionList());
    }

    @Override
    public Amount getTotalExpenditureInMonth(Date date) {
        return wallet.getTotalExpenditureInMonth(date);
    }

    @Override
    public void setBudget(Budget budget) {
        requireNonNull(budget);
        wallet.setBudget(budget);
    }

    @Override
    public void setDefaultBudget(Budget budget) {
        requireNonNull(budget);
        wallet.setDefaultBudget(budget);
    }

    @Override
    public boolean hasExceededBudget(Month month, Year year) {
        return wallet.hasExceededBudget(month, year);
    }

    @Override
    public Budget getBudget(Month month, Year year) {
        return wallet.getBudget(month, year);
    }

    // =========== Util Methods ===============================================================================

    /**
     * Deletes {@code transactionToDelete} from the transaction list.
     */
    public void deleteTransaction(Transaction transactionToDelete) {
        if (transactionToDelete instanceof Expense) {
            deleteExpense((Expense) transactionToDelete);
        } else {
            assert transactionToDelete instanceof Income
                    : "transactionToDelete should be either an Expense class or Income class";
            deleteIncome((Income) transactionToDelete);
        }

        //update transaction list
        filteredTransactions = new FilteredList<Transaction>(this.wallet.getTransactionList());
    }

    // =========== Util Methods Person ========================================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the
     * internal list of {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook) && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons) && wallet.equals(other.wallet);
    }

    // =========== Util Methods Wallet ========================================================================

    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        return filteredExpenses;
    }

    @Override
    public ObservableList<Income> getFilteredIncomeList() {
        return filteredIncomes;
    }

    @Override
    public ObservableList<Transaction> getFilteredTransactionList() {
        return filteredTransactions;
    }

    @Override
    public void updateFilteredTransactionList(Predicate<Transaction> predicate) {
        requireNonNull(predicate);
        filteredTransactions.setPredicate(predicate);
    }
}
