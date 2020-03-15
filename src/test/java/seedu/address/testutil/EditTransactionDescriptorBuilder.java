package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.logic.commands.wallet.WalletEditCommand.EditTransactionDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class to help with building EditTransactionDescriptor objects.
 */
public class EditTransactionDescriptorBuilder {
    private EditTransactionDescriptor descriptor;

    public EditTransactionDescriptorBuilder() {
        descriptor = new EditTransactionDescriptor();
    }

    public EditTransactionDescriptorBuilder(EditTransactionDescriptor descriptor) {
        this.descriptor = new EditTransactionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTransactionDescriptor} with fields containing {@code transaction}'s details
     */
    public EditTransactionDescriptorBuilder(Transaction transaction) {
        descriptor = new EditTransactionDescriptor();
        descriptor.setDescription(transaction.getDescription());
        descriptor.setAmount(transaction.getAmount());
        descriptor.setDate(transaction.getDate());
        descriptor.setTag(transaction.getTag());
    }

    /**
     * Sets the {@code Description} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withAmount(double amount) {
        descriptor.setAmount(new Amount(amount));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withDate(LocalDate date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Parses the {@code tag} into a {@code Tag} and set it to the {@code EditTransactionDescriptor}
     * that we are building.
     */
    public EditTransactionDescriptorBuilder withTag(String tag) {
        descriptor.setTag(new Tag(tag));
        return this;
    }

    public EditTransactionDescriptor build() {
        return descriptor;
    }
}
