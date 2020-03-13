package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Wallet;
import seedu.address.model.transaction.Budget;
import seedu.address.model.transaction.Expense;
import seedu.address.model.transaction.Income;

/**
 * A utility class containing a list of {@code Transaction} objects to be used
 * in tests.
 */
public class TypicalWallet {
    public static final String VALID_DESC_ALLOWANCE = "Allowance from Parents";
    public static final String VALID_AMOUNT_ALLOWANCE = "300";
    public static final String VALID_DATE_ALLOWANCE = "01/01/2010";
    public static final String VALID_TAG_ALLOWANCE = "Allowance";

    public static final String VALID_DESC_TA = "CS2103 TA";
    public static final String VALID_AMOUNT_TA = "3000";
    public static final String VALID_DATE_TA = "10/10/2010";
    public static final String VALID_TAG_TA = "Job";

    public static final String VALID_DESC_DUCK = "Duck Rice";
    public static final String VALID_AMOUNT_DUCK = "3.50";
    public static final String VALID_DATE_DUCK = "02/01/2020";
    public static final String VALID_TAG_DUCK = "Food";
    public static final String VALID_MONTH_DUCK = "01";
    public static final String VALID_YEAR_DUCK = "2020";


    public static final String VALID_DESC_MRT = "Monthly MRT Concession Pass";
    public static final String VALID_AMOUNT_MRT = "45";
    public static final String VALID_DATE_MRT = "31/01/2020";
    public static final String VALID_TAG_MRT = "Transport";

    public static final String VALID_DEFAULT_BUDGET_AMOUNT_ZERO = "0";

    public static final String VALID_BUDGET_AMOUNT_JAN_2010 = "300";
    public static final String VALID_BUDGET_MONTH_JAN_2010 = "01";
    public static final String VALID_BUDGET_YEAR_JAN_2010 = "2010";

    public static final String VALID_BUDGET_AMOUNT_APR_2020 = "200";
    public static final String VALID_BUDGET_MONTH_APR_2020 = "04";
    public static final String VALID_BUDGET_YEAR_APR_2020 = "2020";

    public static final String DESC_ALLOWANCE = " " + PREFIX_NAME + VALID_DESC_ALLOWANCE;
    public static final String AMOUNT_ALLOWANCE = " " + PREFIX_AMOUNT + VALID_AMOUNT_ALLOWANCE;
    public static final String DATE_ALLOWANCE = " " + PREFIX_DATE + VALID_DATE_ALLOWANCE;
    public static final String TAG_ALLOWANCE = " " + PREFIX_TAG + VALID_TAG_ALLOWANCE;

    public static final String DESC_TA = " " + PREFIX_NAME + VALID_DESC_TA;
    public static final String AMOUNT_TA = " " + PREFIX_AMOUNT + VALID_AMOUNT_TA;
    public static final String DATE_TA = " " + PREFIX_DATE + VALID_DATE_TA;
    public static final String TAG_TA = " " + PREFIX_TAG + VALID_TAG_TA;

    public static final String DESC_DUCK = " " + PREFIX_NAME + VALID_DESC_DUCK;
    public static final String AMOUNT_DUCK = " " + PREFIX_AMOUNT + VALID_AMOUNT_DUCK;
    public static final String DATE_DUCK = " " + PREFIX_DATE + VALID_DATE_DUCK;
    public static final String TAG_DUCK = " " + PREFIX_TAG + VALID_TAG_DUCK;

    public static final String DESC_MRT = " " + PREFIX_NAME + VALID_DESC_MRT;
    public static final String AMOUNT_MRT = " " + PREFIX_AMOUNT + VALID_AMOUNT_MRT;
    public static final String DATE_MRT = " " + PREFIX_DATE + VALID_DATE_MRT;
    public static final String TAG_MRT = " " + PREFIX_TAG + VALID_TAG_MRT;

    public static final Income ALLOWANCE = new TransactionBuilder()
        .withDescription(VALID_DESC_ALLOWANCE)
        .withAmount(VALID_AMOUNT_ALLOWANCE)
        .withDate(VALID_DATE_ALLOWANCE)
        .withTag(VALID_TAG_ALLOWANCE)
        .buildIncome();
    public static final Income TA_JOB = new TransactionBuilder()
        .withDescription(VALID_DESC_TA)
        .withAmount(VALID_AMOUNT_TA)
        .withDate(VALID_DATE_TA)
        .withTag(VALID_TAG_TA)
        .buildIncome();
    public static final Expense DUCK_RICE = new TransactionBuilder()
        .withDescription(VALID_DESC_DUCK)
        .withAmount(VALID_AMOUNT_DUCK)
        .withDate(VALID_DATE_DUCK)
        .withTag(VALID_TAG_DUCK)
        .buildExpense();
    public static final Expense MRT_CONCESSION = new TransactionBuilder()
        .withDescription(VALID_DESC_MRT)
        .withAmount(VALID_AMOUNT_MRT)
        .withDate(VALID_DATE_MRT)
        .withTag(VALID_TAG_MRT)
        .buildExpense();
    public static final Budget DEFAULT_BUDGET = new BudgetBuilder()
        .withAmount(VALID_DEFAULT_BUDGET_AMOUNT_ZERO)
        .setAsDefault()
        .buildBudget();
    public static final Budget BUDGET_JAN_2010 = new BudgetBuilder()
        .withAmount(VALID_BUDGET_AMOUNT_JAN_2010)
        .withMonth(VALID_BUDGET_MONTH_JAN_2010)
        .withYear(VALID_BUDGET_YEAR_JAN_2010)
        .buildBudget();
    public static final Budget BUDGET_APRIL_2020 = new BudgetBuilder()
        .withAmount(VALID_BUDGET_AMOUNT_APR_2020)
        .withMonth(VALID_BUDGET_MONTH_APR_2020)
        .withYear(VALID_BUDGET_YEAR_APR_2020)
        .buildBudget();

    private TypicalWallet() {
    } // prevents instantiation

    /**
     * Returns a {@code Wallet} with all the typical transactions.
     */
    public static Wallet getTypicalWallet() {
        Wallet wallet = new Wallet();
        for (Income income : getTypicalIncomes()) {
            wallet.addIncome(income);
        }
        for (Expense expense : getTypicalExpenses()) {
            wallet.addExpense(expense);
        }
        for (Budget budget : getTypicalBudgets()) {
            wallet.setBudget(budget);
        }

        wallet.setDefaultBudget(getTypicalDefaultBudget());

        return wallet;
    }

    public static List<Income> getTypicalIncomes() {
        return new ArrayList<>(Arrays.asList(TA_JOB));
    }

    public static List<Expense> getTypicalExpenses() {
        return new ArrayList<>(Arrays.asList(DUCK_RICE, MRT_CONCESSION));
    }

    public static List<Budget> getTypicalBudgets() {
        return new ArrayList<>(Arrays.asList(BUDGET_JAN_2010));
    }

    public static Budget getTypicalDefaultBudget() {
        return DEFAULT_BUDGET;
    }
}
