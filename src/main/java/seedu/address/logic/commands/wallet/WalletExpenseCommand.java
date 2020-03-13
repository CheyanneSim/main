package seedu.address.logic.commands.wallet;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliPrefix.WALLET_COMMAND_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.Expense;

/**
 * Adds an expense to the wallet.
 */
public class WalletExpenseCommand extends Command {
    public static final String COMMAND_WORD = "expense";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an expense to the wallet. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_AMOUNT + "AMOUNT "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + WALLET_COMMAND_TYPE + " "
            + COMMAND_WORD + " "
            + PREFIX_NAME + "Chicken Rice "
            + PREFIX_AMOUNT + "3.50 "
            + PREFIX_DATE + "10/10/2010 "
            + PREFIX_TAG + "Food";

    public static final String MESSAGE_SUCCESS = "New expense added: %1$s\nYour expenditure for %2$s is: "
            + "%3$s/%4$s";

    private final Expense toAdd;

    /**
     * Creates a WalletExpenseCommand to add the specified {@code Expense}
     */
    public WalletExpenseCommand(Expense expense) {
        requireNonNull(expense);
        toAdd = expense;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addExpense(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                toAdd,
                toAdd.getDate().getMonth() + " " + toAdd.getDate().getYear(),
                model.getTotalExpenditureInMonth(toAdd.getDate()),
                model.getBudget(toAdd.getDate().getMonth(),
                        toAdd.getDate().getYear())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WalletExpenseCommand // instanceof handles nulls
                && toAdd.equals(((WalletExpenseCommand) other).toAdd));
    }
}
