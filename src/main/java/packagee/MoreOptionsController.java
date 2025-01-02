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

public class MoreOptionsController {

    public Button Cancel;
    public Button ChangePassword;
    public Button AccountInfo;
    public Button History;


    @FXML
    private void handleCancelButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/Hello.fxml");
    }

    @FXML
    private void handleChangePasswordButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/ChangePassword.fxml");
    }
    @FXML
    private void handleHistoryButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/History.fxml");
    }
    @FXML
    private void handleAccountInfoButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/AccountInfo.fxml");
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