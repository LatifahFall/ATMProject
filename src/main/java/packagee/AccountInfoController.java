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

public class AccountInfoController {
    @FXML
    private Controller controller;
    public Button Cancel;

    @FXML
    private Label nameLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label emailLabel;
    @FXML
    public void initialize() {
        controller = Controller.getInstance(); // Get the singleton instance
        try {
            balanceLabel.setText("Balance : "+String.format("%.2f", controller.getBalance())+" dhs");
            nameLabel.setText("Account Holder: "+controller.getCurrentUserName());
            phoneLabel.setText("Phone : "+controller.getCurrentUserPhone());
            idLabel.setText("Account Id: "+String.format("%d", controller.getCurrentUserId()));
            emailLabel.setText("Email : "+controller.getCurrentUserEmail());
        }
        catch(Exception E){
            balanceLabel.setText("Balance : --ERROR--");
            idLabel.setText("Account Id: --ERROR--");
            nameLabel.setText("Account Holder: --ERROR--");
            phoneLabel.setText("Phone : --ERROR--");
            emailLabel.setText("Email : --ERROR--");
        }
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