package seedu.address.logic.parser.wallet;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MONTH_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_YEAR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MONTH_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.wallet.WalletBudgetCommand;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Budget;
import seedu.address.model.transaction.Date;
import seedu.address.testutil.BudgetBuilder;

public class WalletBudgetCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, WalletBudgetCommand.MESSAGE_USAGE);
    private WalletBudgetCommandParser parser = new WalletBudgetCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no arguments
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no amount
        assertParseFailure(parser, PREFIX_DATE + VALID_DATE_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // some invalid test strings
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidArguments_failure() {
        // invalid amount only
        assertParseFailure(parser, INVALID_AMOUNT_DESC, Amount.MESSAGE_CONSTRAINTS);

        // invalid amount, invalid month, invalid year
        assertParseFailure(parser, INVALID_AMOUNT_DESC + INVALID_MONTH_DESC + INVALID_YEAR_DESC,
                Amount.MESSAGE_CONSTRAINTS);

        // invalid amount, invalid month, valid year
        assertParseFailure(parser, INVALID_AMOUNT_DESC + INVALID_MONTH_DESC + VALID_YEAR_DESC,
                Amount.MESSAGE_CONSTRAINTS);

        // invalid amount, valid month, invalid year
        assertParseFailure(parser, INVALID_AMOUNT_DESC + VALID_MONTH_DESC + INVALID_YEAR_DESC,
                Amount.MESSAGE_CONSTRAINTS);

        // invalid amount, valid month, valid year
        assertParseFailure(parser, INVALID_AMOUNT_DESC + VALID_MONTH_DESC + VALID_YEAR_DESC,
                Amount.MESSAGE_CONSTRAINTS);

        // valid amount, invalid month, invalid year
        assertParseFailure(parser, VALID_AMOUNT_DESC + INVALID_MONTH_DESC + INVALID_YEAR_DESC,
                Date.MONTH_MESSAGE_CONSTRAINTS);

        // valid amount, invalid month, valid year
        assertParseFailure(parser, VALID_AMOUNT_DESC + INVALID_MONTH_DESC + VALID_YEAR_DESC,
                Date.MONTH_MESSAGE_CONSTRAINTS);

        // valid amount, valid month, invalid year
        assertParseFailure(parser, VALID_AMOUNT_DESC + VALID_MONTH_DESC + INVALID_YEAR_DESC,
                Date.YEAR_MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArguments_success() {
        // valid amount, valid month, valid year
        Budget validAmountAndDateBudget =
                new BudgetBuilder().withAmount(VALID_AMOUNT).withMonth(VALID_MONTH).withYear(VALID_YEAR).buildBudget();
        assertParseSuccess(parser, VALID_AMOUNT_DESC + VALID_MONTH_DESC + VALID_YEAR_DESC,
                new WalletBudgetCommand(validAmountAndDateBudget));

        // valid amount only
        Budget validAmountOnlyBudget = new BudgetBuilder().withAmount(VALID_AMOUNT).buildBudget();
        assertParseSuccess(parser, VALID_AMOUNT_DESC, new WalletBudgetCommand(validAmountOnlyBudget));
    }
}
