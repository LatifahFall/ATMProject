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

public class TransferController {

    public Button Cancel;
    public Button M100;
    public Button M200;
    public Button M500;
    public Button M1000;
    public Button M2000;
    public Button M1500;
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
    private void handleAmountButtonClick(ActionEvent event) throws IOException {
        try {
            Button clickedButton = (Button) event.getSource();
            String buttonId = clickedButton.getId();
            switch (buttonId){
                case "M100": controller.setAmount(100); break;
                case "M200": controller.setAmount(200); break;
                case "M500": controller.setAmount(500); break;
                case "M1000": controller.setAmount(1000); break;
                case "M1500": controller.setAmount(1500); break;
                case "M2000":controller.setAmount(2000); break;
            }

            navigateToScene(event, "viewme/TransferPhone.fxml");
        }catch(Exception e) {navigateToScene(event, "viewme/SystemFailure.fxml");}
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