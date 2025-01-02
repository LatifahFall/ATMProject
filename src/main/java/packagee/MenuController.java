package packagee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    public Button CheckBalance;
    public Button Deposit;
    public Button Withdraw;
    public Button Cancel;
    public Button TopUp;
    public Button Transfer;
    public Button MoreOptions;

    @FXML
    private void handleCancelButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/Hello.fxml");
    }

    @FXML
    private void handleCheckBalanceButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/CheckBalance.fxml");
    }
    @FXML
    private void handleDepositButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/Deposit.fxml");
    }
    @FXML
    private void handleWithdrawButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/Withdraw.fxml");
    }
    @FXML
    private void handleTopUpButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/TopUp.fxml");
    }
    @FXML
    private void handleTransferButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/Transfer.fxml");
    }
    @FXML
    private void handleMoreOptionsButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/MoreOptions.fxml");
    }

    private void navigateToScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.show();
    }
}