package packagee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TransferSuccessController {
    public Button Cancel;
    @FXML
    private Controller controller;
    @FXML
    private Label idenField;
    @FXML
    private Label pincodeField;

    @FXML
    public void initialize() {
        controller = Controller.getInstance(); // Get the singleton instance
        idenField.setText("Iden Code : "+controller.getIden());
        pincodeField.setText("PINCODE : "+controller.getPincode());

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
