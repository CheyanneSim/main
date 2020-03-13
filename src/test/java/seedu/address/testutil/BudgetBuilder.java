package seedu.address.testutil;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Budget;

/**
 * A utility class to help with building Budget objects.
 */
public class BudgetBuilder {
    public static final double DEFAULT_AMOUNT = 0;

    private Amount amount;
    private Month month;
    private Year year;
    private boolean isDefault;

    public BudgetBuilder() {
        this.amount = new Amount(DEFAULT_AMOUNT);
        this.month = LocalDate.now().getMonth();
        this.year = Year.of(LocalDate.now().getYear());
        isDefault = false;
    }

    /**
     * Initializes the BudgetBuilder with the data of {@code budgetToCopy}.
     */
    public BudgetBuilder(Budget budgetToCopy) {
        this.amount = budgetToCopy.getAmount();
        this.month = budgetToCopy.getMonth();
        this.year = budgetToCopy.getYear();
        isDefault = budgetToCopy.isDefault();
    }

    /**
     * Sets the {@code amount} of the {@code Budget} that we are building.
     */
    public BudgetBuilder withAmount(String amount) {
        try {
            this.amount = ParserUtil.parseAmount(amount);
            return this;
        } catch (ParseException e) {
            return this;
        }
    }

    /**
     * Sets the {@code month} of the {@code Budget} that we are building.
     */
    public BudgetBuilder withMonth(String month) {
        try {
            this.month = ParserUtil.parseMonth(month);
            return this;
        } catch (ParseException e) {
            return this;
        }
    }

    /**
     * Sets the {@code year} of the {@code Budget} that we are building.
     */
    public BudgetBuilder withYear(String year) {
        try {
            this.year = ParserUtil.parseYear(year);
            return this;
        } catch (ParseException e) {
            return this;
        }
    }

    /**
     * Sets the type (default or normal) of the {@code Budget} that we are building.
     */
    public BudgetBuilder setAsDefault() {
        this.isDefault = true;
        return this;
    }

    /**
     * Returns a {@code Budget} object.
     */
    public Budget buildBudget() {
        Budget newBudget = new Budget(amount, month, year);
        if (isDefault) {
            newBudget.setAsDefault();
        }
        return newBudget;
    }
}
