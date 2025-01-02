package packagee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePasswordController {
    @FXML
    private Controller controller;
    public Button Cancel;
    public Button Confirm;
    @FXML
    private PasswordField passwordField;
    private boolean isValidPassword(String psswd){
        System.out.println(psswd.length());
        return (psswd.length()<=16)&&(psswd.length()>=8);
    }
    @FXML
    public void initialize() {
        controller = Controller.getInstance();
    }
    @FXML
    private void handleConfirmButtonClick(ActionEvent event) throws IOException {
        try{
            String password=passwordField.getText().trim();
            System.out.println(password);
            if(!isValidPassword(password)) {navigateToScene(event, "viewme/ChangePasswordFailure.fxml");}
            else {
                if (controller.changePassword(password)) {
                    navigateToScene(event, "viewme/ChangePasswordSuccess.fxml");
                } else {
                    navigateToScene(event, "viewme/Wrong.fxml");
                }
            }
        }catch(Exception E){ navigateToScene(event, "viewme/SystemFailure.fxml");}
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

