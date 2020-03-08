package seedu.address.logic.parser.wallet;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.wallet.WalletIncomeCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Income;

/**
 * Parses input arguments and creates a new WalletIncomeCommand object
 */
public class WalletIncomeCommandParser implements Parser<WalletIncomeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * WalletIncomeCommand and returns a WalletIncomeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public WalletIncomeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_AMOUNT, PREFIX_DATE,
                PREFIX_TAG);

        if (!argMultimap.getValue(PREFIX_NAME).isPresent() || !argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            System.out.println(args);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, WalletIncomeCommand.MESSAGE_USAGE));
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_NAME).get());
        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());

        Date date;
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        } else {
            date = Date.getDefault();
        }

        Tag tag;
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
        } else {
            tag = new Tag("Misc");
        }

        Income income = new Income(description, amount, date, tag);

        return new WalletIncomeCommand(income);
    }
}
