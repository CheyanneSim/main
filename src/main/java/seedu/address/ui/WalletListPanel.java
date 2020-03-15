package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.transaction.Transaction;

/**
 * Panel containing the list of transactions.
 */
public class WalletListPanel extends UiPart<Region> {
    private static final String FXML = "WalletListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(WalletListPanel.class);

    @FXML
    private ListView<Transaction> transactionListView;

    public WalletListPanel(ObservableList<Transaction> transactionList) {
        super(FXML);

        transactionListView.setItems(transactionList);
        transactionListView.setCellFactory(listView -> new TransactionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Transaction} using a {@code TransactionCard}.
     */
    class TransactionListViewCell extends ListCell<Transaction> {
        @Override
        protected void updateItem(Transaction transaction, boolean empty) {
            super.updateItem(transaction, empty);

            if (empty || transaction == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new WalletCard(transaction, getIndex() + 1).getRoot());
            }
        }
    }

}
