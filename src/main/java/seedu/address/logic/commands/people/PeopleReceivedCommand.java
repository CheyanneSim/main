package seedu.address.logic.commands.people;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliPrefix.PEOPLE_COMMAND_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSACTION_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Debt;
import seedu.address.model.transaction.Loan;
import seedu.address.model.transaction.TransactionList;

/**
 * Records that the user has received the amount money lent to a person,
 * specified by an index in the address book.
 */
public class PeopleReceivedCommand extends Command {

    public static final String COMMAND_WORD = "received";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Records that the person has paid you back.\n"
            + "Parameters: <person's index> (must be a positive integer) [" + PREFIX_TRANSACTION_INDEX
            + "<loans's index> (must be a positive integer)]\n"
            + "Example: " + PEOPLE_COMMAND_TYPE + " "
            + COMMAND_WORD + " 4 i/1";

    public static final String MESSAGE_RECEIVED_SUCCESS = "Removed loan to %1$s by %2$s. %1$s now owes you %3$s.";
    public static final String MESSAGE_NO_LOAN = "%1$s does not owe you money :(";

    private final Index targetPersonIndex;
    private final Index targetLoanIndex;

    public PeopleReceivedCommand(Index targetIndex, Index targetLoanIndex) {
        this.targetPersonIndex = targetIndex;
        this.targetLoanIndex = targetLoanIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetPersonIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personUserReceivedFrom = lastShownList.get(targetPersonIndex.getZeroBased());

        if (personUserReceivedFrom.getLoans().getTotal().isZero()) {
            throw new CommandException(String.format(MESSAGE_NO_LOAN,
                    personUserReceivedFrom.getName()));
        }

        Amount amountPaid;
        Person reducedLoanPerson;

        if (targetLoanIndex == null) {
            reducedLoanPerson = createPersonReceivedAll(personUserReceivedFrom);
            amountPaid = personUserReceivedFrom.getLoans().getTotal();
        } else {
            List<Loan> loans = personUserReceivedFrom.getLoans().asUnmodifiableObservableList();

            if (targetLoanIndex.getZeroBased() >= loans.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_LOAN_DISPLAYED_INDEX);
            }

            Loan loanPaid = loans.get(targetLoanIndex.getZeroBased());
            reducedLoanPerson = createPersonReceivedByIndex(personUserReceivedFrom, loanPaid);
            amountPaid = loanPaid.getAmount();
        }

        model.setPerson(personUserReceivedFrom, reducedLoanPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_RECEIVED_SUCCESS, personUserReceivedFrom.getName(),
                amountPaid, reducedLoanPerson.getLoans().getTotal()));
    }

    /**
     * Creates and returns a {@code Person} after removing all the loans.
     */
    private static Person createPersonReceivedAll(Person personPaid) {
        assert personPaid != null;

        Name name = personPaid.getName();
        Phone phone = personPaid.getPhone();
        Email email = personPaid.getEmail();
        TransactionList<Debt> debts = personPaid.getDebts();
        TransactionList<Loan> clearedLoans = new TransactionList<>();
        Set<Tag> tags = personPaid.getTags();
        return new Person(name, phone, email, debts, clearedLoans, tags);
    }

    /**
     * Creates and returns a {@code Person} after removing the loan specified by an index.
     */
    private static Person createPersonReceivedByIndex(Person personPaid, Loan loanPaid) {
        assert personPaid != null;
        assert loanPaid != null;

        Name name = personPaid.getName();
        Phone phone = personPaid.getPhone();
        Email email = personPaid.getEmail();
        TransactionList<Debt> debts = personPaid.getDebts();
        TransactionList<Loan> updatedLoans = new TransactionList<>();
        updatedLoans.setTransactions(personPaid.getLoans());
        updatedLoans.remove(loanPaid);
        Set<Tag> tags = personPaid.getTags();
        return new Person(name, phone, email, debts, updatedLoans, tags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PeopleReceivedCommand // instanceof handles nulls
                && targetPersonIndex.equals(((PeopleReceivedCommand) other).targetPersonIndex))
                && (targetLoanIndex == ((PeopleReceivedCommand) other).targetLoanIndex
                || targetLoanIndex.equals(((PeopleReceivedCommand) other).targetLoanIndex));
    }
}
