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

public class TopUpController {
    public Button Cancel;
    public Button IAM;
    public Button INWI;
    public Button ORANGE;
    @FXML
    private Controller controller;
    @FXML
    public void initialize() {
        controller = Controller.getInstance(); // Reference the singleton instance
    }
    @FXML
    private void handleCancelButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/Hello.fxml");
    }
    @FXML
    private void handleInwiButtonClick(ActionEvent event) throws IOException {
        controller.setOperator("INWI");
        navigateToScene(event, "viewme/TopUpAmount.fxml");
    }
    @FXML
    private void handleOrangeButtonClick(ActionEvent event) throws IOException {
        controller.setOperator("ORANGE");
        navigateToScene(event, "viewme/TopUpAmount.fxml");
    }
    @FXML
    private void handleIamButtonClick(ActionEvent event) throws IOException {
        controller.setOperator("IAM");
        navigateToScene(event, "viewme/TopUpAmount.fxml");
    }

    private void navigateToScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}