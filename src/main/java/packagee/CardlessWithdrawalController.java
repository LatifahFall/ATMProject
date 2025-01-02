package packagee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import java.io.IOException;

public class CardlessWithdrawalController {
    private Controller controller;
    @FXML
    private TextField idenField;
    @FXML
    private TextField pincodeField;
    public Button Cancel;
    public Button Confirm;
    public void initialize() {
        controller = Controller.getInstance(); // get the singleton instance
    }
    @FXML
    private void handleConfirmButtonClick(ActionEvent event) throws IOException {
        try {
            String iden=idenField.getText().trim(),pincode=pincodeField.getText().trim();
            // Is (iden,pincode) transfer in database ?
            if (controller.transferExists(iden,pincode)) {
                navigateToScene(event, "viewme/WithdrawSuccess.fxml");
            } else {
                navigateToScene(event, "viewme/Wrong.fxml");
            }
        }catch(Exception e) {navigateToScene(event, "viewme/SystemFailure.fxml");}
    }

    @FXML
    private void handleCancelButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/Hello.fxml");
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