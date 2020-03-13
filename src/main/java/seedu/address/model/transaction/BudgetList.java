package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Month;
import java.time.Year;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.transaction.exceptions.BudgetNotFoundException;

/**
 * A list of Budgets to record individual months' budgets.
 * <p>
 * Supports a limited set of list operations.
 */
public class BudgetList<T extends Budget> implements Iterable<T> {

    final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    private T defaultBudget;

    public Budget getDefaultBudget() {
        return defaultBudget;
    }

    public void setDefaultBudget(T budget) {
        requireNonNull(budget);
        this.defaultBudget = budget;
    }

    /**
     * Returns true if the list contains an equivalent budget as the given argument.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true if the list contains an equivalent budget of the date given.
     */
    public boolean containsBudgetOf(Month month, Year year) {
        requireAllNonNull(month, year);
        Budget tempBudget = new Budget(new Amount(0), month, year);
        return internalList.stream().anyMatch(tempBudget::dateEquals);
    }

    /**
     * Adds a Budget to the list, replacing if there is a Budget that already exists.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Returns a budget of the {@code month} and {@code year}. If the individual budget doesn't exist, the default
     * budget is returned.
     */
    public T get(Month month, Year year) {
        requireAllNonNull(month, year);

        for (T b : internalList) {
            if (b.getMonth().equals(month) && b.getYear().equals(year)) {
                return b;
            }
        }

        return defaultBudget;
    }

    /**
     * Replaces the budget {@code target} in the list with {@code editedBudget}.
     * {@code target} must exist in the list.
     */
    public void setBudget(T target, T editedBudget) {
        requireAllNonNull(target, editedBudget);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BudgetNotFoundException();
        }

        internalList.set(index, editedBudget);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BudgetList<?> // instanceof handles nulls
                && internalList.equals(((BudgetList<?>) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
