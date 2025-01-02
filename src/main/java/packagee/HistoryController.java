package packagee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class HistoryController{
    @FXML
    private Controller controller;

    @FXML
    private TableView<Row> table;

    /*private String[][] transactionsStrings = {
            {"2024-12-01", "Deposit", "500.00"},
            {"2024-12-03", "Withdrawal", "200.00"},
            {"2024-12-05", "Transfer", "150.00"}
    };*/
    private String[][] transactionsStrings(Transaction [] trs){
        String[][] r = new String[trs.length][3]; // Assuming Date, Type, and Amount

        // Populate the 2D array
        for (int i = 0; i < trs.length; i++) {
            Transaction tr = trs[i];
            r[i][0] = tr.getSQLTimestamp();    // Assuming getDate() returns a String
            r[i][1] = tr.getType();    // Assuming getType() returns a String
            r[i][2] = String.valueOf(tr.getAmount()); // Assuming getAmount() returns a number
        }

        return r;
    }
    public void initialize() {
        controller = Controller.getInstance();
        // Convert the array of arrays to an ObservableList of Row objects
        ObservableList<Row> rows = FXCollections.observableArrayList();
            for (String[] data : transactionsStrings(controller.getCurrentUserTransactions())) {
                rows.add(new Row(data[0], data[1], data[2]));}
            // Set the items of the TableView
        table.setItems(rows);
    }
}
