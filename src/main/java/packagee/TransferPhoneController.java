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
import java.security.SecureRandom;

import java.io.IOException;
import java.util.Date;

public class TransferPhoneController {
    private static final SecureRandom RANDOM = new SecureRandom();
    @FXML
    private Controller controller;
    public Button Cancel;
    public Button Confirm;
    @FXML
    private TextField phoneField;

    @FXML
    public void initialize() {
        controller = Controller.getInstance(); // Reference the singleton instance
    }
    private static String generateIden() {
        return String.valueOf(new Date().getTime());
    }
    private static String generatePincode(int length) {
        StringBuilder pin = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            pin.append(RANDOM.nextInt(10)); // Generate digits 0-9
        }
        return pin.toString();
    }
    @FXML
    private void handleCancelButtonClick(ActionEvent event) throws IOException {
        navigateToScene(event, "viewme/Hello.fxml");
    }

    @FXML
    private void handleConfirmButtonClick(ActionEvent event) throws IOException {
        try {
            String phone= phoneField.getText().trim();
            String iden=generateIden();
            String pincode=generatePincode(4);
            if (controller.transfer(controller.getAmount(),phone,iden,pincode)) {
                System.out.println(controller.getcurrent_user()); //See if Transfer was saved
                controller.setIden(iden);
                controller.setPincode(pincode);
                navigateToScene(event, "viewme/TransferSuccess.fxml");
            } else {
                navigateToScene(event, "viewme/Wrong.fxml");
            }
        }catch(Exception e) {navigateToScene(event, "viewme/SystemFailure.fxml");}    }

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