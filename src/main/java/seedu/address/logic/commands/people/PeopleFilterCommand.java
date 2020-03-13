package seedu.address.logic.commands.people;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliPrefix.PEOPLE_COMMAND_TYPE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.FilterPredicate;

/**
 * Filters and lists all persons in address book who has debts/loans.
 */
public class PeopleFilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons who you owe or lend "
            + "money to.\n"
            + "Parameters: debt/loan\n"
            + "Example: " + PEOPLE_COMMAND_TYPE + " "
            + COMMAND_WORD + " debt";

    private final FilterPredicate predicate;

    public PeopleFilterCommand(FilterPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PeopleFilterCommand // instanceof handles nulls
                && predicate.equals(((PeopleFilterCommand) other).predicate)); // state check
    }
}
