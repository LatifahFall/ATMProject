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

public class DepositController {
    private Controller controller;
    public Button Confirm;
    public Button Cancel;

    @FXML
    private TextField amountField;
    public void initialize() {
        controller = Controller.getInstance(); // Reference the singleton instance
    }
    @FXML
    private void handleConfirmButtonClick(ActionEvent event) throws IOException {

        try {
            double amount =Double.parseDouble(amountField.getText().trim());
            if (controller.deposit(amount)) {
                System.out.println(controller.getcurrent_user()); //see if deposit was saved
                navigateToScene(event, "viewme/DepositSuccess.fxml");
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
