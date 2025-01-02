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

public class LoginFormController {
    private Controller controller;
    @FXML
    private TextField idField;
    public Button Cancel;
    public Button Confirm;
    public void initialize() {
        controller = Controller.getInstance(); // the singleton instance
    }

    @FXML
    private void handleConfirmButtonClick(ActionEvent event) throws IOException {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            // Is id in database ?
            if (controller.authenticate(id)) {
                controller.setUserId(id); // Store user ID
                navigateToScene(event, "viewme/LoginFormPassword.fxml");
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}