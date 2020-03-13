package seedu.address.logic.commands.wallet;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalWallet.DUCK_RICE;
import static seedu.address.testutil.TypicalWallet.VALID_AMOUNT_DUCK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code WalletExpenseCommand}.
 */
public class WalletExpenseCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newExpense_success() {

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addExpense(DUCK_RICE);
        assertCommandSuccess(
                new WalletExpenseCommand(DUCK_RICE),
                model,
                String.format(WalletExpenseCommand.MESSAGE_SUCCESS,
                        DUCK_RICE,
                        DUCK_RICE.getDate().getMonth() + " " + DUCK_RICE.getDate().getYear(),
                        "$" + VALID_AMOUNT_DUCK,
                        "$0.00"),
                expectedModel);
    }

}
