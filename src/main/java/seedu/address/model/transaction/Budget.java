package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

/**
 * Represents a Budget for the wallet.
 */
public class Budget {
    public static final String BUDGET_OK = "Your current expenditure is: %1$s / %2$s";
    public static final String BUDGET_EXCEEDED = "You have exceeded your budget by: %1$s / %2$s";

    private Amount budget;
    private Month month;
    private Year year;
    private boolean isDefault = false;

    public Budget(Amount budget) {
        this.budget = budget;
        this.month = Budget.getDefault().getMonth();
        this.year = Budget.getDefault().getYear();
        isDefault = true;
    }

    public Budget(Amount budget, Month month, Year year) {
        requireAllNonNull(budget, month, year);
        this.budget = budget;
        this.month = month;
        this.year = year;
    }

    public void setAsDefault() {
        isDefault = true;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public Amount getAmount() {
        return budget;
    }

    public Month getMonth() {
        return month;
    }

    public Year getYear() {
        return year;
    }

    public static Budget getDefault() {
        return new Budget(new Amount(0), LocalDate.now().getMonth(), Year.of(LocalDate.now().getYear()));
    }

    /**
     * Checks if the date of the {@code other Budget} is equal to the date of this Budget object.
     */
    public boolean dateEquals(Budget other) {
        requireNonNull(other);

        return month.equals(other.getMonth())
                && year.equals(other.getYear());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof Budget
                && budget.equals(((Budget) other).getAmount())
                && month.equals(((Budget) other).getMonth())
                && year.equals(((Budget) other).getYear());
    }

    @Override
    public String toString() {
        return budget.toString();
    }
}
