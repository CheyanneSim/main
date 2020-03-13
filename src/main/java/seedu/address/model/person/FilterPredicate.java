package seedu.address.model.person;

import java.util.function.Function;
import java.util.function.Predicate;

import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionList;

/**
 * Tests whether a {@code Person} has any debts or loans.
 */
public class FilterPredicate implements Predicate<Person> {

    private final Function<Person, TransactionList<? extends Transaction>> function;

    public FilterPredicate(Function<Person, TransactionList<? extends Transaction>> function) {
        this.function = function;
    }

    @Override
    public boolean test(Person person) {
        return !function.apply(person).asUnmodifiableObservableList().isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPredicate // instanceof handles nulls
                && function.equals(((FilterPredicate) other).function)); // state check
    }
}
