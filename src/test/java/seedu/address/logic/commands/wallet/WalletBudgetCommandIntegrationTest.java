package seedu.address.logic.commands.wallet;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalWallet.BUDGET_JAN_2010;
import static seedu.address.testutil.TypicalWallet.DEFAULT_BUDGET;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class WalletBudgetCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newDefaultBudget_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setDefaultBudget(DEFAULT_BUDGET);

        assertCommandSuccess(new WalletBudgetCommand(DEFAULT_BUDGET), model,
                String.format(WalletBudgetCommand.MESSAGE_SUCCESS_DEFAULT, DEFAULT_BUDGET.getAmount()), expectedModel);
    }

    @Test
    public void execute_newBudget_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setBudget(BUDGET_JAN_2010);

        assertCommandSuccess(new WalletBudgetCommand(BUDGET_JAN_2010), model,
                String.format(WalletBudgetCommand.MESSAGE_SUCCESS,
                        BUDGET_JAN_2010.getAmount(),
                        BUDGET_JAN_2010.getMonth(),
                        BUDGET_JAN_2010.getYear()), expectedModel);
    }
}
