package seedu.address.logic.parser.people;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.people.PeopleFilterCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FilterPredicate;

/**
 * Parses input arguments and creates a new PeopleFilterCommand object
 */
public class PeopleFilterCommandParser implements Parser<PeopleFilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PeopleFilterCommand
     * and returns a PeopleFilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PeopleFilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || !trimmedArgs.equals("debt") && !trimmedArgs.equals("loan")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PeopleFilterCommand.MESSAGE_USAGE));
        }
        return new PeopleFilterCommand(new FilterPredicate(trimmedArgs));
    }
}
