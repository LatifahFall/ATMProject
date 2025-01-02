package packagee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormPasswordController {
    private Controller controller;
    @FXML
    private PasswordField passwordField;
    public Button Cancel;
    public Button Confirm;
    public void initialize() {
        controller = Controller.getInstance(); // Get the singleton instance
    }
    @FXML
    private void handleConfirmButtonClick(ActionEvent event) throws IOException {
        try {
            String password =passwordField.getText().trim();
            // Is id and password in database ?
            if (controller.authenticate(controller.getUserId(),password)) {
                controller.setUserId(controller.getUserId()); // Store user ID
                controller.setCurrentUser(); //Store current user (Account)
                controller.loadRecentTransactions();
                System.out.println(controller.getcurrent_user()); //just to display account info
                navigateToScene(event, "viewme/Menu.fxml");
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